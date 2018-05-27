package pl.understandable.understandable_app.user;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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

    private static final int ONE_SECOND_IN_MILLIS = 1000;
    private static final int CONNECTION_TIMEOUT = 0;
    private static final int SOCKET_TIMEOUT = 0;

    private static SyncParams syncParams = new SyncParams();

    public static SyncParams getSyncParams() {
        return syncParams;
    }

    public static void clearSyncParams() {
        syncParams = new SyncParams();
    }

    public static void init(final Context context) {
        final ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Timer timer = new Timer();
        TimerTask syncTask = new TimerTask() {
            @Override
            public void run() {
                if(syncParams.isActionInProgress()) {
                    return;
                }
                if(!UserManager.isUserSignedIn()) {
                    return;
                }

                if(NetworkUtil.isNetworkAvailable(manager)) {
                    if(!syncParams.isSyncOnline()) {
                        if(!syncParams.isSyncRequiredAfterReconnect()) {
                            syncFromServer(context);
                        } else {
                            syncToServer(context);
                        }
                        if(MainActivity.getActivity() != null) {
                            RequestExecutor.offerRequest(new ShowWelcomeMessage(context));
                        }
                    } else {
                        if(UserManager.isSyncRequired()) {
                            syncToServer(context);
                        }
                    }
                    syncParams.setSyncStatus(SyncStatus.ONLINE);

                } else {
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
                syncParams.setActionInProgress(true);
                ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if(NetworkUtil.isNetworkAvailable(manager)) {
                    syncFromServer(context);
                    syncParams.setSyncStatus(SyncStatus.ONLINE);

                    UserFragment fragment = new UserFragment();
                    fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_START)).commit();
                    RequestExecutor.offerRequest(new ShowWelcomeMessage(context), 500L);
                }
                syncParams.setActionInProgress(false);
            }
        }, 1L);
    }

    public static void syncToServerAfterLogOut(final Context context) {
        syncToServer(context);
    }

    private static boolean syncToServer(Context context) {
        try {
            syncParams.setActionInProgress(true);
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("https://dl.understandable.pl/android_webservice/sync_to_server.php");
            httpPost.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            httpPost.setHeader("Pragma", "no-cache");
            httpPost.setHeader("User-Agent", "");

            System.out.println("[JSON TO SERVER] Json: " + UserManager.getUser().toJson().toString());

            List valuePairs = new ArrayList(3);
            valuePairs.add(new BasicNameValuePair("token_id", GoogleSignIn.getLastSignedInAccount(context).getIdToken()));
            valuePairs.add(new BasicNameValuePair("data", UserManager.getUser().toJson().toString()));
            valuePairs.add(new BasicNameValuePair("elements_to_sync", UserManager.getElementsToSyncJson().toString()));
            UrlEncodedFormEntity form  = new UrlEncodedFormEntity(valuePairs, "UTF-8");
            httpPost.setEntity(form);

            HttpResponse httpResponse = client.execute(httpPost);
            String response = EntityUtils.toString(httpResponse.getEntity());

            UserManager.clearElementsToSync();
            UserManager.setSyncRequired(false);
            syncParams.setActionInProgress(false);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            syncParams.setActionInProgress(false);
            return false;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            syncParams.setActionInProgress(false);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            syncParams.setActionInProgress(false);
            return false;
        }
        return true;
    }

    private static boolean syncFromServer(Context context) {
        try {
            syncParams.setActionInProgress(true);
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("https://dl.understandable.pl/android_webservice/sync_from_server.php");
            httpPost.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            httpPost.setHeader("Pragma", "no-cache");
            httpPost.setHeader("User-Agent", "");

            List valuePairs = new ArrayList(1);
            valuePairs.add(new BasicNameValuePair("token_id", GoogleSignIn.getLastSignedInAccount(context).getIdToken()));
            httpPost.setEntity(new UrlEncodedFormEntity(valuePairs));

            HttpResponse httpResponse = client.execute(httpPost);
            String response = EntityUtils.toString(httpResponse.getEntity());
            response = response.replaceAll("\"", "\\\"");

            JSONObject data = new JSONObject(response);
            UserManager.getUser().updateFromJson(data);
            syncParams.setDataPulledFromServer(true);
            syncParams.setActionInProgress(false);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            syncParams.setActionInProgress(false);
            return false;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            syncParams.setActionInProgress(false);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            syncParams.setActionInProgress(false);
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            syncParams.setActionInProgress(false);
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

        private boolean actionInProgress = false;

        public void setDataPulledFromServer(boolean status) {
            this.dataPulledFromServer = status;
        }

        public void setSyncStatus(SyncStatus status) {
            this.syncStatus = status;
        }

        public void setSyncRequiredAfterReconnect(boolean required) {
            syncRequiredAfterReconnect = required;
        }

        public void setActionInProgress(boolean actionInProgress) {
            this.actionInProgress = actionInProgress;
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

        public boolean isActionInProgress() {
            return actionInProgress;
        }

    }

}
