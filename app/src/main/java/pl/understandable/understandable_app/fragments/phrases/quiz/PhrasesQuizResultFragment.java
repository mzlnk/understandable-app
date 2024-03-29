package pl.understandable.understandable_app.fragments.phrases.quiz;

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

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.custom_words_data.CustomWordsQuizData;
import pl.understandable.understandable_app.data.entities_data.phrases.PhrasesListData;
import pl.understandable.understandable_app.data.entities_data.phrases.PhrasesQuizData;
import pl.understandable.understandable_app.data.entities_data.phrases.PhrasesRepetitionData;
import pl.understandable.understandable_app.user.ExpManager;
import pl.understandable.understandable_app.user.RequestExecutor;
import pl.understandable.understandable_app.user.data.UserStatistics;
import pl.understandable.understandable_app.user.requests.AddExp;
import pl.understandable.understandable_app.user.requests.AddTestSolved;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_PHRASES_CHOICE_CATEGORY;
import static pl.understandable.understandable_app.utils.FragmentUtil.F_PHRASES_QUIZ_RESULT;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class PhrasesQuizResultFragment extends Fragment {

    private PhrasesQuizData quizData;

    private RelativeLayout mainLayout;
    private TextView title;
    private TextView questionAmount, questionAmountInfo;
    private TextView correctAnswers, correctAnswersInfo;
    private TextView incorrectAnswers, incorrectAnswersInfo;
    private Button tryAgain;
    private TableLayout correctAnswersField, incorrectAnswersField;

    public PhrasesQuizResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quizData = PhrasesQuizData.getQuizData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_phrases_quiz_result, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();
        addUserStats();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_phrases_quiz_result);
        title = (TextView) rootView.findViewById(R.id.f_phrases_quiz_result_title);
        questionAmount = (TextView) rootView.findViewById(R.id.f_phrases_quiz_result_questions_amount);
        questionAmountInfo = (TextView) rootView.findViewById(R.id.f_phrases_quiz_result_questions_amount_info);
        correctAnswers = (TextView) rootView.findViewById(R.id.f_phrases_quiz_result_correct_answers_amount);
        correctAnswersInfo = (TextView) rootView.findViewById(R.id.f_phrases_quiz_result_correct_answers_info);
        incorrectAnswers = (TextView) rootView.findViewById(R.id.f_phrases_quiz_result_incorrect_answers_amount);
        incorrectAnswersInfo = (TextView) rootView.findViewById(R.id.f_phrases_quiz_result_incorrect_answers_info);
        tryAgain = (Button) rootView.findViewById(R.id.f_phrases_quiz_result_try_again);
        correctAnswersField = (TableLayout) rootView.findViewById(R.id.f_phrases_quiz_result_correct_answers_field);
        incorrectAnswersField = (TableLayout) rootView.findViewById(R.id.f_phrases_quiz_result_incorrect_answers_field);
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
                PhrasesQuizData.getQuizData().resetStats();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, new PhrasesQuizFragment(), redirectTo(F_PHRASES_CHOICE_CATEGORY)).commit();
            }
        });

        correctAnswersField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_for_fragments, new PhrasesQuizResultCorrectWordsSummaryFragment(), redirectTo(F_PHRASES_QUIZ_RESULT));
                transaction.commit();
            }
        });

        incorrectAnswersField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_for_fragments, new PhrasesQuizResultIncorrectWordsSummaryFragment(), redirectTo(F_PHRASES_QUIZ_RESULT));
                transaction.commit();
            }
        });
    }

    private void addUserStats() {
        if(!PhrasesQuizData.getQuizData().areStatsUpdated()) {
            int amount = PhrasesQuizData.getQuizData().wordsSeen;
            int amountCorrect = PhrasesQuizData.getQuizData().correctAnswers.size();
            RequestExecutor.offerRequest(new AddExp(getContext(), ExpManager.ExpRatio.PHRASES_QUIZ, amount, amountCorrect));
            RequestExecutor.offerRequest(new AddTestSolved(UserStatistics.PHRASES, UserStatistics.QUIZ));
            PhrasesQuizData.getQuizData().setStatsUpdated(true);
        }
    }

}
