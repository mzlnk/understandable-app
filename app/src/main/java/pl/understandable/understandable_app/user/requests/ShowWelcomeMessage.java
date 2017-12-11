package pl.understandable.understandable_app.user.requests;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import pl.understandable.understandable_app.dialogs.user_dialogs.UserMessageWithNoIconDialog;
import pl.understandable.understandable_app.user.Request;

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
                final UserMessageWithNoIconDialog dialog = new UserMessageWithNoIconDialog(context, "Witaj ponownie");
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
