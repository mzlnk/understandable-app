package pl.understandable.understandable_dev_app.utils;

import android.content.Context;
import android.content.SharedPreferences;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.dialogs.RateAppInfoDialog;

/**
 * Created by Marcin on 2017-08-20.
 */

public class RateAppUtil {

    private static final int MIN_APP_OPENINGS = 6;
    private static final long MIN_TIME_IN_MILLIS_BETWEEN_RATE_APP_INFOS = 172800000;
    private static final long FIVE_MINUTES_IN_MILLS = 300000;

    public static final String RATED = "rated";
    public static final String LATER = "later";
    public static final String NO = "no";

    private Context context;
    private SharedPreferences sharedPreferences;

    public RateAppUtil(Context context) {
        this.context = context;

        String sharedPrefFileName = context.getResources().getString(R.string.sp_preferences_file_key);
        sharedPreferences = context.getSharedPreferences(sharedPrefFileName, Context.MODE_PRIVATE);
    }

    public void showRateAppDialogIfNecessary() {
        if(isMinimumAppOpenings() && isRateAppStatusSetToLater()) {
            if(isMinimumTimeBetweenRateAppInfos()) {
                updateLastRateAppInfo();
                RateAppInfoDialog dialog = new RateAppInfoDialog(context);
                dialog.show();
            }
        }
    }

    public void updateRateAppStatus(String status) {
        String sharedPrefRateAppStatusKey = context.getResources().getString(R.string.sp_rate_app_status);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(sharedPrefRateAppStatusKey, status);
        editor.commit();
    }

    public void updateAmountOfAppOpenings() {
        String sharedPrefAmountOfAppOpeningsKey = context.getResources().getString(R.string.sp_amount_of_app_openings);
        int amountOfAppOpenings = sharedPreferences.getInt(sharedPrefAmountOfAppOpeningsKey, 0);
        amountOfAppOpenings++;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(sharedPrefAmountOfAppOpeningsKey, amountOfAppOpenings);
        editor.commit();
    }

    private boolean isRateAppStatusSetToLater() {
        String sharedPrefRateAppStatusKey = context.getResources().getString(R.string.sp_rate_app_status);
        String rateAppStatus = sharedPreferences.getString(sharedPrefRateAppStatusKey, LATER);

        return rateAppStatus.equals(LATER);
    }

    private boolean isMinimumAppOpenings() {
        String sharedPrefAmountOfAppOpeningsKey = context.getResources().getString(R.string.sp_amount_of_app_openings);
        int amountOfAppOpenings = sharedPreferences.getInt(sharedPrefAmountOfAppOpeningsKey, 0);

        return amountOfAppOpenings >= MIN_APP_OPENINGS;
    }

    private boolean isMinimumTimeBetweenRateAppInfos() {
        String sharedPrefLastRateAppInfoKey  = context.getResources().getString(R.string.sp_last_rate_app_info);
        long lastRateAppInfo = sharedPreferences.getLong(sharedPrefLastRateAppInfoKey, 0);
        long now = System.currentTimeMillis();
        return (now - lastRateAppInfo >= FIVE_MINUTES_IN_MILLS);
    }

    private void updateLastRateAppInfo() {
        String sharedPrefLastRateAppInfoKey  = context.getResources().getString(R.string.sp_last_rate_app_info);
        long now = System.currentTimeMillis();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(sharedPrefLastRateAppInfoKey, now);
        editor.commit();
    }

}
