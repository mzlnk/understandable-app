package net.heliantum.ziedic.fragments;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
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
import net.heliantum.ziedic.database.entity.LanguageEntity;
import net.heliantum.ziedic.data.CurrentlyChosenWordsData;
import net.heliantum.ziedic.data.LearningWay;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class QuizFragment extends Fragment {

    private TableLayout layout;
    private TextView question, questionNumber;
    private Button[] options = new Button[4];
    private TextView scoreView;
    private ProgressBar time;

    private int timeLeft = 5000;
    private int correctOption;
    private int chosenOption = -1;
    private boolean isAnswered = false, timesUp = false;

    private List<LanguageEntity> words;
    private List<LanguageEntity> wordsWithoutCorrectOne;
    private LanguageEntity correctWord;
    private LanguageEntity[] incorrectWords = new LanguageEntity[3];
    private LearningWay direction;

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
        words = CurrentlyChosenWordsData.getChosenWords();
        wordsWithoutCorrectOne = new ArrayList<>(words);
        direction = CurrentlyChosenWordsData.getWay();
        Toast.makeText(getContext(), "onCreate method. Words: " + CurrentlyChosenWordsData.getChosenWords().size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
        Toast.makeText(getContext(), "onCreateView method", Toast.LENGTH_SHORT).show();

        layout = (TableLayout) rootView.findViewById(R.id.fragment_quiz_table_layout_2);
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        layout.setAnimation(anim);

        String fontPath = "fonts/Montserrat-Regular-PL.ttf";
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

        question = (TextView) rootView.findViewById(R.id.fragment_quiz_question);
        questionNumber = (TextView) rootView.findViewById(R.id.fragment_quiz_questionnumber);
        options[0] = (Button) rootView.findViewById(R.id.fragment_quiz_option0);
        options[1] = (Button) rootView.findViewById(R.id.fragment_quiz_option1);
        options[2] = (Button) rootView.findViewById(R.id.fragment_quiz_option2);
        options[3] = (Button) rootView.findViewById(R.id.fragment_quiz_option3);
        scoreView = (TextView) rootView.findViewById(R.id.fragment_quiz_score);
        time = (ProgressBar) rootView.findViewById(R.id.fragment_quiz_time);

        time.setMax(5000);
        scoreView.setText(String.valueOf(score));
        questionNumber.setText(new String("Pytanie " + number));

        //setting font:
        question.setTypeface(typeFace);
        questionNumber.setTypeface(typeFace);
        options[0].setTypeface(typeFace);
        options[1].setTypeface(typeFace);
        options[2].setTypeface(typeFace);
        options[3].setTypeface(typeFace);
        scoreView.setTypeface(typeFace);
        time.setProgressTintList(ColorStateList.valueOf(Color.rgb(0, 153, 255)));

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

            if(getActivity() != null) {
                if (isAnswered && chosenOption != -1) {
                    options[chosenOption].setBackgroundResource(R.drawable.button_rounded_red);
                }
                if (chosenOption == correctOption) {
                    options[correctOption].setBackgroundResource(R.drawable.button_rounded_green);
                }

                if (chosenOption == correctOption) {
                    score += timeLeft;
                    scoreView.setText(String.valueOf(score));

                    Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
                    scoreView.startAnimation(anim);
                }

                android.os.Handler handler = new android.os.Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        options[correctOption].setBackgroundResource(R.drawable.button_rounded_green);
                    }
                }, 1000);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i < 4; i++) {
                            if (i == correctOption) continue;

                            Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
                            options[i].startAnimation(anim);
                        }

                    }
                }, 1500);

                //todo: next fragment

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (words.size() > 0 && getActivity() != null) {
                            QuizFragment nextFragment = QuizFragment.newInstance(score, number);
                            FragmentManager manager = getActivity().getSupportFragmentManager();
                            manager.beginTransaction().replace(R.id.layout_for_fragments, nextFragment).commit();
                        } else if (getActivity() != null) {
                            Toast.makeText(getContext(), "Koniec quizu", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 3000);
            }
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
