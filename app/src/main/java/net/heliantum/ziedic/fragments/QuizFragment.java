package net.heliantum.ziedic.fragments;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.data.QuizData;
import net.heliantum.ziedic.database.entity.LanguageEntity;
import net.heliantum.ziedic.data.ChosenWordsData;
import net.heliantum.ziedic.data.enums.LearningWay;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class QuizFragment extends Fragment {

    private View rootView;
    private TableLayout layout;

    private TextView question, questionNumber;
    private Button[] answers = new Button[4];
    private ProgressBar time;

    private int timeLeft = 5000;
    private int correctOption;
    private int chosenOption = -1;
    private boolean isAnswered = false, timesUp = false;

    private List<LanguageEntity> wordsWithoutCorrectOne;
    private LanguageEntity correctWord;
    private LanguageEntity[] incorrectWords = new LanguageEntity[3];

    private static Random r = new Random();

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wordsWithoutCorrectOne = new ArrayList<>(QuizData.getAllChosenWords());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
        layout = (TableLayout) rootView.findViewById(R.id.fragment_quiz_table_layout_2);

        question = (TextView) rootView.findViewById(R.id.fragment_quiz_question);
        questionNumber = (TextView) rootView.findViewById(R.id.fragment_quiz_questionnumber);
        answers[0] = (Button) rootView.findViewById(R.id.fragment_quiz_option0);
        answers[1] = (Button) rootView.findViewById(R.id.fragment_quiz_option1);
        answers[2] = (Button) rootView.findViewById(R.id.fragment_quiz_option2);
        answers[3] = (Button) rootView.findViewById(R.id.fragment_quiz_option3);
        time = (ProgressBar) rootView.findViewById(R.id.fragment_quiz_time);

        time.setMax(5000);
        time.setProgressTintList(ColorStateList.valueOf(Color.rgb(0, 153, 255)));
        questionNumber.setText(new String("Pytanie " + QuizData.getCurrentQuestion()));

        setAnimation();
        setTypeface();
        setAnswers();
        addListeners();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new QuizTask(getActivity()), 0, 1);

        return rootView;
    }

    private void addListeners() {
        for(int i = 0; i < 4; i++) {
            final int option = i;
            answers[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!timesUp) {
                        chosenOption = option;
                        isAnswered = true;

                        if(chosenOption == correctOption) {
                            QuizData.addCorrectAnswer();
                            QuizData.addCorrectWord(correctWord);
                        } else {
                            QuizData.addIncorrectAnswer();
                            QuizData.addIncorrectWord(correctWord);
                        }

                        getActivity().runOnUiThread(new ShowAnswerTask());
                    }
                }
            });
        }
    }

    private void setAnswers() {
        correctOption = r.nextInt(4);
        correctWord = QuizData.getChosenWordsLeft().get(r.nextInt(QuizData.getChosenWordsLeft().size()));
        QuizData.removeWord(correctWord);
        wordsWithoutCorrectOne.remove(correctWord);
        for(int i = 0; i < 3; i++) {
            int pos = r.nextInt(wordsWithoutCorrectOne.size());
            incorrectWords[i] = wordsWithoutCorrectOne.get(pos);
            wordsWithoutCorrectOne.remove(pos);
        }
        wordsWithoutCorrectOne.clear();

        switch(QuizData.getWay()) {
            case POLISH_TO_ENGLISH:
                setQuestionsPolishToEnglish();
                break;
            case ENGLISH_TO_POLISH:
                setQuestionsEnglishToPolish();
                break;
            default:
                if(r.nextBoolean())
                    setQuestionsPolishToEnglish();
                else
                    setQuestionsEnglishToPolish();
        }
    }

    private void setQuestionsPolishToEnglish() {
        question.setText(correctWord.getPolishWord());
        for(int i = 0, j = 0; i < 4; i++) {
            if(i == correctOption) {
                answers[i].setText(correctWord.getEnglishWord());
                continue;
            }
            answers[i].setText(incorrectWords[j].getEnglishWord());
            j++;
        }
    }

    private void setQuestionsEnglishToPolish() {
        question.setText(correctWord.getEnglishWord());
        for(int i = 0, j = 0; i < 4; i++) {
            if(i == correctOption) {
                answers[i].setText(correctWord.getPolishWord());
                continue;
            }
            answers[i].setText(incorrectWords[j].getPolishWord());
            j++;
        }
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        layout.setAnimation(anim);
    }

    private void setTypeface() {
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular-PL.ttf");
        question.setTypeface(typeFace);
        questionNumber.setTypeface(typeFace);
        answers[0].setTypeface(typeFace);
        answers[1].setTypeface(typeFace);
        answers[2].setTypeface(typeFace);
        answers[3].setTypeface(typeFace);
    }

    private class QuizTask extends TimerTask {

        private FragmentActivity activity;

        public QuizTask(FragmentActivity activity) {
            this.activity = activity;
        }

        @Override
        public void run() {

            if(timeLeft > 0) {
                timeLeft--;
                time.setProgress(timeLeft);
            }
            if(timeLeft <= 0 || isAnswered) {
                timesUp = true;

                if(!isAnswered) {
                    QuizData.addIncorrectAnswer();
                    activity.runOnUiThread(new ShowAnswerTask());
                }
                this.cancel();
            }
        }
    }

    private class ShowAnswerTask implements Runnable {

        @Override
        public void run() {

            if(getActivity() != null) {

                int showCorrectOptionDelay = 1000;
                int hideIncorrectOptionsDelay = 1500;

                if (isAnswered && chosenOption != -1) {
                    answers[chosenOption].setBackgroundResource(R.drawable.button_rounded_red);
                }
                if (chosenOption == correctOption) {
                    answers[chosenOption].setBackgroundResource(R.drawable.button_rounded_green);
                    showCorrectOptionDelay = 0;
                    hideIncorrectOptionsDelay = 500;
                }

                android.os.Handler handler = new android.os.Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        answers[correctOption].setBackgroundResource(R.drawable.button_rounded_green);
                    }
                }, showCorrectOptionDelay);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 4; i++) {
                            if (i == correctOption) continue;

                            Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
                            answers[i].startAnimation(anim);
                        }

                    }
                }, hideIncorrectOptionsDelay);

                //todo: next fragment

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        QuizData.nextQuestion();

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        if (QuizData.getChosenWordsLeft().size() > 0 && getActivity() != null) {
                            transaction.replace(R.id.layout_for_fragments, new QuizFragment()).commit();
                        } else if (getActivity() != null) {
                            transaction.replace(R.id.layout_for_fragments, new QuizResultFragment()).commit();
                        }
                    }
                }, 3000);
            }
        }
    }

}
