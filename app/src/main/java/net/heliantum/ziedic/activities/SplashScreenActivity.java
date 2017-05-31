package net.heliantum.ziedic.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import net.heliantum.ziedic.R;

public class SplashScreenActivity extends AppCompatActivity {

    private ProgressBar loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_activity_start_screen);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //toolbar.setTitleTextColor(Color.BLACK);

        loadingBar = (ProgressBar) findViewById(R.id.f_loading_progress_bar);

        ActivityStarter starter = new ActivityStarter();
        starter.start();
    }

    private class ActivityStarter extends Thread {

        @Override
        public void run() {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(SplashScreenActivity.this, NavigationActivity.class);
            SplashScreenActivity.this.startActivity(intent);
            SplashScreenActivity.this.finish();
        }
    }

}
