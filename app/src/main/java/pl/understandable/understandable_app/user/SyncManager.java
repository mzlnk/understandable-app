package pl.understandable.understandable_app.user;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import pl.understandable.understandable_app.user.requests.ShowSyncStoppedMessage;
import pl.understandable.understandable_app.user.requests.ShowWelcomeMessage;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class SyncManager {

    private static boolean syncRequiredAfterReconnect = false;
    private static SyncStatus syncStatus = SyncStatus.OFFLINE;

    public static boolean isSyncOnline() {
        return syncStatus.equals(SyncStatus.ONLINE);
    }

    private static boolean isSyncRequiredAfterReconnect() {
        return syncRequiredAfterReconnect;
    }

    public static void init(final ConnectivityManager manager) {
        Timer timer = new Timer();
        TimerTask syncTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Start sync");
                Log.d("SYNC", "Start sync");
                if(!UserManager.isUserSignedIn()) {
                    System.out.println("No account");
                    System.out.println("Sync finished");
                    return;
                }
                Log.d("SYNC", "Account detected");

                if(isNetworkAvailable(manager)) {
                    System.out.println("Network available");
                    if(!isSyncOnline() && !isSyncRequiredAfterReconnect()) {
                        RequestExecutor.offerRequest(new ShowWelcomeMessage());
                        System.out.println("Sync from server");
                        syncFromServer();
                    }
                    syncStatus = SyncStatus.ONLINE;

                    if(UserManager.isSyncRequired() || isSyncRequiredAfterReconnect()) {
                        System.out.println("Sync to server");
                        syncToServer();
                    }
                } else {
                    System.out.println("No network available");
                    if(isSyncOnline()) {
                        if(UserManager.isSyncRequired()) {
                            syncRequiredAfterReconnect = true;
                        }
                        RequestExecutor.offerRequest(new ShowSyncStoppedMessage());
                    }
                    syncStatus = SyncStatus.OFFLINE;
                }
            }
        };
        timer.scheduleAtFixedRate(syncTask, 1000L, 5000L);
    }

    private static boolean isNetworkAvailable(ConnectivityManager manager) {
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        if(activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        }

        try {
            HttpURLConnection urlc = (HttpURLConnection) (new URL("http://clients3.google.com/generate_204").openConnection());
            urlc.setRequestProperty("User-Agent", "Android");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(1500);
            urlc.connect();

            return (urlc.getResponseCode() == 204 && urlc.getContentLength() == 0);
        } catch (IOException e) {
            Log.e("SYNC-CONNECTION", "Error checking internet connection", e);
            return false;
        }
    }

    private static boolean syncToServer() {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("https://www.understandable.pl/resources/script/sync_to_server.php");

            List valuePairs = new ArrayList(2);
            valuePairs.add(new BasicNameValuePair("token_id", UserManager.getUser().getTokenId()));
            valuePairs.add(new BasicNameValuePair("data", UserManager.getUser().toJson().toString()));
            valuePairs.add(new BasicNameValuePair("elements_to_sync", UserManager.getElementsToSyncJson().toString()));
            httpPost.setEntity(new UrlEncodedFormEntity(valuePairs));

            HttpResponse httpResponse = client.execute(httpPost);
            String response = EntityUtils.toString(httpResponse.getEntity());

            UserManager.clearElementsToSync();
            UserManager.setSyncRequired(false);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static boolean syncFromServer() {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("https://www.understandable.pl/resources/script/sync_from_server.php");

            List valuePairs = new ArrayList(1);
            System.out.println("TokenID: " + UserManager.getUser().getTokenId());
            valuePairs.add(new BasicNameValuePair("token_id", UserManager.getUser().getTokenId()));
            httpPost.setEntity(new UrlEncodedFormEntity(valuePairs));

            HttpResponse httpResponse = client.execute(httpPost);
            String response = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("Json: " + response);

            JSONObject data = new JSONObject(response);
            UserManager.getUser().updateFromJson(data);
            System.out.println("name field: " +data.getString("name"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static enum SyncStatus {
        OFFLINE,
        ONLINE;
    }

}
