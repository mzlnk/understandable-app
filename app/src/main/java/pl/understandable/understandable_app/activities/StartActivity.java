package pl.understandable.understandable_app.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.database.repository.CustomWordsSetsRepository;
import pl.understandable.understandable_app.database.repository.IrregularVerbEntityRepository;
import pl.understandable.understandable_app.database.repository.PhraseEntityRepository;
import pl.understandable.understandable_app.database.repository.WordEntityRepository;
import pl.understandable.understandable_app.utils.GoogleAnalyticsManager;
import pl.understandable.understandable_app.utils.RateAppUtil;

import static pl.understandable.understandable_app.utils.GoogleAnalyticsManager.Action.GA_A_APP_OPEN;
import static pl.understandable.understandable_app.utils.GoogleAnalyticsManager.Category.GA_C_APP;

/**
 * Created by Marcin Zielonka on 2017-11-07.
 */

public class StartActivity extends AppCompatActivity {

    private static final long TIME_WAITING_IN_MILLIS = 3000;

    private ImageView icon;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_activity_start_screen);

        icon = (ImageView) findViewById(R.id.f_content_start_screen_icon);
        setAnimation();

        new RateAppUtil(getApplicationContext()).updateAmountOfAppOpenings();
        new LoadDataTask().execute();
        GoogleAnalyticsManager.Tracker.trackEvent(getApplication(), GA_A_APP_OPEN, GA_C_APP);
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.start);
        icon.startAnimation(anim);
    }

    private class LoadDataTask extends AsyncTask<Void, Void, Void> {

        private long timeStart;

        public LoadDataTask() {
            timeStart = System.currentTimeMillis();
        }

        @Override
        protected Void doInBackground(Void... params) {
            CustomWordsSetsRepository.init(getApplicationContext());
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            long timeFinish = System.currentTimeMillis();
            long time = timeFinish - timeStart;
            long delay = TIME_WAITING_IN_MILLIS - time;
            if(delay <= 10) {
                delay = 10;
            }

            android.os.Handler handler = new android.os.Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    StartActivity.this.startActivity(intent);
                    StartActivity.this.finish();
                }
            }, delay);

            //Toast.makeText(getApplicationContext(), "Time loading: " + String.valueOf(time) + "ms", Toast.LENGTH_LONG).show();
        }

    }

}
