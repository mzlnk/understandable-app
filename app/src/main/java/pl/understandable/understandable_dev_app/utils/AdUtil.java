package pl.understandable.understandable_dev_app.utils;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by Marcin Zielonka on 2017-08-20.
 */

public class AdUtil {

    private static final String ID = "ca-app-pub-3940256099942544/1033173712";
    private static final long COOLDOWN_IN_MILLIS = 300000;

    private static Context context;
    private static InterstitialAd ad;
    private static long lastAd = 0;

    public static void init(Context context) {
        AdUtil.context = context;
        ad = new InterstitialAd(context);
        ad.setAdUnitId(ID);
        ad.loadAd(new AdRequest.Builder().build());
    }

    public static void showAd() {
        if(canBeShowed() && ad.isLoaded()) {
            ad.show();
            lastAd = System.currentTimeMillis();
        }
        reload();
    }

    private static void reload() {
        ad = new InterstitialAd(context);
        ad.setAdUnitId(ID);
        ad.loadAd(new AdRequest.Builder().build());
    }

    private static boolean canBeShowed() {
        long now = System.currentTimeMillis();
        return (now - lastAd > COOLDOWN_IN_MILLIS);
    }

}
