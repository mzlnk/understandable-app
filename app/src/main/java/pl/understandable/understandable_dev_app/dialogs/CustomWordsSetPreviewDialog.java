package pl.understandable.understandable_dev_app.dialogs;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.database.entity.CustomWordsSetEntity;
import pl.understandable.understandable_dev_app.database.repository.temp.AllCustomWordsSetsRepository;
import pl.understandable.understandable_dev_app.utils.ThemeUtil;
import pl.understandable.understandable_dev_app.utils.font.Font;
import pl.understandable.understandable_dev_app.webservice.WebService;

/**
 * Created by Marcin Zielonka on 2017-09-09.
 */

public class CustomWordsSetPreviewDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private FragmentManager fragmentManager;

    private TextView idInfo, nameInfo, descriptionInfo;
    private LinearLayout idField, nameField, descriptionField;
    private TextView id, name, description;
    private Button back, download;

    private CustomWordsSetEntity wordsSet;

    public CustomWordsSetPreviewDialog(Context context, FragmentManager fragmentManager, String id) {
        super(context);
        this.context = context;
        this.fragmentManager = fragmentManager;
        wordsSet = AllCustomWordsSetsRepository.getWordsSet(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.d_custom_words_set_preview);
        loadViewsFromXml();
        prepareViews();
        prepareData();
        setFonts();
        addListeners();
    }

    private void loadViewsFromXml() {
        idInfo = (TextView) findViewById(R.id.d_custom_words_set_preview_words_set_id_info);
        nameInfo = (TextView) findViewById(R.id.d_custom_words_set_preview_words_set_name_info);
        descriptionInfo = (TextView) findViewById(R.id.d_custom_words_set_preview_words_set_description_info);
        idField = (LinearLayout) findViewById(R.id.d_custom_words_set_preview_id_field);
        nameField = (LinearLayout) findViewById(R.id.d_custom_words_set_preview_name_field);
        descriptionField = (LinearLayout) findViewById(R.id.d_custom_words_set_preview_description_field);
        id = (TextView) findViewById(R.id.d_custom_words_set_preview_words_set_id);
        name = (TextView) findViewById(R.id.d_custom_words_set_preview_words_set_name);
        description = (TextView) findViewById(R.id.d_custom_words_set_preview_words_set_description);
        back = (Button) findViewById(R.id.d__custom_words_set_preview_button_back);
        download = (Button) findViewById(R.id.d_custom_words_set_preview_button_download);
    }

    private void prepareViews() {
        ThemeUtil themeUtil = new ThemeUtil(context);
        if(themeUtil.isDefaultTheme()) {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_white);
            idInfo.setTextColor(Color.BLACK);
            nameInfo.setTextColor(Color.BLACK);
            descriptionInfo.setTextColor(Color.BLACK);
            id.setTextColor(Color.BLACK);
            name.setTextColor(Color.BLACK);
            description.setTextColor(Color.BLACK);
            idField.setBackgroundResource(R.drawable.field_rounded_light_light_light_gray);
            nameField.setBackgroundResource(R.drawable.field_rounded_light_light_light_gray);
            descriptionField.setBackgroundResource(R.drawable.field_rounded_light_light_light_gray);
            back.setBackgroundResource(R.drawable.field_rounded_pink);
            download.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_dark_dark_gray);
            idInfo.setTextColor(Color.WHITE);
            nameInfo.setTextColor(Color.WHITE);
            descriptionInfo.setTextColor(Color.WHITE);
            id.setTextColor(Color.WHITE);
            name.setTextColor(Color.WHITE);
            description.setTextColor(Color.WHITE);
            idField.setBackgroundResource(R.drawable.field_rounded_dark_gray);
            nameField.setBackgroundResource(R.drawable.field_rounded_dark_gray);
            descriptionField.setBackgroundResource(R.drawable.field_rounded_dark_gray);
            back.setBackgroundResource(R.drawable.field_rounded_gray);
            download.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
        back.setTextColor(Color.WHITE);
        download.setTextColor(Color.WHITE);
    }

    private void prepareData() {
        id.setText(wordsSet.getId());
        name.setText(wordsSet.getName());
        description.setText(wordsSet.getDescription());
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        idInfo.setTypeface(typeface);
        nameInfo.setTypeface(typeface);
        descriptionInfo.setTypeface(typeface);
        id.setTypeface(typeface);
        name.setTypeface(typeface);
        description.setTypeface(typeface);
        back.setTypeface(typeface);
        download.setTypeface(typeface);
    }

    private void addListeners() {
        back.setOnClickListener(this);
        download.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.d__custom_words_set_preview_button_back:
                break;
            case R.id.d_custom_words_set_preview_button_download:
                new WebService.DownloadWordsSetTask(context, fragmentManager).execute(wordsSet.getId());
                break;
            default:
                break;
        }
        dismiss();
    }

}
