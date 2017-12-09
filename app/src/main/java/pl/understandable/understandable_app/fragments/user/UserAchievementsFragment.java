package pl.understandable.understandable_app.fragments.user;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.user.data.achievements.Achievement;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.buttons.achievements.AchievementButton;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin Zielonka
 */

public class UserAchievementsFragment extends Fragment {

    private RelativeLayout mainLayout;
    private TableLayout achievementsTable;
    private TextView title;
    private Button back;

    private List<AchievementButton> achievements = new ArrayList<>();

    public UserAchievementsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the categoriesLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_user_achievements, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();
        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_user_achievements);
        achievementsTable = (TableLayout) rootView.findViewById(R.id.f_user_achievements_achievements_table);
        title = (TextView) rootView.findViewById(R.id.f_user_achievements_title);
        back = (Button) rootView.findViewById(R.id.f_user_achievements_button_back);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        initCategoriesButtons();
        addCategoryButtonsToTable();
    }

    public void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    public void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        back.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            back.setBackgroundResource(R.drawable.field_rounded_pink);
        } else {
            back.setBackgroundResource(R.drawable.field_rounded_gray);
        }
    }

    private void initCategoriesButtons() {
        for(Achievement achievement : UserManager.getUser().getAllAchievements().values()) {
            achievements.add(new AchievementButton(getContext(), achievement));
        }
    }

    private void addCategoryButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (AchievementButton achievementButton : achievements) {
            currentImageRow.addView(achievementButton.getImage());
            currentTextRow.addView(achievementButton.getText());
            if (x == 3) {
                achievementsTable.addView(currentImageRow);
                achievementsTable.addView(currentTextRow);
                currentImageRow = new TableRow(getContext());
                currentTextRow = new TableRow(getContext());
                x = 0;
            } else {
                x++;
            }
        }
        if (x != 0) {
            achievementsTable.addView(currentImageRow);
            achievementsTable.addView(currentTextRow);
        }
    }

    private void addListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                UserFragment fragment = new UserFragment();
                manager.beginTransaction().replace(R.id.layout_for_fragments, fragment).commit();
            }
        });
    }

}
