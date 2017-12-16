package pl.understandable.understandable_app.user;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentManager;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.activities.MainActivity;
import pl.understandable.understandable_app.fragments.user.UserFragment;
import pl.understandable.understandable_app.user.requests.ShowSyncStoppedMessage;
import pl.understandable.understandable_app.user.requests.ShowWelcomeMessage;
import pl.understandable.understandable_app.utils.NetworkUtil;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class SyncManager {

    private static SyncParams syncParams = new SyncParams();

    public static SyncParams getSyncParams() {
        return syncParams;
    }

    public static void init(final Context context) {
        System.out.println("[INIT] Json");
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
                    if(!syncParams.isSyncOnline()) {
                        if(MainActivity.getActivity() != null) {
                            RequestExecutor.offerRequest(new ShowWelcomeMessage(context));
                        } else {
                            System.out.println("[WELCOME] Welcome message not showed - null!");
                        }
                        if(!syncParams.isSyncRequiredAfterReconnect()) {
                            System.out.println("[WELCOME] Welcome message");
                            System.out.println("Sync from server");

                            syncFromServer(context);
                        }
                    }
                    syncParams.setSyncStatus(SyncStatus.ONLINE);

                    if(UserManager.isSyncRequired() || syncParams.isSyncRequiredAfterReconnect()) {
                        System.out.println("Sync to server");
                        syncToServer(context);
                    }
                } else {
                    System.out.println("No network available");
                    if(syncParams.isSyncOnline()) {
                        RequestExecutor.offerRequest(new ShowSyncStoppedMessage(context));
                        if(UserManager.isSyncRequired()) {
                            syncParams.setSyncRequiredAfterReconnect(true);
                        }
                    }
                    syncParams.setSyncStatus(SyncStatus.OFFLINE);
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
                    syncParams.setSyncStatus(SyncStatus.ONLINE);

                    UserFragment fragment = new UserFragment();
                    fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_START)).commit();
                }
            }
        }, 1L);
    }

    private static boolean syncToServer(Context context) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("https://www.understandable.pl/resources/script/sync_to_server.php");

            System.out.println("[JSON TO SERVER] Json: " + UserManager.getUser().toJson().toString());

            List valuePairs = new ArrayList(2);
            valuePairs.add(new BasicNameValuePair("token_id", GoogleSignIn.getLastSignedInAccount(context).getIdToken()));
            valuePairs.add(new BasicNameValuePair("data", UserManager.getUser().toJson().toString()));
            valuePairs.add(new BasicNameValuePair("elements_to_sync", UserManager.getElementsToSyncJson().toString()));
            httpPost.setEntity(new UrlEncodedFormEntity(valuePairs));

            HttpResponse httpResponse = client.execute(httpPost);
            String response = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("JSON TO SERVER RESPONSE] Json: " + response);

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
            System.out.println("[JSON FROM SERVER] Json: " + response);

            JSONObject data = new JSONObject(response);
            UserManager.getUser().updateFromJson(data);
            System.out.println("name field: " + data.getString("name"));
            syncParams.setDataPulledFromServer(true);

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

    public static class SyncParams {

        private boolean syncRequiredAfterReconnect = false;
        private boolean dataPulledFromServer = false;
        private SyncStatus syncStatus = SyncStatus.OFFLINE;

        public void setDataPulledFromServer(boolean status) {
            this.dataPulledFromServer = status;
        }

        public void setSyncStatus(SyncStatus status) {
            this.syncStatus = status;
        }

        public void setSyncRequiredAfterReconnect(boolean required) {
            syncRequiredAfterReconnect = required;
        }

        public boolean isSyncRequiredAfterReconnect() {
            return syncRequiredAfterReconnect;
        }

        public boolean isDataPulledFromServer() {
            return dataPulledFromServer;
        }

        public boolean isSyncOnline() {
            return syncStatus.equals(SyncStatus.ONLINE);
        }

        public void logout() {
            syncRequiredAfterReconnect = false;
            syncStatus = SyncStatus.OFFLINE;
            dataPulledFromServer = false;
        }

    }

}
