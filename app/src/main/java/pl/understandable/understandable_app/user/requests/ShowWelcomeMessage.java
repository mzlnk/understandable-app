package pl.understandable.understandable_app.user.requests;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.WindowManager;

import pl.understandable.understandable_app.activities.MainActivity;
import pl.understandable.understandable_app.dialogs.user_dialogs.UserMessageWithNoIconDialog;
import pl.understandable.understandable_app.user.Request;
import pl.understandable.understandable_app.user.UserManager;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class ShowWelcomeMessage implements Request {

    private Context context;

    public ShowWelcomeMessage(Context context) {
        this.context = context;
    }

    @Override
    public void executeRequest() {
        Handler handler = new Handler(context.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String name = UserManager.getUser().getName();
                String firstName = (!name.split(" ")[0].isEmpty()) ? name.split(" ")[0] : name;
                final UserMessageWithNoIconDialog dialog = new UserMessageWithNoIconDialog(MainActivity.getActivity(), "Witaj ponownie " + firstName);
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
