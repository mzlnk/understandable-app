package pl.understandable.understandable_dev_app.user;

import java.util.Timer;
import java.util.TimerTask;

import pl.understandable.understandable_dev_app.user.requests.AddTimeLearnt;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class TimeLearntHandler {

    public static void init() {
        Timer timer = new Timer();
        TimerTask requestsTask = new TimerTask() {
            @Override
            public void run() {
                RequestExecutor.offerRequest(new AddTimeLearnt(1000L));
            }
        };
        timer.scheduleAtFixedRate(requestsTask, 1000L, 1000L);
    }

}
