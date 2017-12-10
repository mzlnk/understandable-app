package pl.understandable.understandable_app.dialogs.user_dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.database.entity.CustomWordsSetEntity;
import pl.understandable.understandable_app.database.repository.temp.AllCustomWordsSetsRepository;
import pl.understandable.understandable_app.user.data.achievements.Achievement;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;
import pl.understandable.understandable_app.webservice.DownloadWordsSetTask;

/**
 * Created by Marcin Zielonka on 2017-12-10.
 */

public class UserAchievementPreviewDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private Achievement achievement;

    private TextView nameInfo, descriptionInfo;
    private LinearLayout iconArea, nameField, descriptionField;
    private TextView name, description;
    private Button back;

    public UserAchievementPreviewDialog(Context context, Achievement achievement) {
        super(context);
        this.context = context;
        this.achievement = achievement;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.d_user_achievement_preview);
        loadViewsFromXml();
        prepareViews();
        prepareData();
        setFonts();
        addListeners();
    }

    private void loadViewsFromXml() {
        iconArea = (LinearLayout) findViewById(R.id.d_user_achievement_preview_icon_area);
        nameInfo = (TextView) findViewById(R.id.d_user_achievement_preview_achievement_name_info);
        descriptionInfo = (TextView) findViewById(R.id.d_user_achievement_preview_achievement_description_info);
        nameField = (LinearLayout) findViewById(R.id.d_user_achievement_preview_achievement_name_field);
        descriptionField = (LinearLayout) findViewById(R.id.d_user_achievement_preview_achievement_description_field);
        name = (TextView) findViewById(R.id.d_user_achievement_preview_achievement_name);
        description = (TextView) findViewById(R.id.d_user_achievement_preview_achievement_description);
        back = (Button) findViewById(R.id.d_user_achievement_preview_button_back);
    }

    private void prepareViews() {
        ThemeUtil themeUtil = new ThemeUtil(context);
        if(themeUtil.isDefaultTheme()) {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_white);
            nameInfo.setTextColor(Color.BLACK);
            descriptionInfo.setTextColor(Color.BLACK);
            name.setTextColor(Color.BLACK);
            description.setTextColor(Color.BLACK);
            nameField.setBackgroundResource(R.drawable.field_rounded_light_light_light_gray);
            descriptionField.setBackgroundResource(R.drawable.field_rounded_light_light_light_gray);
            back.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            getWindow().setBackgroundDrawableResource(R.drawable.field_rounded_dark_dark_gray);
            nameInfo.setTextColor(Color.WHITE);
            descriptionInfo.setTextColor(Color.WHITE);
            name.setTextColor(Color.WHITE);
            description.setTextColor(Color.WHITE);
            nameField.setBackgroundResource(R.drawable.field_rounded_dark_gray);
            descriptionField.setBackgroundResource(R.drawable.field_rounded_dark_gray);
            back.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
        back.setTextColor(Color.WHITE);

        prepareIcon();
    }

    private void prepareData() {
        name.setText(achievement.isAchieved() ? achievement.getName() : "???");
        description.setText(achievement.isAchieved() ? achievement.getDescription() : "???");


    }

    private void prepareIcon() {
        ImageView icon = new ImageView(context);
        if(achievement.isAchieved()) {
            icon.setImageResource(achievement.getResId());
        } else {
            icon.setImageResource(R.drawable.f_user_achievements_not_achieved);
            final float ITEM_NOT_CHOSEN = 0.35F;
            icon.setAlpha(ITEM_NOT_CHOSEN);
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(350, 350);
        icon.setLayoutParams(layoutParams);

        iconArea.addView(icon);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        nameInfo.setTypeface(typeface);
        descriptionInfo.setTypeface(typeface);
        name.setTypeface(typeface);
        description.setTypeface(typeface);
        back.setTypeface(typeface);
    }

    private void addListeners() {
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.d_user_achievement_preview_button_back:
                break;
            default:
                break;
        }
        dismiss();
    }

}
