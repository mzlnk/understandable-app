package net.heliantum.ziedic.fragments;


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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.data.QuizData;

import static net.heliantum.ziedic.fragments.QuizResultWordsSummaryFragment.CORRECT_WORDS_SUMMARY;
import static net.heliantum.ziedic.fragments.QuizResultWordsSummaryFragment.INCORRECT_WORDS_SUMMARY;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizResultFragment extends Fragment {

    private View rootView;
    private RelativeLayout mainLayout;

    private TextView title;
    private TextView questionAmount, questionAmountInfo;
    private TextView correctAnswers, correctAnswersInfo;
    private TextView incorrectAnswers, incorrectAnswersInfo;
    private Button tryAgain;
    private ImageView correctAnswersImg, incorrectAnswersImg;

    public QuizResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_quiz_result, container, false);
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.fragment_quiz_result_fragment_layout);

        title = (TextView) rootView.findViewById(R.id.f_quiz_result_title);
        questionAmount = (TextView) rootView.findViewById(R.id.f_quiz_result_questions_amount);
        questionAmountInfo = (TextView) rootView.findViewById(R.id.f_quiz_result_questions_amount_info);
        correctAnswers = (TextView) rootView.findViewById(R.id.f_quiz_result_correct_answers);
        correctAnswersInfo = (TextView) rootView.findViewById(R.id.f_quiz_result_correct_answers_info);
        incorrectAnswers = (TextView) rootView.findViewById(R.id.f_quiz_result_incorrect_answers);
        incorrectAnswersInfo = (TextView) rootView.findViewById(R.id.f_quiz_result_incorrect_answers_info);
        tryAgain = (Button) rootView.findViewById(R.id.f_quiz_result_try_again);
        correctAnswersImg = (ImageView) rootView.findViewById(R.id.f_quiz_result_correct_answers_img);
        incorrectAnswersImg = (ImageView) rootView.findViewById(R.id.f_quiz_result_incorrect_answers_img);

        setAnimation();
        setTypeface();

        questionAmount.setText(String.valueOf(QuizData.getAllChosenWords().size()));
        correctAnswers.setText(String.valueOf(QuizData.getCorrectAnswers()));
        incorrectAnswers.setText(String.valueOf(QuizData.getIncorrectAnswers()));

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuizData.addChosenWordsToQuizData();
                QuizData.resetStats();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, new QuizFragment()).commit();
            }
        });

        correctAnswersImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuizData.setWordsSummaryStatus(CORRECT_WORDS_SUMMARY);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_for_fragments, new QuizResultWordsSummaryFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        incorrectAnswersImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuizData.setWordsSummaryStatus(INCORRECT_WORDS_SUMMARY);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_for_fragments, new QuizResultWordsSummaryFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return rootView;
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setTypeface() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Regular-PL.ttf");
        title.setTypeface(typeface);
        questionAmount.setTypeface(typeface);
        questionAmountInfo.setTypeface(typeface);
        correctAnswers.setTypeface(typeface);
        correctAnswersInfo.setTypeface(typeface);
        incorrectAnswersInfo.setTypeface(typeface);
        incorrectAnswersInfo.setTypeface(typeface);
        tryAgain.setTypeface(typeface);
    }

}
