package pl.understandable.understandable_app.fragments.words.quiz;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.words_data.WordsQuizData;
import pl.understandable.understandable_app.database.entity.WordEntity;
import pl.understandable.understandable_app.utils.FragmentUtil;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class WordsQuizFragment extends Fragment {

    private static final int QUESTION_ANSWER_TIME_IN_MILLIS = 5000;
    private static final Random r = new Random();

    private WordsQuizData quizData;

    private LinearLayout questionArea;
    private TextView question, questionNumber;
    private Button[] answers = new Button[4];
    private Button finish;
    private ProgressBar time;

    private int timeLeft = QUESTION_ANSWER_TIME_IN_MILLIS;
    private int correctOption;
    private int chosenOption = -1;
    private boolean isAnswered = false, timesUp = false;

    private WordEntity correctWord;
    private WordEntity[] incorrectWords = new WordEntity[3];

    public WordsQuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quizData = WordsQuizData.getQuizData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_words_quiz, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();
        initTimer();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        questionArea = (LinearLayout) rootView.findViewById(R.id.f_words_quiz_question_area);
        question = (TextView) rootView.findViewById(R.id.f_words_quiz_question);
        questionNumber = (TextView) rootView.findViewById(R.id.f_words_quiz_question_number);
        answers[0] = (Button) rootView.findViewById(R.id.f_words_quiz_option_0);
        answers[1] = (Button) rootView.findViewById(R.id.f_words_quiz_option_1);
        answers[2] = (Button) rootView.findViewById(R.id.f_words_quiz_option_2);
        answers[3] = (Button) rootView.findViewById(R.id.f_words_quiz_option_3);
        time = (ProgressBar) rootView.findViewById(R.id.f_words_quiz_time);
        finish = (Button) rootView.findViewById(R.id.f_words_quiz_finish);
    }

    private void initTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new QuizTask(getActivity()), 0, 1);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        prepareViews();
        setAnswers();
    }

    private void prepareViews() {
        questionNumber.setText(new String("Pytanie " + WordsQuizData.getQuizData().currentQuestion));
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

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, new WordsQuizResultFragment(), FragmentUtil.F_WORDS_QUIZ_RESULT).commit();
            }
        });
    }

    private void setAnswers() {
        WordsQuizData.getQuizData().nextQuestion();
        correctOption = WordsQuizData.Util.getRandomCorrectOption();
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
        question.setText(correctWord.getPolish());
        for(int i = 0, j = 0; i < 4; i++) {
            if(i == correctOption) {
                answers[i].setText(correctWord.getEnglish());
                continue;
            }
            answers[i].setText(incorrectWords[j].getEnglish());
            j++;
        }
    }

    private void setQuestionsEnglishToPolish() {
        question.setText(correctWord.getEnglish());
        for(int i = 0, j = 0; i < 4; i++) {
            if(i == correctOption) {
                answers[i].setText(correctWord.getPolish());
                continue;
            }
            answers[i].setText(incorrectWords[j].getPolish());
            j++;
        }
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        questionArea.setAnimation(anim);
    }

    private void setFonts() {
        question.setTypeface(Font.TYPEFACE_MONTSERRAT);
        questionNumber.setTypeface(Font.TYPEFACE_MONTSERRAT);
        answers[0].setTypeface(Font.TYPEFACE_MONTSERRAT);
        answers[1].setTypeface(Font.TYPEFACE_MONTSERRAT);
        answers[2].setTypeface(Font.TYPEFACE_MONTSERRAT);
        answers[3].setTypeface(Font.TYPEFACE_MONTSERRAT);
        finish.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            for(Button answer : answers) {
                answer.setBackgroundResource(R.drawable.field_rounded_light_light_gray);
            }
            finish.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            for(Button answer : answers) {
                answer.setBackgroundResource(R.drawable.field_rounded_gray);
            }
            finish.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
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
                    answers[chosenOption].setBackgroundResource(R.drawable.field_rounded_red);
                }
                if (chosenOption == correctOption) {
                    answers[chosenOption].setBackgroundResource(R.drawable.field_rounded_green);
                    showCorrectOptionDelay = 0;
                    hideIncorrectOptionsDelay = 500;
                }

                android.os.Handler handler = new android.os.Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(getContext() == null) {
                            return;
                        }
                        answers[correctOption].setBackgroundResource(R.drawable.field_rounded_green);
                    }
                }, showCorrectOptionDelay);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(getContext() == null) {
                            return;
                        }
                        for (int i = 0; i < 4; i++) {
                            if (i == correctOption) {
                                continue;
                            }
                            Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
                            answers[i].startAnimation(anim);
                        }

                    }
                }, hideIncorrectOptionsDelay);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(getActivity() == null) {
                            return;
                        }
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        if (quizData.wordsLeft.size() > 1) {
                            transaction.replace(R.id.layout_for_fragments, new WordsQuizFragment(), FragmentUtil.F_WORDS_QUIZ).commit();
                        } else {
                            transaction.replace(R.id.layout_for_fragments, new WordsQuizResultFragment(), FragmentUtil.F_WORDS_QUIZ_RESULT).commit();
                        }
                    }
                }, 3000);
            }
        }
    }

}
