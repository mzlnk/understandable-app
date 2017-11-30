package pl.understandable.understandable_app.user;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
                        //pull data from server db
                    }
                    syncStatus  = SyncStatus.ONLINE;

                    if(UserManager.isSyncRequired()) {
                        //sync
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

    private static enum SyncStatus {
        OFFLINE,
        ONLINE;
    }

}
