package pl.understandable.understandable_app.user.requests;

import android.content.Context;
import android.os.Handler;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.activities.MainActivity;
import pl.understandable.understandable_app.dialogs.user_dialogs.UserMessageWithIconDialog;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class ShowSyncStoppedMessage implements Request {

    private Context context;

    public ShowSyncStoppedMessage(Context context) {
        this.context = context;
    }

    @Override
    public void executeRequest() {
        Handler handler = new Handler(context.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                final UserMessageWithIconDialog dialog = new UserMessageWithIconDialog(MainActivity.getActivity(), "Przeszedłeś w tryb offline", R.drawable.d_user_message_offline);
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
