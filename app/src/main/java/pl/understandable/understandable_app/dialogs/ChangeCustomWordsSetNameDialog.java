package pl.understandable.understandable_app.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.database.repository.CustomWordsSetsRepository;
import pl.understandable.understandable_app.fragments.custom_words.other.CustomWordsSetsListFragment;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin on 2017-08-19.
 */

public class ChangeCustomWordsSetNameDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private TextView title, textView;
    private Button save, cancel;

    private String oldText;
    private String id;

    public ChangeCustomWordsSetNameDialog(Context context, String id, String oldText, TextView textView) {
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
        setContentView(R.layout.d_change_custom_words_set_name);
        loadViewsFromXml();
        prepareViews();
        setFonts();
        addListeners();
    }

    private void loadViewsFromXml() {
        title = (TextView) findViewById(R.id.d_change_custom_words_set_name_title);
        save = (Button) findViewById(R.id.d_change_custom_words_set_name_button_save);
        cancel = (Button) findViewById(R.id.d_remove_custom_words_set_button_no);
    }

    private void prepareViews() {
        ThemeUtil themeUtil = new ThemeUtil(context);
        if(themeUtil.isDefaultTheme()) {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_white);
            title.setTextColor(Color.BLACK);
            save.setTextColor(Color.WHITE);
            cancel.setTextColor(Color.WHITE);
            save.setBackgroundResource(R.drawable.field_rounded_light_pink);
            cancel.setBackgroundResource(R.drawable.field_rounded_pink);
        } else {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_dark_dark_gray);
            title.setTextColor(Color.WHITE);
            save.setTextColor(Color.WHITE);
            cancel.setTextColor(Color.WHITE);
            save.setBackgroundResource(R.drawable.field_rounded_light_gray);
            cancel.setBackgroundResource(R.drawable.field_rounded_gray);
        }
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        save.setTypeface(typeface);
        cancel.setTypeface(typeface);
    }

    private void addListeners() {
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.d_remove_custom_words_set_button_yes:
                CustomWordsSetsRepository.removeEntity(id);
                CustomWordsSetsListFragment fragment = new CustomWordsSetsListFragment();
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_START)).commit();
                break;
            default:
                break;
        }
        dismiss();
    }

}
