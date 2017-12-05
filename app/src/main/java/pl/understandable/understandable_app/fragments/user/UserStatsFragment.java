package pl.understandable.understandable_app.fragments.user;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.TypedValue;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.fragments.start.StartFragment;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.user.data.User;
import pl.understandable.understandable_app.utils.ColorUtil;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;
import pl.understandable.understandable_app.webservice.DownloadFollowedWordsSetsDataTask;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static pl.understandable.understandable_app.user.data.UserStatistics.*;
import static pl.understandable.understandable_app.utils.FragmentUtil.F_USER_STATS;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class UserStatsFragment extends Fragment {

    private RelativeLayout mainLayout;
    private TextView name, level;
    private TableLayout statsTable;
    private Button achievements, followedWordsSets;

    private Button logOut;

    private int textColor;

    public UserStatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.f_user_stats, container, false);
        initColors();
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
        followedWordsSets = (Button) rootView.findViewById(R.id.f_user_stats_button_followed_words_sets);
        logOut = (Button) rootView.findViewById(R.id.f_user_stats_button_log_out);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        addStatsToTable();
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
        followedWordsSets.setTypeface(typeface);
        logOut.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            achievements.setBackgroundResource(R.drawable.field_rounded_pink);
            followedWordsSets.setBackgroundResource(R.drawable.field_rounded_pink);
            logOut.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            achievements.setBackgroundResource(R.drawable.field_rounded_gray);
            followedWordsSets.setBackgroundResource(R.drawable.field_rounded_gray);
            logOut.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
    }

    private void addListeners() {
        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAchievementsFragment fragment = new UserAchievementsFragment();
                getFragmentManager().beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_USER_STATS)).commit();
            }
        });

        followedWordsSets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadFollowedWordsSetsDataTask(getContext(), getFragmentManager()).execute();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build();
                GoogleSignInClient client = GoogleSignIn.getClient(getContext(), gso);
                client.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        UserManager.setUserStatus(UserManager.UserStatus.NO_ACCOUNT);
                        StartFragment fragment = new StartFragment();
                        getFragmentManager().beginTransaction().replace(R.id.layout_for_fragments, fragment).commit();
                    }
                });
                client.revokeAccess().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
            }
        });
    }

    private void addStatsToTable() {
        User user = UserManager.getUser();
        statsTable.addView(getPreparedRow("pobrane testy: " + user.getStats().getWordsSetsDownloaded()));
        statsTable.addView(getPreparedRow(""));
        statsTable.addView(getPreparedRow("rozwiązane testy ze słówek:"));
        statsTable.addView(getPreparedRow("jako lista: " + user.getStats().getWordsTestsSolved(LIST)));
        statsTable.addView(getPreparedRow("jako powtarzanie: " + user.getStats().getWordsTestsSolved(REPETITION)));
        statsTable.addView(getPreparedRow("jako pisownia: " + user.getStats().getWordsTestsSolved(SPELLING)));
        statsTable.addView(getPreparedRow("jako quiz: " + user.getStats().getWordsTestsSolved(QUIZ)));
        statsTable.addView(getPreparedRow(""));
        statsTable.addView(getPreparedRow("rozwiązane testy z czasowników nieregularnych:"));
        statsTable.addView(getPreparedRow("jako lista: " + user.getStats().getIrregularVerbsTestsSolved(LIST)));
        statsTable.addView(getPreparedRow("jako powtarzanie: " + user.getStats().getIrregularVerbsTestsSolved(REPETITION)));
        statsTable.addView(getPreparedRow(""));
        statsTable.addView(getPreparedRow("rozwiązane testy z wyrażeń:"));
        statsTable.addView(getPreparedRow("jako lista: " + user.getStats().getPhrasesTestsSolved(LIST)));
        statsTable.addView(getPreparedRow("jako powtarzanie: " + user.getStats().getPhrasesTestsSolved(REPETITION)));
        statsTable.addView(getPreparedRow("jako quiz: " + user.getStats().getPhrasesTestsSolved(QUIZ)));
        statsTable.addView(getPreparedRow(""));
        statsTable.addView(getPreparedRow("czas spedzony na nauce: " + user.getStats().getFormattedTimeLearnt()));
    }

    private TableRow getPreparedRow(String content) {
        TableRow row = new TableRow(getContext());
        TextView textView = new TextView(getContext());

        textView.setText(content);
        textView.setTextColor(textColor);
        textView.setTypeface(Font.TYPEFACE_MONTSERRAT);

        TableRow.LayoutParams params = new TableRow.LayoutParams(0, MATCH_PARENT, 0.5F);

        TypedValue outValue = new TypedValue();
        getResources().getValue(R.dimen.f_list_text_factor, outValue, true);
        float factor = outValue.getFloat();
        float textSizeInPixels = textView.getTextSize() * factor;
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeInPixels);

        int margin = getResources().getDimensionPixelSize(R.dimen.f_list_margin);
        params.setMargins(margin, margin, margin, margin);

        textView.setLayoutParams(params);

        row.setMeasureWithLargestChildEnabled(true);
        row.addView(textView);

        return row;
    }

    private void initColors() {
        ColorUtil colorUtil = new ColorUtil(getContext());
        textColor = colorUtil.getColor(R.attr.text_1_color);
    }

}
