package pl.understandable.understandable_app.user.requests;

import android.content.Context;
import android.os.Handler;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.activities.MainActivity;
import pl.understandable.understandable_app.dialogs.user_dialogs.UserMessageWithIconDialog;

/**
 * Created by Marcin Zielonka on 2017-12-17.
 */

public class ShowLevelUpMessage implements Request {

    private Context context;
    private int previousLevel;
    private int currentLevel;

    public ShowLevelUpMessage(Context context, int previousLevel, int currentLevel) {
        this.context = context;
        this.previousLevel = previousLevel;
        this.currentLevel = currentLevel;
    }

    @Override
    public void executeRequest() {
        Handler handler = new Handler(context.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String message = "Zdobyto kolejny poziom: " + String.valueOf(currentLevel);
                final UserMessageWithIconDialog dialog = new UserMessageWithIconDialog(MainActivity.getActivity(), message, R.drawable.d_user_message_level_up);
                dialog.show();

                Handler postHandler = new Handler();
                postHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                }, 2000L);
            }
        });

    }

    @Override
    public long getCooldownInMillis() {
        return 3000L;
    }

}
