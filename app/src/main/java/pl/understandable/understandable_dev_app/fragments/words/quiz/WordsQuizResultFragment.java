package pl.understandable.understandable_dev_app.fragments.words.quiz;

import android.graphics.Typeface;
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

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.entities_data.words_data.WordsQuizData;
import pl.understandable.understandable_dev_app.utils.FragmentUtil;
import pl.understandable.understandable_dev_app.utils.ThemeUtil;
import pl.understandable.understandable_dev_app.utils.font.Font;

import static pl.understandable.understandable_dev_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_dev_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class WordsQuizResultFragment extends Fragment {

    private WordsQuizData quizData;

    private RelativeLayout mainLayout;
    private TextView title;
    private TextView questionAmount, questionAmountInfo;
    private TextView correctAnswers, correctAnswersInfo;
    private TextView incorrectAnswers, incorrectAnswersInfo;
    private Button tryAgain;
    private TableLayout correctAnswersField, incorrectAnswersField;

    public WordsQuizResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quizData = WordsQuizData.getQuizData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_words_quiz_result, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_words_quiz_result);
        title = (TextView) rootView.findViewById(R.id.f_words_quiz_result_title);
        questionAmount = (TextView) rootView.findViewById(R.id.f_words_quiz_result_questions_amount);
        questionAmountInfo = (TextView) rootView.findViewById(R.id.f_words_quiz_result_questions_amount_info);
        correctAnswers = (TextView) rootView.findViewById(R.id.f_words_quiz_result_correct_answers_amount);
        correctAnswersInfo = (TextView) rootView.findViewById(R.id.f_words_quiz_result_correct_answers_info);
        incorrectAnswers = (TextView) rootView.findViewById(R.id.f_words_quiz_result_incorrect_answers_amount);
        incorrectAnswersInfo = (TextView) rootView.findViewById(R.id.f_words_quiz_result_incorrect_answers_info);
        tryAgain = (Button) rootView.findViewById(R.id.f_words_quiz_result_try_again);
        correctAnswersField = (TableLayout) rootView.findViewById(R.id.f_words_quiz_result_correct_answers_field);
        incorrectAnswersField = (TableLayout) rootView.findViewById(R.id.f_words_quiz_result_incorrect_answers_field);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        prepareViews();
    }

    private void prepareViews() {
        questionAmount.setText(String.valueOf(quizData.wordsSeen) + "/" + String.valueOf(quizData.getEntities().size()));
        correctAnswers.setText(String.valueOf(quizData.correctAnswers.size()));
        incorrectAnswers.setText(String.valueOf(quizData.incorrectAnswers.size()));
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        questionAmount.setTypeface(typeface);
        questionAmountInfo.setTypeface(typeface);
        correctAnswers.setTypeface(typeface);
        correctAnswersInfo.setTypeface(typeface);
        incorrectAnswers.setTypeface(typeface);
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
                WordsQuizData.getQuizData().resetStats();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, new WordsQuizFragment(), redirectTo(F_START)).commit();
            }
        });

        correctAnswersField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_for_fragments, new WordsQuizResultCorrectWordsSummaryFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        incorrectAnswersField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_for_fragments, new WordsQuizResultIncorrectWordsSummaryFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

}
