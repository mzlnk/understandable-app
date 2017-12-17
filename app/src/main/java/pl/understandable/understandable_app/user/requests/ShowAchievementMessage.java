package pl.understandable.understandable_app.user.requests;

import android.content.Context;
import android.os.Handler;

import pl.understandable.understandable_app.activities.MainActivity;
import pl.understandable.understandable_app.dialogs.user_dialogs.UserMessageWithIconDialog;
import pl.understandable.understandable_app.user.data.achievements.Achievement;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class ShowAchievementMessage implements Request {

    private Achievement achievement;
    private Context context;

    public ShowAchievementMessage(Context context, Achievement achievement) {
        this.context = context;
        this.achievement = achievement;
    }

    @Override
    public void executeRequest() {
        Handler handler = new Handler(context.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                final UserMessageWithIconDialog dialog = new UserMessageWithIconDialog(MainActivity.getActivity(), "Zdobyto osiągnięcie", achievement.getResId());
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
        return 3000;
    }

}
