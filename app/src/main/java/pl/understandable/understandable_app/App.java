package pl.understandable.understandable_app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONObject;

import pl.understandable.understandable_app.user.AchievementChecker;
import pl.understandable.understandable_app.user.RequestExecutor;
import pl.understandable.understandable_app.user.SyncManager;
import pl.understandable.understandable_app.user.TimeLearntManager;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.user.data.achievements.AchievementId;
import pl.understandable.understandable_app.utils.AdUtil;
import pl.understandable.understandable_app.utils.announcement.AnnouncementManager;
import pl.understandable.understandable_app.utils.font.Font;
import pl.understandable.understandable_app.utils.font.FontsOverride;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */

public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Montserrat-Regular-PL.ttf");
        prepareCustomWordsSetsDirectory();
        Font.loadBuiltInTypefaces(getApplicationContext());

        AdUtil.init(getApplicationContext());

        UserManager.init();
        googleSilentSignIn();

        SyncManager.init(getApplicationContext());
        //RequestExecutor.init();
        TimeLearntManager.init();
        AchievementChecker.init(getApplicationContext());

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    private void prepareCustomWordsSetsDirectory() {
        File customWordsSetsDirectory = new File(getFilesDir(), "/words_sets/");
        File customWordsSetsInfoDirectory = new File(getFilesDir(), "/words_sets/info/");
        if(!customWordsSetsDirectory.exists()) {
            customWordsSetsDirectory.mkdir();
        }
        if(!customWordsSetsInfoDirectory.exists()) {
            customWordsSetsInfoDirectory.mkdir();
        }
    }

    private void googleSilentSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.server_client_id)).requestProfile().build();
        GoogleSignInClient client = GoogleSignIn.getClient(getApplicationContext(), gso);
        client.silentSignIn().addOnCompleteListener(new OnCompleteListener<GoogleSignInAccount>() {
            @Override
            public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                try {
                    GoogleSignInAccount account = task.getResult();
                    if (account != null) {
                        Log.d("USER", "Token ID: " + account.getIdToken());
                        UserManager.getUser().setTokenId(account.getIdToken());
                        UserManager.setUserStatus(UserManager.UserStatus.SIGNED_IN);
                    } else {
                        UserManager.setUserStatus(UserManager.UserStatus.NO_ACCOUNT);
                    }
                } catch (Exception e) {
                }
            }
        });
    }

}
