package pl.understandable.understandable_app.user;

import android.content.Context;

import java.util.Timer;
import java.util.TimerTask;

import pl.understandable.understandable_app.user.requests.CheckAchievements;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class AchievementChecker {

    public static void init(final Context context) {
        Timer timer = new Timer();
        TimerTask requestsTask = new TimerTask() {
            @Override
            public void run() {
                RequestExecutor.offerRequest(new CheckAchievements(context));
            }
        };
        timer.scheduleAtFixedRate(requestsTask, 7500L, 1000L);
    }

}
