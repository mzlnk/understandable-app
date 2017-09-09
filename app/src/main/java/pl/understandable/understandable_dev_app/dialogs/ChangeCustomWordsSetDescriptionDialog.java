package pl.understandable.understandable_dev_app.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.database.repository.CustomWordsSetsRepository;
import pl.understandable.understandable_dev_app.utils.ThemeUtil;
import pl.understandable.understandable_dev_app.utils.font.Font;

/**
 * Created by Marcin Zielonka on 2017-08-19.
 */

public class ChangeCustomWordsSetDescriptionDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private TextView title, textView;
    private EditText textField;
    private Button save, cancel;

    private String oldText;
    private String id;

    public ChangeCustomWordsSetDescriptionDialog(Context context, String id, String oldText, TextView textView) {
        super(context);
        this.context = context;
        this.textView = textView;
        this.oldText = oldText;
        this.id = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.d_change_custom_words_set_description);
        loadViewsFromXml();
        prepareViews();
        setFonts();
        addListeners();
    }

    private void loadViewsFromXml() {
        title = (TextView) findViewById(R.id.d_change_custom_words_set_description_title);
        save = (Button) findViewById(R.id.d_change_custom_words_set_description_button_save);
        cancel = (Button) findViewById(R.id.d_change_custom_words_set_description_button_cancel);
        textField = (EditText) findViewById(R.id.d_change_custom_words_set_description_text_field);
    }

    private void prepareViews() {
        textField.setText(oldText);

        ThemeUtil themeUtil = new ThemeUtil(context);
        if(themeUtil.isDefaultTheme()) {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_white);
            title.setTextColor(Color.BLACK);
            save.setTextColor(Color.WHITE);
            cancel.setTextColor(Color.WHITE);
            textField.setTextColor(Color.BLACK);
            save.setBackgroundResource(R.drawable.field_rounded_light_pink);
            cancel.setBackgroundResource(R.drawable.field_rounded_pink);
        } else {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_dark_dark_gray);
            title.setTextColor(Color.WHITE);
            save.setTextColor(Color.WHITE);
            cancel.setTextColor(Color.WHITE);
            textField.setTextColor(Color.WHITE);
            save.setBackgroundResource(R.drawable.field_rounded_light_gray);
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
        save.setTypeface(typeface);
        cancel.setTypeface(typeface);
        textField.setTypeface(typeface);
    }

    private void addListeners() {
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.d_change_custom_words_set_description_button_save:
                String text = textField.getText().toString();
                if(text.length() > 200) {
                    Toast.makeText(getContext(), "Nazwa nie może być dłuższa niż 200 znaków", Toast.LENGTH_SHORT).show();
                    return;
                }
                textView.setText(text);
                CustomWordsSetsRepository.setDescription(id, text);
                Toast.makeText(getContext(), "Opis zostal zmieniony", Toast.LENGTH_SHORT).show();;
                break;
            case R.id.d_change_custom_words_set_name_button_cancel:
                break;
            default:
                break;
        }
        dismiss();
    }

}
