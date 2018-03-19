package pl.understandable.understandable_app.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin Zielonka on 2018-03-19.
 */

public class GrammarInfoDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private LinearLayout mainLayout;
    private ImageView icon;
    private TextView title;

    public GrammarInfoDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.d_grammar_info);
        loadViewsFromXml();
        prepareIcon();
        prepareViews();
        setFonts();
        addListeners();
    }

    private void loadViewsFromXml() {
        mainLayout = (LinearLayout) findViewById(R.id.d_grammar_info);
        icon = (ImageView) findViewById(R.id.d_grammar_info_icon);
        title = (TextView) findViewById(R.id.d_grammar_info_title);
    }

    private void prepareViews() {
        ThemeUtil themeUtil = new ThemeUtil(context);
        if(themeUtil.isDefaultTheme()) {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_white);
            title.setTextColor(Color.BLACK);
        } else {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_dark_dark_gray);
            title.setTextColor(Color.WHITE);
        }
    }

    private void prepareIcon() {
        icon.setImageResource(R.drawable.f_app_icon);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
    }

    private void addListeners() {
        mainLayout.setOnClickListener(this);
        icon.setOnClickListener(this);
        title.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

}
