package pl.understandable.understandable_app.user;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Marcin Zielonka on 2017-10-13.
 */

public class RequestExecutor {

    private static LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<>();

    public static void offerRequest(Request request) {
        requests.offer(request);
    }

    public static void init() {
        Timer timer = new Timer();
        TimerTask requestsTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    requests.take().executeRequest();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.scheduleAtFixedRate(requestsTask, 20L, 1000L);
    }

}
