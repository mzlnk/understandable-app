package pl.understandable.understandable_app.dialogs;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin Zielonka on 2018-02-05.
 */

public class AnnouncementDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private TextView title;
    private WebView webView;
    private Button ok;

    public AnnouncementDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.d_announcement);
        loadViewsFromXml();
        prepareViews();
        prepareWebView();
        setFonts();
        addListeners();
    }

    private void loadViewsFromXml() {
        title = (TextView) findViewById(R.id.d_announcement_title);
        webView = (WebView) findViewById(R.id.d_announcement_webview);
        ok = (Button) findViewById(R.id.d_announcement_button_ok);
    }

    private void prepareViews() {
        ThemeUtil themeUtil = new ThemeUtil(context);
        if(themeUtil.isDefaultTheme()) {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_white);
            ok.setBackgroundResource(R.drawable.field_rounded_light_pink);
            title.setTextColor(Color.BLACK);
        } else {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_dark_dark_gray);
            title.setTextColor(Color.WHITE);
            ok.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
        ok.setTextColor(Color.WHITE);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        ok.setTypeface(typeface);
    }

    private void prepareWebView() {
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        ThemeUtil themeUtil = new ThemeUtil(context);
        String theme = themeUtil.isDefaultTheme() ? "default" : "night";

        Toast.makeText(context, theme, Toast.LENGTH_SHORT).show();
        String url = "http://dl.understandable.pl?theme=" + theme;
        webView.loadUrl(url);
    }

    private void addListeners() {
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.d_announcement_button_ok:
                dismiss();
                break;
            default:
                break;
        }
    }

}
