package pl.understandable.understandable_app.dialogs.help;

import android.content.Context;
import android.content.SharedPreferences;

import pl.understandable.understandable_app.R;

/**
 * Created by Marcin Zielonka on 2018-02-02.
 */

public class HelpManager {

    public static void showHelpDialog(Context context, HelpDialog helpDialog) {
        String sharedPrefFileName = context.getResources().getString(R.string.sp_preferences_file_key);
        String sharedPrefHelpKey = context.getResources().getString(R.string.sp_d_help_subkey) + helpDialog.getHelpId();
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPrefFileName, Context.MODE_PRIVATE);

        boolean helpSeen = sharedPreferences.getBoolean(sharedPrefHelpKey, false);
        if(!helpSeen) {
            helpDialog.show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(sharedPrefHelpKey, true);
            editor.commit();
        }
    }

}
