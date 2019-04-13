package pl.understandable.understandable_app.utils;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.activities.AdActivity;
import pl.understandable.understandable_app.activities.MainActivity;

/**
 * Created by Marcin Zielonka on 2017-08-20.
 */

public class AdUtil {

    // ca-app-pub-3940256099942544/1033173712 - test data
    private static String adId;
    private static final long COOLDOWN_IN_MILLIS = 240000;

    private static InterstitialAd ad;
    private static long lastAd = 0;
    private static int attempts = 0;

    public static void init(Context context) {
        adId = context.getString(R.string.ad_id);
        ad = new InterstitialAd(context);
        ad.setAdUnitId(adId);
        ad.loadAd(new AdRequest.Builder().build());
    }

    public static void showAd(Context context) {
        if(canBeShown()) {
            if(canCustomAdBeShown()) {
                Intent intent = new Intent(MainActivity.getActivity(), AdActivity.class);
                MainActivity.getActivity().startActivity(intent);
                lastAd = System.currentTimeMillis();
            } else if(ad.isLoaded()) {
                ad.show();
                lastAd = System.currentTimeMillis();
                reload(context);
            }
            attempts++;
        }

    }

    private static void reload(Context context) {
        ad = new InterstitialAd(context);
        ad.setAdUnitId(adId);
        ad.loadAd(new AdRequest.Builder().build());
    }

    private static boolean canBeShown() {
        long now = System.currentTimeMillis();
        return (now - lastAd > COOLDOWN_IN_MILLIS);
    }

    private static boolean canCustomAdBeShown() {
        return ((attempts + 4) % 5) == 0;
    }

}
