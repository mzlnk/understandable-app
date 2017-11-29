package pl.understandable.understandable_app.user;

import java.util.Timer;
import java.util.TimerTask;

import pl.understandable.understandable_app.user.requests.AddTimeLearnt;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class TimeLearntManager {

    public static void init() {
        Timer timer = new Timer();
        TimerTask requestsTask = new TimerTask() {
            @Override
            public void run() {
                RequestExecutor.offerRequest(new AddTimeLearnt(60000L));
            }
        };
        timer.scheduleAtFixedRate(requestsTask, 60000L, 60000L);
    }

}
