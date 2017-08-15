package pl.understandable.understandable_app.fragments.grammar.fill_gap;

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
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.grammar.GrammarFillGapData;
import pl.understandable.understandable_app.utils.FragmentUtil;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin Zielonka
 */

public class GrammarFillGapResultFragment extends Fragment {

    private GrammarFillGapData fillGapData;

    private RelativeLayout mainLayout;
    private TextView title;
    private TextView wordsSeenAmount, wordsSeenAmountInfo;
    private TextView correctAnswers, correctAnswersInfo;
    private TextView incorrectAnswers, incorrectAnswersInfo;
    private Button tryAgain;
    private TableLayout correctAnswersField, incorrectAnswersField;

    public GrammarFillGapResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillGapData = GrammarFillGapData.getFillGapData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_grammar_fill_gap_result, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_grammar_fill_gap_result);
        title = (TextView) rootView.findViewById(R.id.f_grammar_fill_gap_result_title);
        wordsSeenAmount = (TextView) rootView.findViewById(R.id.f_grammar_fill_gap_result_words_seen_amount);
        wordsSeenAmountInfo = (TextView) rootView.findViewById(R.id.f_grammar_fill_gap_result_words_seen_amount_info);
        correctAnswers = (TextView) rootView.findViewById(R.id.f_grammar_fill_gap_result_correct_answers_amount);
        correctAnswersInfo = (TextView) rootView.findViewById(R.id.f_grammar_fill_gap_result_correct_answers_info);
        incorrectAnswers = (TextView) rootView.findViewById(R.id.f_grammar_fill_gap_result_incorrect_answers_amount);
        incorrectAnswersInfo = (TextView) rootView.findViewById(R.id.f_grammar_fill_gap_result_incorrect_answers_info);
        tryAgain = (Button) rootView.findViewById(R.id.f_grammar_fill_gap_result_try_again);
        correctAnswersField = (TableLayout) rootView.findViewById(R.id.f_grammar_fill_gap_result_correct_answers_field);
        incorrectAnswersField = (TableLayout) rootView.findViewById(R.id.f_grammar_fill_gap_result_incorrect_answers_field);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        prepareViews();
    }

    private void prepareViews() {
        String wordsSeenInfo = String.valueOf(fillGapData.wordsSeen.size()) + "/" + String.valueOf(fillGapData.getEntities().size());
        wordsSeenAmount.setText(wordsSeenInfo);
        correctAnswers.setText(String.valueOf(fillGapData.correctAnswers.size()));
        incorrectAnswers.setText(String.valueOf(fillGapData.incorrectAnswers.size()));
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        wordsSeenAmount.setTypeface(typeface);
        wordsSeenAmountInfo.setTypeface(typeface);
        correctAnswers.setTypeface(typeface);
        correctAnswersInfo.setTypeface(typeface);
        incorrectAnswersInfo.setTypeface(typeface);
        incorrectAnswersInfo.setTypeface(typeface);
        tryAgain.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            tryAgain.setBackgroundResource(R.drawable.field_rounded_light_pink);
            correctAnswersField.setBackgroundResource(R.drawable.field_rounded_light_light_light_gray);
            incorrectAnswersField.setBackgroundResource(R.drawable.field_rounded_light_light_light_gray);
        } else {
            tryAgain.setBackgroundResource(R.drawable.field_rounded_light_gray);
            correctAnswersField.setBackgroundResource(R.drawable.field_rounded_dark_gray);
            incorrectAnswersField.setBackgroundResource(R.drawable.field_rounded_dark_gray);

        }
    }

    private void addListeners() {
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillGapData.resetStats();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, new GrammarFillGapFragment(), FragmentUtil.F_WORDS_SPELLING).commit();
            }
        });

    }

}
