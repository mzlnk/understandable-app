package pl.understandable.understandable_app.webservice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.fragments.start.StartFragment;
import pl.understandable.understandable_app.user.SyncManager;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.utils.NetworkUtil;

/**
 * Created by Marcin Zielonka on 2018-03-25.
 */

public class RemoveUserAccountTask extends AsyncTask<Void, Void, Integer> {

    private Context context;
    private FragmentManager fragmentManager;

    public RemoveUserAccountTask(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        if(!NetworkUtil.isNetworkAvailable((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))) {
            return 1;
        }
        if(!removeUserAccount(context)) {
            return 2;
        }
        return 0;
    }

    @Override
    protected void onPostExecute(Integer result) {
        switch(result) {
            case 1:
                Toast.makeText(context, "Brak dostępu do Internetu", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(context, "Usunięcie profilu nie powiodło się", Toast.LENGTH_SHORT).show();
                break;
            case 0:
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build();
                GoogleSignInClient client = GoogleSignIn.getClient(context, gso);
                client.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        UserManager.logout();
                        SyncManager.clearSyncParams();
                        StartFragment fragment = new StartFragment();
                        fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment).commit();
                        Toast.makeText(context, "Pomyślnie usunięto profil", Toast.LENGTH_SHORT).show();
                    }
                });
                client.revokeAccess().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
        }
    }

    private static boolean removeUserAccount(Context context) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("https://dl.understandable.pl/android_webservice/remove_account.php");
            httpPost.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            httpPost.setHeader("Pragma", "no-cache");
            httpPost.setHeader("User-Agent", "");

            List valuePairs = new ArrayList(1);
            valuePairs.add(new BasicNameValuePair("token_id", GoogleSignIn.getLastSignedInAccount(context).getIdToken()));
            httpPost.setEntity(new UrlEncodedFormEntity(valuePairs));

            HttpResponse httpResponse = client.execute(httpPost);
            String response = EntityUtils.toString(httpResponse.getEntity());
            response = response.replaceAll("\"", "\\\"");

            if(response.equals("Invalid token")) {
                return false;
            } else {
                return true;
            }

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
    }

}
