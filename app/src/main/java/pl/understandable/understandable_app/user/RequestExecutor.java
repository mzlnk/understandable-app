package pl.understandable.understandable_app.user;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

import pl.understandable.understandable_app.user.requests.Request;

/**
 * Created by Marcin Zielonka on 2017-10-13.
 */

public class RequestExecutor {

    private static LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<>();

    private static long nextOffer = 0;

    public static void offerRequest(Request request) {
        requests.offer(request);
    }

    public static void offerRequest(Request request, boolean instant) {
        offerRequest(request, true, 1L);
    }

    public static void offerRequest(final Request request, boolean instant, long delayInMillis) {
        if(instant) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    request.executeRequest();
                    nextOffer = System.currentTimeMillis() + request.getCooldownInMillis();
                }
            }, delayInMillis);
        } else {
            offerRequest(request);
        }
    }


    public static void init() {
        Timer timer = new Timer();
        TimerTask requestsTask = new TimerTask() {
            @Override
            public void run() {
                if(System.currentTimeMillis() < nextOffer) {
                    return;
                }
                try {
                    Request request = requests.take();
                    request.executeRequest();
                    nextOffer = System.currentTimeMillis() + request.getCooldownInMillis();
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }

            }
        };
        timer.scheduleAtFixedRate(requestsTask, 20L, 500L);
    }

}
