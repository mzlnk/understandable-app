package pl.understandable.understandable_app.user;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

import pl.understandable.understandable_app.user.requests.Request;

/**
 * Created by Marcin Zielonka on 2017-10-13.
 */

public class RequestExecutor {

    //private static LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<>();

    public static void offerRequest(Request request) {
        offerRequest(request, 1L);
    }

    public static void offerRequest(final Request request, long delayInMillis) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                request.executeRequest();
                //nextOffer = System.currentTimeMillis() + request.getCooldownInMillis();
            }
        }, delayInMillis);
    }



//    public static void init() {
//        Timer timer = new Timer();
//        TimerTask requestsTask = new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("[REQUEST-EXEC] now: " + (long)(System.currentTimeMillis() / 1000D) + ", nextOffer: " + (long)(nextOffer / 1000D));
//                if(System.currentTimeMillis() > nextOffer) {
//                    try {
//                        Request request = requests.take();
//                        System.out.println("[REQUEST-EXEC] Request: " + request.getClass().getName());
//                        nextOffer = System.currentTimeMillis() + request.getCooldownInMillis();
//                        request.executeRequest();
//                    } catch (InterruptedException e) {
//                        System.out.println("[REQUEST-EXEC] Try!");
//                    }
//                } else {
//                    System.out.println("[REQUEST-EXEC] cooldown!");
//                }
//            }
//        };
//        timer.scheduleAtFixedRate(requestsTask, 20L, 500L);
//    }

}
