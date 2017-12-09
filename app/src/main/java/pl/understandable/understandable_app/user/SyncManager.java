package pl.understandable.understandable_app.user;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.OptionalPendingResult;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.fragments.user.UserStatsFragment;
import pl.understandable.understandable_app.user.requests.ShowSyncStoppedMessage;
import pl.understandable.understandable_app.user.requests.ShowWelcomeMessage;
import pl.understandable.understandable_app.utils.NetworkUtil;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class SyncManager {

    private static boolean syncRequiredAfterReconnect = false;
    private static boolean dataPulledFromServer = false;
    private static SyncStatus syncStatus = SyncStatus.OFFLINE;

    public static boolean isSyncOnline() {
        return syncStatus.equals(SyncStatus.ONLINE);
    }

    public static void logout() {
        syncRequiredAfterReconnect = false;
        syncStatus = SyncStatus.OFFLINE;
        dataPulledFromServer = false;
    }

    public static boolean isDataPulledFromServer() {
        return dataPulledFromServer;
    }

    private static boolean isSyncRequiredAfterReconnect() {
        return syncRequiredAfterReconnect;
    }

    public static void init(final Context context) {
        final ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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

                if(NetworkUtil.isNetworkAvailable(manager)) {
                    System.out.println("Network available");
                    if(!isSyncOnline() && !isSyncRequiredAfterReconnect()) {
                        RequestExecutor.offerRequest(new ShowWelcomeMessage());
                        System.out.println("Sync from server");
                        syncFromServer(context);
                    }
                    syncStatus = SyncStatus.ONLINE;

                    if(UserManager.isSyncRequired() || isSyncRequiredAfterReconnect()) {
                        System.out.println("Sync to server");
                        syncToServer(context);
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

    public static void syncFromServerAfterLogIn(final Context context, final FragmentManager fragmentManager) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if(NetworkUtil.isNetworkAvailable(manager)) {
                    syncFromServer(context);
                    syncStatus = SyncStatus.ONLINE;

                    UserStatsFragment fragment = new UserStatsFragment();
                    fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_START)).commit();
                }
            }
        }, 1L);
    }

    private static boolean syncToServer(Context context) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("https://www.understandable.pl/resources/script/sync_to_server.php");

            List valuePairs = new ArrayList(2);
            valuePairs.add(new BasicNameValuePair("token_id", GoogleSignIn.getLastSignedInAccount(context).getIdToken()));
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

    private static boolean syncFromServer(Context context) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("https://www.understandable.pl/resources/script/sync_from_server.php");

            List valuePairs = new ArrayList(1);
            System.out.println("TokenID: " + GoogleSignIn.getLastSignedInAccount(context).getIdToken());
            valuePairs.add(new BasicNameValuePair("token_id", GoogleSignIn.getLastSignedInAccount(context).getIdToken()));
            httpPost.setEntity(new UrlEncodedFormEntity(valuePairs));

            HttpResponse httpResponse = client.execute(httpPost);
            String response = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("Json: " + response);

            JSONObject data = new JSONObject(response);
            UserManager.getUser().updateFromJson(data);
            System.out.println("name field: " + data.getString("name"));
            dataPulledFromServer = true;

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
