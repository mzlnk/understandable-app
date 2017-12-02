package pl.understandable.understandable_app.user;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
                if(!UserManager.isUserLoggedIn()) {
                    return;
                }
                if(isNetworkAvailable(manager)) {
                    if(!isSyncAvailable()) {
                        RequestExecutor.offerRequest(new ShowWelcomeMessage());
                        boolean syncResult = syncFromServer();
                        if(syncResult) {
                            System.out.println("Sync from server completed successfully");
                        } else {
                            System.out.println("Sync from server not completed successfully");
                        }
                    }
                    syncStatus  = SyncStatus.ONLINE;

                    if(UserManager.isSyncRequired()) {
                        boolean syncResult = syncToServer();
                        if(syncResult) {
                            System.out.println("Sync to server completed successfully");
                        } else {
                            System.out.println("Sync from server not completed successfully");
                        }
                    }
                } else {
                    if(isSyncAvailable()) {
                        RequestExecutor.offerRequest(new ShowSyncStoppedMessage());
                    }
                    syncStatus = SyncStatus.OFFLINE;
                }
            }
        };
        timer.scheduleAtFixedRate(syncTask, 10000L, 1000L);
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
            valuePairs.add(new BasicNameValuePair("token_id", "")); //todo : tokenId here
            valuePairs.add(new BasicNameValuePair("data", UserManager.getJsonData().toString()));
            httpPost.setEntity(new UrlEncodedFormEntity(valuePairs));

            HttpResponse httpResponse = client.execute(httpPost);
            String response = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("Sync response: " + response);

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

            List valuePairs = new ArrayList(1);
            valuePairs.add(new BasicNameValuePair("token_id", "")); //todo: tokenId here
            httpPost.setEntity(new UrlEncodedFormEntity(valuePairs));

            HttpResponse httpResponse = client.execute(httpPost);
            String response = EntityUtils.toString(httpResponse.getEntity());

            System.out.println("Sync response: " + response);

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
