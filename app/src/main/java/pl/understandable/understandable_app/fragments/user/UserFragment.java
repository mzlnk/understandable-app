package pl.understandable.understandable_app.fragments.user;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.dialogs.help.HelpManager;
import pl.understandable.understandable_app.dialogs.help.ProfileHelpDialog;
import pl.understandable.understandable_app.dialogs.user_dialogs.ChangeUserNameDialog;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.user.data.User;
import pl.understandable.understandable_app.user.data.enums.UserTitle;
import pl.understandable.understandable_app.user.data.enums.buttons_data.UserOptions;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.buttons.user.UserButton;
import pl.understandable.understandable_app.utils.font.Font;
import pl.understandable.understandable_app.webservice.DownloadFollowedWordsSetsDataTask;
import pl.understandable.understandable_app.webservice.LogOutTask;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_USER;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class UserFragment extends Fragment {

    private RelativeLayout mainLayout;
    private TextView mainTitle, name, title, nameInfo, titleInfo, levelInfo, levelProgressInfo;
    private LinearLayout nameField, titleField;
    private ProgressBar levelProgress;
    private TableLayout optionsTable;
    private UserButton achievements, stats, followedWordsSets;
    private Button logOut;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.f_user, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        HelpManager.showHelpDialog(getContext(), new ProfileHelpDialog(getContext()));

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_user);
        mainTitle = (TextView) rootView.findViewById(R.id.f_user_main_title);
        name = (TextView) rootView.findViewById(R.id.f_user_name);
        title = (TextView) rootView.findViewById(R.id.f_user_title);
        nameInfo = (TextView) rootView.findViewById(R.id.f_user_name_info);
        titleInfo = (TextView) rootView.findViewById(R.id.f_user_title_info);
        levelInfo = (TextView) rootView.findViewById(R.id.f_user_level_info);
        levelProgressInfo = (TextView) rootView.findViewById(R.id.f_user_level_progress_info);
        nameField = (LinearLayout) rootView.findViewById(R.id.f_user_name_field);
        titleField = (LinearLayout) rootView.findViewById(R.id.f_user_title_field);
        levelProgress = (ProgressBar) rootView.findViewById(R.id.f_user_level_progress);
        optionsTable = (TableLayout) rootView.findViewById(R.id.f_user_options_table);
        logOut = (Button) rootView.findViewById(R.id.f_user_button_log_out);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareStats();
        prepareButtons();
        prepareFields();
        addButtonsToOptionsTable();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        mainTitle.setTypeface(typeface);
        name.setTypeface(typeface);
        title.setTypeface(typeface);
        nameInfo.setTypeface(typeface);
        titleInfo.setTypeface(typeface);
        levelInfo.setTypeface(typeface);
        levelProgressInfo.setTypeface(typeface);
        logOut.setTypeface(typeface);
    }

    private void prepareFields() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            nameField.setBackgroundResource(R.drawable.field_rounded_light_light_light_gray);
            titleField.setBackgroundResource(R.drawable.field_rounded_light_light_light_gray);
        } else {
            nameField.setBackgroundResource(R.drawable.field_rounded_dark_gray);
            titleField.setBackgroundResource(R.drawable.field_rounded_dark_gray);
        }
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            logOut.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            logOut.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }

        achievements = new UserButton(getContext(), UserOptions.ACHIEVEMENTS) {
            @Override
            protected void setOnClickListener() {
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fragmentManager = getFragmentManager();
                        UserAchievementsFragment fragment = new UserAchievementsFragment();
                        fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_USER)).commit();
                    }
                });
            }
        };

        stats = new UserButton(getContext(), UserOptions.STATS) {
            @Override
            protected void setOnClickListener() {
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fragmentManager = getFragmentManager();
                        UserStatsFragment fragment = new UserStatsFragment();
                        fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_USER)).commit();
                    }
                });
            }
        };

        followedWordsSets = new UserButton(getContext(), UserOptions.FOLLOWED_WORDS_SETS) {
            @Override
            protected void setOnClickListener() {
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DownloadFollowedWordsSetsDataTask(getContext(), getFragmentManager()).execute();
                    }
                });
            }
        };

    }

    private void addButtonsToOptionsTable() {
        TableRow imageRow = new TableRow(getContext());
        TableRow textRow = new TableRow(getContext());


        imageRow.addView(stats.getImage());
        imageRow.addView(achievements.getImage());
        imageRow.addView(followedWordsSets.getImage());

        textRow.addView(stats.getText());
        textRow.addView(achievements.getText());
        textRow.addView(followedWordsSets.getText());

        optionsTable.addView(imageRow);
        optionsTable.addView(textRow);
    }

    private void addListeners() {
        nameField.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ChangeUserNameDialog dialog = new ChangeUserNameDialog(getContext(), name.getText().toString(), name);
                dialog.show();
                return true;
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LogOutTask(getContext(), getFragmentManager()).execute();
            }
        });

    }

    private void prepareStats() {
        User user = UserManager.getUser();

        name.setText(user.getName());
        levelInfo.setText("poziom: " + String.valueOf(user.getLevel()));
        title.setText(UserTitle.getTitleByLevel(user.getLevel()).getTitle());

        int level = user.getLevel();
        double progress = (double)(user.getExp() - user.getTotalExpForLevel(level - 1)) / (double)(user.getExpForLevel(level));
        System.out.println("[USER-EXP] progress: " + progress);
        progress = Math.round(progress * 10000D) / 100D;
        System.out.println("[USER-EXP] lvl: " + level + ", totalExpForLvl-1: " + user.getTotalExpForLevel(level-1) + ", exp: " + user.getExp() + ", expForLvl: " + user.getExpForLevel(level));
        levelProgressInfo.setText(String.valueOf(progress) + " %");

        levelProgress.setProgress((int)(progress * 100D));

        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(!themeUtil.isDefaultTheme()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                levelProgress.setProgressDrawable(getContext().getDrawable(R.drawable.f_user_progress_bar_theme_night));
            } else {
                levelProgress.setProgressDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.f_user_progress_bar_theme_night, null));
            }
        }
    }

}
