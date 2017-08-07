package pl.understandable.understandable_app.fragments.custom_words.spelling;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import pl.understandable.understandable_app.data.entities_data.custom_words_data.CustomWordsSpellingData;
import pl.understandable.understandable_app.data.entities_data.words_data.WordsSpellingData;
import pl.understandable.understandable_app.fragments.words.spelling.WordsSpellingFragment;
import pl.understandable.understandable_app.fragments.words.spelling.WordsSpellingResultCorrectWordsSummaryFragment;
import pl.understandable.understandable_app.fragments.words.spelling.WordsSpellingResultIncorrectWordsSummaryFragment;
import pl.understandable.understandable_app.utils.FragmentUtil;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomWordsSpellingResultFragment extends Fragment {

    private CustomWordsSpellingData spellingData;

    private RelativeLayout mainLayout;
    private TextView title;
    private TextView wordsSeenAmount, wordsSeenAmountInfo;
    private TextView correctAnswers, correctAnswersInfo;
    private TextView incorrectAnswers, incorrectAnswersInfo;
    private Button tryAgain;
    private TableLayout correctAnswersField, incorrectAnswersField;

    public CustomWordsSpellingResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spellingData = CustomWordsSpellingData.getSpellingData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_custom_words_spelling_result, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_custom_words_spelling_result);
        title = (TextView) rootView.findViewById(R.id.f_custom_words_spelling_result_title);
        wordsSeenAmount = (TextView) rootView.findViewById(R.id.f_custom_words_spelling_result_words_seen_amount);
        wordsSeenAmountInfo = (TextView) rootView.findViewById(R.id.f_custom_words_spelling_result_words_seen_amount_info);
        correctAnswers = (TextView) rootView.findViewById(R.id.f_custom_words_spelling_result_correct_answers_amount);
        correctAnswersInfo = (TextView) rootView.findViewById(R.id.f_custom_words_spelling_result_correct_answers_info);
        incorrectAnswers = (TextView) rootView.findViewById(R.id.f_custom_words_spelling_result_incorrect_answers_amount);
        incorrectAnswersInfo = (TextView) rootView.findViewById(R.id.f_custom_words_spelling_result_incorrect_answers_info);
        tryAgain = (Button) rootView.findViewById(R.id.f_custom_words_spelling_result_try_again);
        correctAnswersField = (TableLayout) rootView.findViewById(R.id.f_custom_words_spelling_result_correct_answers_field);
        incorrectAnswersField = (TableLayout) rootView.findViewById(R.id.f_custom_words_spelling_result_incorrect_answers_field);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        prepareViews();
    }

    private void prepareViews() {
        String wordsSeenInfo = String.valueOf(spellingData.wordsSeen.size()) + "/" + String.valueOf(spellingData.getEntities().size());
        wordsSeenAmount.setText(wordsSeenInfo);
        correctAnswers.setText(String.valueOf(spellingData.correctAnswers.size()));
        incorrectAnswers.setText(String.valueOf(spellingData.incorrectAnswers.size()));
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        title.setTypeface(Font.TYPEFACE_MONTSERRAT);
        wordsSeenAmount.setTypeface(Font.TYPEFACE_MONTSERRAT);
        wordsSeenAmountInfo.setTypeface(Font.TYPEFACE_MONTSERRAT);
        correctAnswers.setTypeface(Font.TYPEFACE_MONTSERRAT);
        correctAnswersInfo.setTypeface(Font.TYPEFACE_MONTSERRAT);
        incorrectAnswersInfo.setTypeface(Font.TYPEFACE_MONTSERRAT);
        incorrectAnswersInfo.setTypeface(Font.TYPEFACE_MONTSERRAT);
        tryAgain.setTypeface(Font.TYPEFACE_MONTSERRAT);
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
                spellingData.resetStats();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, new CustomWordsSpellingFragment(), FragmentUtil.F_CUSTOM_WORDS_SPELLING).commit();
            }
        });

        correctAnswersField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_for_fragments, new CustomWordsSpellingResultCorrectWordsSummaryFragment(), FragmentUtil.F_CUSTOM_WORDS_SPELLING_RESULT_CORRECT_WORDS_SUMMARY);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        incorrectAnswersField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_for_fragments, new CustomWordsSpellingResultIncorrectWordsSummaryFragment(), FragmentUtil.F_CUSTOM_WORDS_SPELLING_RESULT_INCORRECT_WORDS_SUMMARY);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

}
