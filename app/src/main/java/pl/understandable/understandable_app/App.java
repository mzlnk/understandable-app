package pl.understandable.understandable_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import pl.understandable.understandable_app.database.database_access.DatabaseManager;
import pl.understandable.understandable_app.user.AchievementChecker;
import pl.understandable.understandable_app.user.SyncManager;
import pl.understandable.understandable_app.user.TimeLearntManager;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.utils.font.Font;
import pl.understandable.understandable_app.utils.font.FontsOverride;

import java.io.File;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */

public class App extends MultiDexApplication {

    private static GoogleAnalytics googleAnalytics;
    private static Tracker tracker;

    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Montserrat-Regular-PL.ttf");
        prepareCustomWordsSetsDirectory();
        Font.loadBuiltInTypefaces(getApplicationContext());

        DatabaseManager.init(getApplicationContext());

        googleAnalytics = GoogleAnalytics.getInstance(this);

        UserManager.init();
        googleSilentSignIn();

        SyncManager.init(getApplicationContext());
        TimeLearntManager.init();
        AchievementChecker.init(getApplicationContext());

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    synchronized public Tracker getDefaultTracker() {
        if (tracker == null) {
            tracker = googleAnalytics.newTracker(R.xml.global_tracker);
        }
        return tracker;
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
