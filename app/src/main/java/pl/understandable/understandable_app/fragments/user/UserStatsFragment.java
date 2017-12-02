package pl.understandable.understandable_app.fragments.user;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin Zielonka
 */

public class UserStatsFragment extends Fragment {

    private RelativeLayout mainLayout;
    private TextView name, level;
    private TableLayout statsTable;
    private Button achievements, followedTests;
    private Button logOut;

    public UserStatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.f_user_stats, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_user_stats);
        name = (TextView) rootView.findViewById(R.id.f_user_stats_name);
        level = (TextView) rootView.findViewById(R.id.f_user_stats_level);
        statsTable = (TableLayout) rootView.findViewById(R.id.f_user_stats_stats_table);
        achievements = (Button) rootView.findViewById(R.id.f_user_stats_button_achievements);
        followedTests = (Button) rootView.findViewById(R.id.f_user_stats_button_followed_tests);
        logOut = (Button) rootView.findViewById(R.id.f_user_stats_button_log_out);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        name.setTypeface(typeface);
        level.setTypeface(typeface);
        achievements.setTypeface(typeface);
        followedTests.setTypeface(typeface);
        logOut.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            achievements.setBackgroundResource(R.drawable.field_rounded_pink);
            followedTests.setBackgroundResource(R.drawable.field_rounded_pink);
            logOut.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            achievements.setBackgroundResource(R.drawable.field_rounded_gray);
            followedTests.setBackgroundResource(R.drawable.field_rounded_gray);
            logOut.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
    }

    private void addListeners() {
        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo: code here
            }
        });

        followedTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo: code here
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo: code here
            }
        });
    }

}
