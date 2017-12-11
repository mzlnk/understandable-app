package pl.understandable.understandable_app.dialogs.user_dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin Zielonka on 2017-12-11.
 */

public class UserMessageWithNoIconDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private TextView text;

    private String content;

    public UserMessageWithNoIconDialog(Context context, String content) {
        super(context);
        this.context = context;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.d_user_message_with_no_icon);
        loadViewsFromXml();
        prepareViews();
        setFonts();
    }

    private void loadViewsFromXml() {
        text = (TextView) findViewById(R.id.d_user_message_with_no_icon_text);
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
        getWindow().setAttributes(wlp);
    }

    @Override
    public void onClick(View v) {}

}
