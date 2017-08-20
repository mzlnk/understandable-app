package pl.understandable.understandable_app.dialogs;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.utils.RateAppUtil;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin on 2017-08-20.
 */

public class RateAppInfoDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private TextView title, message;
    private Button yes, no, later;

    public RateAppInfoDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.d_rate_app_info);
        loadViewsFromXml();
        prepareViews();
        setFonts();
        addListeners();
    }

    private void loadViewsFromXml() {
        title = (TextView) findViewById(R.id.d_rate_app_info_title);
        message = (TextView) findViewById(R.id.d_rate_app_info_message);
        yes = (Button) findViewById(R.id.d_rate_app_info_button_yes);
        no = (Button) findViewById(R.id.d_rate_app_info_button_no);
        later = (Button) findViewById(R.id.d_rate_app_info_button_later);
    }

    private void prepareViews() {
        ThemeUtil themeUtil = new ThemeUtil(context);
        if(themeUtil.isDefaultTheme()) {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_white);
            title.setTextColor(Color.BLACK);
            message.setTextColor(Color.BLACK);
            yes.setTextColor(Color.WHITE);
            no.setTextColor(Color.WHITE);
            later.setTextColor(Color.WHITE);
            yes.setBackgroundResource(R.drawable.field_rounded_light_pink);
            no.setBackgroundResource(R.drawable.field_rounded_pink);
            later.setBackgroundResource(R.drawable.field_rounded_pink);
        } else {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_dark_dark_gray);
            title.setTextColor(Color.WHITE);
            message.setTextColor(Color.WHITE);
            yes.setTextColor(Color.WHITE);
            no.setTextColor(Color.WHITE);
            later.setTextColor(Color.WHITE);
            yes.setBackgroundResource(R.drawable.field_rounded_light_gray);
            no.setBackgroundResource(R.drawable.field_rounded_gray);
            later.setBackgroundResource(R.drawable.field_rounded_gray);
        }
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        message.setTypeface(typeface);
        yes.setTypeface(typeface);
        no.setTypeface(typeface);
        later.setTypeface(typeface);
    }

    private void addListeners() {
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        later.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        RateAppUtil util = new RateAppUtil(context);
        switch (v.getId()) {
            case R.id.d_rate_app_info_button_yes:
                util.updateRateAppStatus(RateAppUtil.RATED);
                redirectToPlayStore();
                break;
            case R.id.d_rate_app_info_button_no:
                util.updateRateAppStatus(RateAppUtil.NO);
                break;
            case R.id.d_rate_app_info_button_later:
                util.updateRateAppStatus(RateAppUtil.LATER);
                break;
            default:
                break;
        }
        dismiss();
    }

    private void redirectToPlayStore() {
        String id = context.getPackageName();
        Uri uri = Uri.parse("market://details?id=" + id);
        Intent toPlayStore = new Intent(Intent.ACTION_VIEW, uri);
        toPlayStore.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

        try {
            context.startActivity(toPlayStore);
        } catch(ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + id)));
        }
    }

}
