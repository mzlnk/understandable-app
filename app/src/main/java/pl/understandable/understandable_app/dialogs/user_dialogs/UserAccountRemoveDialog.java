package pl.understandable.understandable_app.dialogs.user_dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;
import pl.understandable.understandable_app.webservice.RemoveUserAccountTask;

/**
 * Created by Marcin Zielonka on 2018-03-25.
 */

public class UserAccountRemoveDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private FragmentManager fragmentManager;

    private TextView title, info;
    private EditText textField;
    private Button approve, cancel;

    public UserAccountRemoveDialog(Context context, FragmentManager fragmentManager) {
        super(context);
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.d_user_account_remove);
        loadViewsFromXml();
        prepareViews();
        setFonts();
        addListeners();
    }

    private void loadViewsFromXml() {
        title = (TextView) findViewById(R.id.d_user_account_remove_title);
        info = (TextView) findViewById(R.id.d_user_account_remove_info);
        approve = (Button) findViewById(R.id.d_user_account_remove_button_approve);
        cancel = (Button) findViewById(R.id.d_user_account_remove_button_cancel);
        textField = (EditText) findViewById(R.id.d_user_account_remove_text_field);
    }

    private void prepareViews() {
        ThemeUtil themeUtil = new ThemeUtil(context);
        if(themeUtil.isDefaultTheme()) {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_white);
            title.setTextColor(Color.BLACK);
            info.setTextColor(Color.BLACK);
            approve.setTextColor(Color.WHITE);
            cancel.setTextColor(Color.WHITE);
            textField.setTextColor(Color.BLACK);
            approve.setBackgroundResource(R.drawable.field_rounded_light_pink);
            cancel.setBackgroundResource(R.drawable.field_rounded_pink);
        } else {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_dark_dark_gray);
            title.setTextColor(Color.WHITE);
            info.setTextColor(Color.WHITE);
            approve.setTextColor(Color.WHITE);
            cancel.setTextColor(Color.WHITE);
            textField.setTextColor(Color.WHITE);
            approve.setBackgroundResource(R.drawable.field_rounded_light_gray);
            cancel.setBackgroundResource(R.drawable.field_rounded_gray);
        }
        if(themeUtil.isNightTheme()) {
            Drawable bottomLine = textField.getBackground();
            bottomLine.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            if(Build.VERSION.SDK_INT > 16) {
                textField.setBackground(bottomLine);
            } else {
                textField.setBackgroundDrawable(bottomLine);
            }
        }
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        approve.setTypeface(typeface);
        cancel.setTypeface(typeface);
        textField.setTypeface(typeface);
    }

    private void addListeners() {
        approve.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.d_user_account_remove_button_approve:
                String text = textField.getText().toString();
                if(text.equalsIgnoreCase("usuń")) {
                    new RemoveUserAccountTask(getContext(), fragmentManager).execute();
                    break;
                } else {
                    return;
                }
            default:
                break;
        }
        dismiss();
    }

}
