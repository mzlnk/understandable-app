package net.heliantum.ziedic.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.database.entity.LanguageEntity;
import net.heliantum.ziedic.utils.CurrentlyChosenWordsData;
import net.heliantum.ziedic.utils.LanguageLearningDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class QuizFragment extends Fragment {

    private TextView question, questionNumber;
    private Button[] options = new Button[4];
    private TextView scoreView;
    private ProgressBar time;

    private int timeLeft = 5000;
    private int correctOption;
    private int chosenOption = -1;
    private boolean isAnswered = false, timesUp = false;

    List<LanguageEntity> words;
    List<LanguageEntity> wordsWithoutCorrectOne;
    LanguageEntity correctWord;
    LanguageEntity[] incorrectWords = new LanguageEntity[3];
    LanguageLearningDirection direction;

    private static Random r = new Random();
    private static final String SCORE_PARAM = "score";
    private static final String NUM_PARAM = "number";

    private int score;
    private int number;

    public QuizFragment() {
        // Required empty public constructor
    }

    public static QuizFragment newInstance(int score, int number) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putInt(SCORE_PARAM, score);
        number++;
        args.putInt(NUM_PARAM, number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            score = getArguments().getInt(SCORE_PARAM);
            number = getArguments().getInt(NUM_PARAM);
        }
        words = CurrentlyChosenWordsData.chosenWords;
        wordsWithoutCorrectOne = new ArrayList<>(words);
        direction = CurrentlyChosenWordsData.direction;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);

        question = (TextView) rootView.findViewById(R.id.quiz_question_word);
        questionNumber = (TextView) rootView.findViewById(R.id.question_number);
        options[0] = (Button) rootView.findViewById(R.id.quiz_option_0);
        options[1] = (Button) rootView.findViewById(R.id.quiz_option_1);
        options[2] = (Button) rootView.findViewById(R.id.quiz_option_2);
        options[3] = (Button) rootView.findViewById(R.id.quiz_option_3);
        scoreView = (TextView) rootView.findViewById(R.id.quiz_score);
        time = (ProgressBar) rootView.findViewById(R.id.quiz_time);

        time.setMax(5000);
        scoreView.setText(String.valueOf(score));
        questionNumber.setText(new String("Pytanie " + number));

        //setting correct & incorrect words objects:
        correctOption = r.nextInt(4);
        int correctWordPos = r.nextInt(words.size());
        correctWord = words.get(correctWordPos);
        words.remove(correctWordPos);
        wordsWithoutCorrectOne.remove(correctWordPos);
        for(int i = 0; i < 3; i++) {
            int pos = r.nextInt(wordsWithoutCorrectOne.size());
            incorrectWords[i] = wordsWithoutCorrectOne.get(pos);
            wordsWithoutCorrectOne.remove(pos);
        }
        wordsWithoutCorrectOne.clear();

        //setting questionView & optionsViews:
        switch(direction) {
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

        options[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!timesUp) {
                    options[0].setBackgroundColor(Color.rgb(255, 246, 143));
                    chosenOption = 0;
                    isAnswered = true;
                    getActivity().runOnUiThread(new ShowAnswerTask());
                }
            }
        });

        options[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!timesUp) {
                    options[1].setBackgroundColor(Color.rgb(255, 246, 143));
                    chosenOption = 1;
                    isAnswered = true;
                    getActivity().runOnUiThread(new ShowAnswerTask());
                }
            }
        });

        options[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!timesUp) {
                    options[2].setBackgroundColor(Color.rgb(255, 246, 143));
                    chosenOption = 2;
                    isAnswered = true;
                    getActivity().runOnUiThread(new ShowAnswerTask());
                }
            }
        });

        options[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!timesUp) {
                    options[3].setBackgroundColor(Color.rgb(255, 246, 143));
                    chosenOption = 3;
                    isAnswered = true;
                    getActivity().runOnUiThread(new ShowAnswerTask());
                }
            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new QuizTask(getActivity()), 0, 1);

        return rootView;
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

                if(!isAnswered) activity.runOnUiThread(new ShowAnswerTask());
                this.cancel();
            }
        }
    }

    private class ShowAnswerTask implements Runnable {

        @Override
        public void run() {


            android.os.Handler handler = new android.os.Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(isAnswered && chosenOption != -1) {
                        options[chosenOption].setBackgroundColor(Color.rgb(205, 85, 85));
                    }
                    options[correctOption].setBackgroundColor(Color.rgb(113, 198, 113));

                    if(chosenOption == correctOption) {
                        score += timeLeft;
                        scoreView.setText(String.valueOf(score));
                    }
                }
            }, 1500);

            //todo: next fragment

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(words.size() > 0 && getActivity() != null) {
                        QuizFragment nextFragment = QuizFragment.newInstance(score, number);
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.layout_for_fragments, nextFragment).commit();
                    }
                    else if(getActivity() != null){
                        Toast.makeText(getContext(), "Koniec quizu", Toast.LENGTH_SHORT).show();
                    }
                }
            }, 3000);
        }
    }

    private void setQuestionsPolishToEnglish() {
        question.setText(correctWord.getPolishWord());
        for(int i = 0, j = 0; i < 4; i++, j++) {
            if(i == correctOption) {
                j--;
                options[i].setText(correctWord.getEnglishWord());
                continue;
            }
            options[i].setText(incorrectWords[j].getEnglishWord());
        }
    }

    private void setQuestionsEnglishToPolish() {
        question.setText(correctWord.getEnglishWord());
        for(int i = 0, j = 0; i < 4; i++, j++) {
            if(i == correctOption) {
                j--;
                options[i].setText(correctWord.getPolishWord());
                continue;
            }
            options[i].setText(incorrectWords[j].getPolishWord());
        }
    }

}
