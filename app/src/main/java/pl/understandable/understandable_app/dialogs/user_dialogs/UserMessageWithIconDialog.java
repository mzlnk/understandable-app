package pl.understandable.understandable_app.dialogs.user_dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.activities.MainActivity;
import pl.understandable.understandable_app.user.RequestExecutor;
import pl.understandable.understandable_app.user.requests.ChangeUserName;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin Zielonka on 2017-12-11.
 */

public class UserMessageWithIconDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private TextView text;
    private LinearLayout iconArea;

    private String content;
    private int iconResId;

    public UserMessageWithIconDialog(Context context, String content, int iconResId) {
        super(context);
        this.context = context;
        this.content = content;
        this.iconResId = iconResId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.d_user_message_with_icon);
        loadViewsFromXml();
        prepareViews();
        setFonts();
    }

    private void loadViewsFromXml() {
        text = (TextView) findViewById(R.id.d_user_message_with_icon_text);
        iconArea = (LinearLayout) findViewById(R.id.d_user_message_with_icon_icon_area);
    }

    private void prepareViews() {
        text.setText(content);

        ThemeUtil themeUtil = new ThemeUtil(context);
        if(themeUtil.isDefaultTheme()) {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_white);
            text.setTextColor(Color.BLACK);
        } else {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_dark_dark_gray);
            text.setTextColor(Color.WHITE);
        }

        ImageView icon = new ImageView(getContext());
        icon.setImageResource(iconResId);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        icon.setLayoutParams(params);

        iconArea.addView(icon);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        text.setTypeface(typeface);
    }

    private void prepareWindow() {
        getWindow().setDimAmount(0F);

        WindowManager.LayoutParams wlp = getWindow().getAttributes();
        wlp.gravity = Gravity.TOP;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        this.getWindow().setAttributes(wlp);
    }

    @Override
    public void onClick(View v) {}

}
