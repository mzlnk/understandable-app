package pl.understandable.understandable_dev_app.user;

import java.util.Timer;
import java.util.TimerTask;

import pl.understandable.understandable_dev_app.user.requests.CheckAchievements;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class AchievementChecker {

    public static void init() {
        Timer timer = new Timer();
        TimerTask requestsTask = new TimerTask() {
            @Override
            public void run() {
                RequestExecutor.offerRequest(new CheckAchievements());
            }
        };
        timer.scheduleAtFixedRate(requestsTask, 500L, 1000L);
    }

}
