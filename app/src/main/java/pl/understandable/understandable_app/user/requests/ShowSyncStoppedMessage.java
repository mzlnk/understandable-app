package pl.understandable.understandable_app.user.requests;

import android.content.Context;
import android.os.Handler;

import pl.understandable.understandable_app.activities.MainActivity;
import pl.understandable.understandable_app.dialogs.user_dialogs.UserMessageWithNoIconDialog;
import pl.understandable.understandable_app.user.Request;
import pl.understandable.understandable_app.user.UserManager;

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
                final UserMessageWithNoIconDialog dialog = new UserMessageWithNoIconDialog(MainActivity.getActivity(), "Przeszedłeś w tryb offline");
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
}
