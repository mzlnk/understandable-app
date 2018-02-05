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

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.user.data.User;
import pl.understandable.understandable_app.user.data.enums.buttons_data.UserStats;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.buttons.user_stats.UserStatsButton;
import pl.understandable.understandable_app.utils.font.Font;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class UserStatsFragment extends Fragment {

    private RelativeLayout mainLayout;
    private TableLayout generalStatsTable, wordsTestsStatsTable, irregularVerbsTestsStatsTable, phrasesTestsStatsTable, customWordsTestsStatsTable;
    private TextView title, wordsTestsStatsSubtitle, irregularVerbsTestsStatsSubtitle, phrasesTestsStatsSubtitle, customWordsTestsStatsSubtitle;
    private Button back;

    public UserStatsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the generalStatsTable for this fragment
        View rootView = inflater.inflate(R.layout.f_user_stats, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_user_stats);
        generalStatsTable = (TableLayout) rootView.findViewById(R.id.f_user_stats_general_stats_table);
        wordsTestsStatsTable = (TableLayout) rootView.findViewById(R.id.f_user_stats_words_tests_stats_table);
        irregularVerbsTestsStatsTable = (TableLayout) rootView.findViewById(R.id.f_user_stats_irregular_verbs_tests_stats_table);
        phrasesTestsStatsTable = (TableLayout) rootView.findViewById(R.id.f_user_stats_phrases_tests_stats_table);
        customWordsTestsStatsTable = (TableLayout) rootView.findViewById(R.id.f_user_stats_custom_words_tests_stats_table);
        wordsTestsStatsSubtitle = (TextView) rootView.findViewById(R.id.f_user_stats_words_tests_stats_subtitle);
        irregularVerbsTestsStatsSubtitle = (TextView) rootView.findViewById(R.id.f_user_stats_irregular_verbs_tests_stats_subtitle);
        phrasesTestsStatsSubtitle = (TextView) rootView.findViewById(R.id.f_user_stats_phrases_tests_stats_subtitle);
        customWordsTestsStatsSubtitle = (TextView) rootView.findViewById(R.id.f_user_stats_custom_words_tests_stats_subtitle);
        title = (TextView) rootView.findViewById(R.id.f_user_stats_title);
        back = (Button) rootView.findViewById(R.id.f_user_stats_button_back);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        addStatsToTable();
    }

    private void addStatsToTable() {
        addGeneralStatsToTable();
        addTestsStatsToTable();
    }

    private void addGeneralStatsToTable() {
        User user = UserManager.getUser();
        UserStatsButton timeLearnt = new UserStatsButton(getContext(), UserStats.TIME_LEARNT, user.getStats().getFormattedTimeLearnt(), true);
        UserStatsButton wordsSetsDownloaded = new UserStatsButton(getContext(), UserStats.WORDS_SETS_DOWNLOADED, String.valueOf(user.getStats().getWordsSetsDownloaded()), true);

        TableRow iconRow = new TableRow(getContext());
        iconRow.addView(timeLearnt.getImage());
        iconRow.addView(wordsSetsDownloaded.getImage());

        TableRow valueRow = new TableRow(getContext());
        valueRow.addView(timeLearnt.getText2());
        valueRow.addView(wordsSetsDownloaded.getText2());

        TableRow infoRow = new TableRow(getContext());
        infoRow.addView(timeLearnt.getText());
        infoRow.addView(wordsSetsDownloaded.getText());

        generalStatsTable.addView(iconRow);
        generalStatsTable.addView(valueRow);
        generalStatsTable.addView(infoRow);
    }

    private void addTestsStatsToTable() {
        User user = UserManager.getUser();
        TableRow wordsIconRow = new TableRow(getContext());
        TableRow wordsValueRow = new TableRow(getContext());
        for(int i = 0; i < 4; i++) {
            UserStatsButton stat = new UserStatsButton(getContext(), UserStats.getEnumByPosInUserStatistics(i), String.valueOf(user.getStats().getWordsTestsSolved(i)), false);
            wordsIconRow.addView(stat.getImage());
            wordsValueRow.addView(stat.getText2());
        }
        wordsTestsStatsTable.addView(wordsIconRow);
        wordsTestsStatsTable.addView(wordsValueRow);

        TableRow irregularVerbsIconRow = new TableRow(getContext());
        TableRow irregularVerbsValueRow = new TableRow(getContext());
        irregularVerbsIconRow.addView(new TextView(getContext()));
        irregularVerbsValueRow.addView(new TextView(getContext()));
        for(int i = 0; i < 2; i++) {
            UserStatsButton stat = new UserStatsButton(getContext(), UserStats.getEnumByPosInUserStatistics(i), String.valueOf(user.getStats().getIrregularVerbsTestsSolved(i)), false);
            irregularVerbsIconRow.addView(stat.getImage());
            irregularVerbsValueRow.addView(stat.getText2());
        }
        irregularVerbsIconRow.addView(new TextView(getContext()));
        irregularVerbsValueRow.addView(new TextView(getContext()));
        irregularVerbsTestsStatsTable.addView(irregularVerbsIconRow);
        irregularVerbsTestsStatsTable.addView(irregularVerbsValueRow);

        TableRow phrasesIconRow = new TableRow(getContext());
        TableRow phrasesValueRow = new TableRow(getContext());
        phrasesIconRow.addView(new TextView(getContext()));
        phrasesValueRow.addView(new TextView(getContext()));
        for(int i = 0; i < 3; i++) {
            UserStatsButton stat = new UserStatsButton(getContext(), UserStats.getEnumByPosInUserStatistics(i), String.valueOf(user.getStats().getPhrasesTestsSolved(i)), false);
            phrasesIconRow.addView(stat.getImage());
            phrasesValueRow.addView(stat.getText2());
        }
        phrasesIconRow.addView(new TextView(getContext()));
        phrasesValueRow.addView(new TextView(getContext()));
        phrasesTestsStatsTable.addView(phrasesIconRow);
        phrasesTestsStatsTable.addView(phrasesValueRow);

        TableRow customWordsIconRow = new TableRow(getContext());
        TableRow customWordsValueRow = new TableRow(getContext());
        customWordsIconRow.addView(new TextView(getContext()));
        customWordsValueRow.addView(new TextView(getContext()));
        for(int i = 0; i < 4; i++) {
            UserStatsButton stat = new UserStatsButton(getContext(), UserStats.getEnumByPosInUserStatistics(i), String.valueOf(user.getStats().getCustomWordsTestsSolved(i)), false);
            customWordsIconRow.addView(stat.getImage());
            customWordsValueRow.addView(stat.getText2());
        }
        customWordsIconRow.addView(new TextView(getContext()));
        customWordsValueRow.addView(new TextView(getContext()));
        customWordsTestsStatsTable.addView(customWordsIconRow);
        customWordsTestsStatsTable.addView(customWordsValueRow);
    }

    public void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    public void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        wordsTestsStatsSubtitle.setTypeface(typeface);
        irregularVerbsTestsStatsSubtitle.setTypeface(typeface);
        phrasesTestsStatsSubtitle.setTypeface(typeface);
        customWordsTestsStatsSubtitle.setTypeface(typeface);
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

    private void addListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                UserFragment fragment = new UserFragment();
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_START)).commit();
            }
        });
    }

}
