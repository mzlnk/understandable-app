package pl.understandable.understandable_app.fragments.user;

import android.graphics.Typeface;
import android.os.Bundle;
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
import android.widget.Toast;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.words.WordsLanguageCategory;
import pl.understandable.understandable_app.fragments.words.choice.WordsChoiceMethodFragment;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.user.data.User;
import pl.understandable.understandable_app.utils.ColorUtil;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.buttons.words.WordsCategoryButton;
import pl.understandable.understandable_app.utils.font.Font;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static pl.understandable.understandable_app.user.data.UserStatistics.LIST;
import static pl.understandable.understandable_app.user.data.UserStatistics.QUIZ;
import static pl.understandable.understandable_app.user.data.UserStatistics.REPETITION;
import static pl.understandable.understandable_app.user.data.UserStatistics.SPELLING;
import static pl.understandable.understandable_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class UserStatsFragment extends Fragment {

    private RelativeLayout mainLayout;
    private TableLayout statsTable;
    private TextView title;
    private Button back;

    private int textColor;

    public UserStatsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the statsTable for this fragment
        View rootView = inflater.inflate(R.layout.f_user_stats, container, false);
        loadViewsFromXml(rootView);
        initColors();
        prepareLayout();
        addListeners();
        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_user_stats);
        statsTable = (TableLayout) rootView.findViewById(R.id.f_user_stats_table);
        title = (TextView) rootView.findViewById(R.id.f_user_stats_title);
        back = (Button) rootView.findViewById(R.id.f_user_stats_button_back);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        addStatsToTable();
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

    private void addStatsToTable() {
        User user = UserManager.getUser();
        statsTable.addView(getPreparedRow("czas spedzony na nauce: " + user.getStats().getFormattedTimeLearnt()));
        statsTable.addView(getPreparedRow("pobrane testy: " + user.getStats().getWordsSetsDownloaded()));
        statsTable.addView(getPreparedRow(""));
        statsTable.addView(getPreparedRow("rozwiązane testy ze słówek:"));
        statsTable.addView(getPreparedRow(""));
        statsTable.addView(getPreparedRow("jako lista: " + user.getStats().getWordsTestsSolved(LIST)));
        statsTable.addView(getPreparedRow("jako powtarzanie: " + user.getStats().getWordsTestsSolved(REPETITION)));
        statsTable.addView(getPreparedRow("jako pisownia: " + user.getStats().getWordsTestsSolved(SPELLING)));
        statsTable.addView(getPreparedRow("jako quiz: " + user.getStats().getWordsTestsSolved(QUIZ)));
        statsTable.addView(getPreparedRow(""));
        statsTable.addView(getPreparedRow("rozwiązane testy z czasowników nieregularnych:"));
        statsTable.addView(getPreparedRow(""));
        statsTable.addView(getPreparedRow("jako lista: " + user.getStats().getIrregularVerbsTestsSolved(LIST)));
        statsTable.addView(getPreparedRow("jako powtarzanie: " + user.getStats().getIrregularVerbsTestsSolved(REPETITION)));
        statsTable.addView(getPreparedRow(""));
        statsTable.addView(getPreparedRow("rozwiązane testy z wyrażeń:"));
        statsTable.addView(getPreparedRow(""));
        statsTable.addView(getPreparedRow("jako lista: " + user.getStats().getPhrasesTestsSolved(LIST)));
        statsTable.addView(getPreparedRow("jako powtarzanie: " + user.getStats().getPhrasesTestsSolved(REPETITION)));
        statsTable.addView(getPreparedRow("jako quiz: " + user.getStats().getPhrasesTestsSolved(QUIZ)));
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
