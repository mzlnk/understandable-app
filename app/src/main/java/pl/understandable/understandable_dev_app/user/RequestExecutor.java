package pl.understandable.understandable_dev_app.user;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Marcin Zielonka on 2017-10-13.
 */

public class RequestExecutor {

    private static LinkedBlockingQueue<Request> achievementRequests = new LinkedBlockingQueue<>();
    private static LinkedBlockingQueue<Request> statsRequests = new LinkedBlockingQueue<>();

    public static void offerRequest(Request request) {
        switch(request.getRequestType()) {
            case ACHIEVEMENTS:
                achievementRequests.offer(request);
                break;
            case STATS:
                statsRequests.offer(request);
                break;
        }
    }

    public static void init() {
        Timer timer = new Timer();
        TimerTask statsTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    statsRequests.take().executeRequest();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        TimerTask achievementsTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    achievementRequests.take().executeRequest();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.scheduleAtFixedRate(statsTask, 10L, 1000L);
        timer.scheduleAtFixedRate(achievementsTask, 20L, 1000L);
    }

}
