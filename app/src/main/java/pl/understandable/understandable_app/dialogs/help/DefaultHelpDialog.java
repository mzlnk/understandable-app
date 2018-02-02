package pl.understandable.understandable_app.dialogs.help;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin Zielonka on 2018-02-02.
 */

public class DefaultHelpDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private LinearLayout contentArea;
    private ImageView image;
    private TextView text;
    private Button previous, next;

    protected List<HelpContent> content = new ArrayList<>();
    private HelpContent currentContent;

    public DefaultHelpDialog(Context context) {
        super(context);
        this.context = context;
    }

    public String getHelpId() {
        return "d_help_default";
    }

    public void addContent() {
        content.add(new HelpContent(0, R.drawable.h_help, R.string.h_help, true));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.d_help);

        addContent();
        this.currentContent = content.get(0);

        loadViewsFromXml();
        prepareViews();
        setFonts();
        addListeners();
    }

    private void loadViewsFromXml() {
        contentArea = (LinearLayout) findViewById(R.id.d_help_content_area);
        previous = (Button) findViewById(R.id.d_help_button_previous);
        next = (Button) findViewById(R.id.d_help_button_next);
    }

    private void prepareViews() {
        prepareIcon();
        prepareText();

        ThemeUtil themeUtil = new ThemeUtil(context);
        if(themeUtil.isDefaultTheme()) {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_white);
            text.setTextColor(Color.BLACK);
            previous.setBackgroundResource(R.drawable.field_rounded_pink);
            next.setBackgroundResource(R.drawable.field_rounded_pink);
        } else {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_dark_dark_gray);
            text.setTextColor(Color.WHITE);
            previous.setBackgroundResource(R.drawable.field_rounded_gray);
            next.setBackgroundResource(R.drawable.field_rounded_gray);
        }
        previous.setTextColor(Color.WHITE);
        next.setTextColor(Color.WHITE);
    }

    private void prepareIcon() {
        image = new ImageView(context);
        image.setImageResource(currentContent.getImageResId());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300,550);
        int margin = context.getResources().getDimensionPixelSize(R.dimen.f_border_margin_large);
        layoutParams.setMargins(0, 0, 0, margin);
        image.setAdjustViewBounds(true);
        image.setLayoutParams(layoutParams);

        contentArea.addView(image);
    }

    private void prepareText() {
        text = new TextView(context);
        text.setText(currentContent.getTextResId());
        text.setGravity(Gravity.CENTER);
        contentArea.addView(text);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        text.setTypeface(typeface);
        previous.setTypeface(typeface);
        next.setTypeface(typeface);
    }

    private void addListeners() {
        previous.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.d_help_button_previous:
                if(currentContent.isLast()) {
                    ThemeUtil themeUtil = new ThemeUtil(context);
                    if(themeUtil.isDefaultTheme()) {
                        next.setBackgroundResource(R.drawable.field_rounded_pink);
                    } else {
                        next.setBackgroundResource(R.drawable.field_rounded_gray);
                    }
                    next.setText(R.string.button_next_page);
                }
                if(currentContent.getPos() > 0) {
                    currentContent = content.get(currentContent.getPos() - 1);
                    image.setImageResource(currentContent.getImageResId());
                    text.setText(currentContent.getTextResId());
                }
                break;
            case R.id.d_help_button_next:
                if(!currentContent.isLast()) {
                    currentContent = content.get(currentContent.getPos() + 1);
                    image.setImageResource(currentContent.getImageResId());
                    text.setText(currentContent.getTextResId());

                    if(currentContent.isLast()) {
                        ThemeUtil themeUtil = new ThemeUtil(context);
                        if(themeUtil.isDefaultTheme()) {
                            next.setBackgroundResource(R.drawable.field_rounded_light_pink);
                        } else {
                            next.setBackgroundResource(R.drawable.field_rounded_light_gray);
                        }
                        next.setText(R.string.button_ok);
                    }
                } else {
                    dismiss();
                    break;
                }
                break;
            default:
                dismiss();
                break;
        }
    }

    protected static class HelpContent {

        private int pos;
        private int imageResId;
        private int textResId;
        private boolean last = false;

        public HelpContent(int pos, int imageResId, int textResId) {
            this.pos = pos;
            this.imageResId = imageResId;
            this.textResId = textResId;
        }

        public HelpContent(int pos, int imageResId, int textResId, boolean last) {
            this(pos, imageResId, textResId);
            this.last = last;
        }

        public int getPos() {
            return pos;
        }

        public int getImageResId() {
            return imageResId;
        }

        public int getTextResId() {
            return textResId;
        }

        public boolean isLast() {
            return last;
        }

    }

}
