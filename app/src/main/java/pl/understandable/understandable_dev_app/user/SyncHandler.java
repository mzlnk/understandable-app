package pl.understandable.understandable_dev_app.user;

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

import pl.understandable.understandable_dev_app.user.data.User;
import pl.understandable.understandable_dev_app.user.requests.ShowSyncStoppedMessage;
import pl.understandable.understandable_dev_app.user.requests.ShowWelcomeMessage;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class SyncHandler {

    private static boolean syncStatus = false;

    public static boolean getSyncStatus() {
        return syncStatus;
    }

    public static void init(final Context context) {
        Timer timer = new Timer();
        TimerTask syncTask = new TimerTask() {
            @Override
            public void run() {
                if(isNetworkAvailable(context)) {
                    if(syncStatus == false) {
                        RequestExecutor.offerRequest(new ShowWelcomeMessage());
                    }
                    syncStatus = true;

                    if(User.getUser().isSyncRequired()) {
                        //sync
                    }
                } else {
                    if(syncStatus == true) {
                        RequestExecutor.offerRequest(new ShowSyncStoppedMessage());
                    }
                    syncStatus = false;
                }
            }
        };
        timer.scheduleAtFixedRate(syncTask, 10000L, 1000L);
    }

    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
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

}
