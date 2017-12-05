package pl.understandable.understandable_app.user;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;

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
import java.net.URI;
import java.net.URISyntaxException;
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

    private static SyncStatus syncStatus = SyncStatus.OFFLINE;

    public static boolean isSyncAvailable() {
        return syncStatus.equals(SyncStatus.ONLINE);
    }

    public static void init(final ConnectivityManager manager) {
        Timer timer = new Timer();
        TimerTask syncTask = new TimerTask() {
            @Override
            public void run() {
                Log.d("SYNC", "==========================================================");
                Log.d("SYNC", "Sync started");
                if(!UserManager.isUserSignedIn()) {
                    Log.d("SYNC", "No account detected");
                    Log.d("SYNC", "Sync finished");
                    return;
                }
                Log.d("SYNC", "Account detected");
                if(isNetworkAvailable(manager)) {
                    Log.d("SYNC", "Available network detected");
                    if(!isSyncAvailable()) {
                        Log.d("SYNC", "Start sync from server");
                        RequestExecutor.offerRequest(new ShowWelcomeMessage());
                        boolean syncResult = syncFromServer();
                        if(syncResult) {
                            Log.d("SYNC", "Sync from server completed successfully");
                        } else {
                            Log.d("SYNC", "Sync from server not completed successfully");
                        }
                    }
                    syncStatus  = SyncStatus.ONLINE;

                    if(UserManager.isSyncRequired()) {
                        Log.d("SYNC", "Start sync to server");
                        boolean syncResult = syncToServer();
                        if(syncResult) {
                          Log.d("SYNC", "Sync to server completed successfully");
                        } else {
                            Log.d("SYNC", "Sync from server not completed successfully");
                        }
                        UserManager.setSyncRequired(false);
                    }
                } else {
                    Log.d("SYNC", "No network available - sync stopped");
                    if(isSyncAvailable()) {
                        RequestExecutor.offerRequest(new ShowSyncStoppedMessage());
                    }
                    syncStatus = SyncStatus.OFFLINE;
                }
                Log.d("SYNC", "Sync finished");
            }
        };
        timer.scheduleAtFixedRate(syncTask, 10000L, 10000L);
    }

    private static boolean isNetworkAvailable(ConnectivityManager manager) {
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        if(activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        }

        try {
            URI uri = new URI("http://www.understandable.pl/resources/script/check_connection.php");
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(uri);
            HttpResponse httpResponse = httpClient.execute(httpPost);
        } catch (URISyntaxException e) {
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
            Log.d("SYNC-JSON", "Input: " + UserManager.getUser().toJson().toString());
            Log.d("SYNC-JSON", "Elements to sync JSON: " + UserManager.getElementsToSyncJson().toString());
            Log.d("WEB", "Http status code: " + httpResponse.getStatusLine().getStatusCode());
            Log.d("SYNC-WEB", "Sync response: " + response);

            UserManager.clearElementsToSync();

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

            Log.d("SYNC-WEB", "Token ID: " + UserManager.getUser().getTokenId());

            List valuePairs = new ArrayList(1);
            valuePairs.add(new BasicNameValuePair("token_id", UserManager.getUser().getTokenId()));
            httpPost.setEntity(new UrlEncodedFormEntity(valuePairs));

            HttpResponse httpResponse = client.execute(httpPost);
            Log.d("WEB", "Http status code: " + httpResponse.getStatusLine().getStatusCode());
            String response = EntityUtils.toString(httpResponse.getEntity());

            Log.d("SYNC-WEB", "Sync response: " + response);

            JSONObject data = new JSONObject(response);
            UserManager.getUser().updateFromJson(data);

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
