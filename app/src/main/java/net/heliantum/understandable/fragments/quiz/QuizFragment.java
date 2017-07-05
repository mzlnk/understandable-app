package net.heliantum.understandable.fragments.quiz;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

import net.heliantum.understandable.R;
import net.heliantum.understandable.data.QuizData;
import net.heliantum.understandable.database.entity.LanguageEntity;
import net.heliantum.understandable.utils.font.Font;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class QuizFragment extends Fragment {

    private static final int QUESTION_ANSWER_TIME_IN_MILLIS = 5000;
    private static final Random r = new Random();

    private QuizData quizData;

    private TableLayout questionLayout;
    private TextView question, questionNumber;
    private Button[] answers = new Button[4];
    private ProgressBar time;

    private int timeLeft = QUESTION_ANSWER_TIME_IN_MILLIS;
    private int correctOption;
    private int chosenOption = -1;
    private boolean isAnswered = false, timesUp = false;

    private LanguageEntity correctWord;
    private LanguageEntity[] incorrectWords = new LanguageEntity[3];

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quizData = QuizData.getQuizData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_quiz, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();
        initTimer();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        questionLayout = (TableLayout) rootView.findViewById(R.id.f_quiz_question_table);
        question = (TextView) rootView.findViewById(R.id.f_quiz_question);
        questionNumber = (TextView) rootView.findViewById(R.id.f_quiz_question_number);
        answers[0] = (Button) rootView.findViewById(R.id.f_quiz_option_0);
        answers[1] = (Button) rootView.findViewById(R.id.f_quiz_option_1);
        answers[2] = (Button) rootView.findViewById(R.id.f_quiz_option_2);
        answers[3] = (Button) rootView.findViewById(R.id.f_quiz_option_3);
        time = (ProgressBar) rootView.findViewById(R.id.f_quiz_time);
    }

    private void initTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new QuizTask(getActivity()), 0, 1);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareViews();
        setAnswers();
    }

    private void prepareViews() {
        questionNumber.setText(new String("Pytanie " + QuizData.getQuizData().currentQuestion));
        time.setMax(QUESTION_ANSWER_TIME_IN_MILLIS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            time.setProgressTintList(ColorStateList.valueOf(Color.rgb(0, 153, 255)));
        }
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
                            quizData.correctAnswer();
                        } else {
                            quizData.incorrectAnswer();
                        }
                        getActivity().runOnUiThread(new ShowAnswerTask());
                    }
                }
            });
        }
    }

    private void setAnswers() {
        QuizData.getQuizData().nextQuestion();
        correctOption = QuizData.Util.getRandomCorrectOption();
        correctWord = quizData.currentWord;
        incorrectWords = quizData.getRandomIncorrectAnswers();

        switch(quizData.getParams().way) {
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
        questionLayout.setAnimation(anim);
    }

    private void setFonts() {
        question.setTypeface(Font.TYPEFACE_MONTSERRAT);
        questionNumber.setTypeface(Font.TYPEFACE_MONTSERRAT);
        answers[0].setTypeface(Font.TYPEFACE_MONTSERRAT);
        answers[1].setTypeface(Font.TYPEFACE_MONTSERRAT);
        answers[2].setTypeface(Font.TYPEFACE_MONTSERRAT);
        answers[3].setTypeface(Font.TYPEFACE_MONTSERRAT);
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
                    quizData.incorrectAnswer();
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
                    answers[chosenOption].setBackgroundResource(R.drawable.button_red);
                }
                if (chosenOption == correctOption) {
                    answers[chosenOption].setBackgroundResource(R.drawable.button_green);
                    showCorrectOptionDelay = 0;
                    hideIncorrectOptionsDelay = 500;
                }

                android.os.Handler handler = new android.os.Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        answers[correctOption].setBackgroundResource(R.drawable.button_green);
                    }
                }, showCorrectOptionDelay);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 4; i++) {
                            if (i == correctOption) {
                                continue;
                            }
                            Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
                            answers[i].startAnimation(anim);
                        }

                    }
                }, hideIncorrectOptionsDelay);

                //todo: next fragment

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        if (quizData.wordsLeft.size() > 1 && getActivity() != null) {
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
