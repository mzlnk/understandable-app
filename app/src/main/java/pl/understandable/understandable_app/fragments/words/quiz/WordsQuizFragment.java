package pl.understandable.understandable_app.fragments.words.quiz;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.words_data.WordsQuizData;
import pl.understandable.understandable_app.database.entity.WordEntity;
import pl.understandable.understandable_app.dialogs.help.HelpManager;
import pl.understandable.understandable_app.dialogs.help.QuizHelpDialog;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

import java.util.Random;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class WordsQuizFragment extends Fragment {

    private static final Random r = new Random();

    private WordsQuizData quizData;

    private LinearLayout questionArea;
    private TextView question, questionNumber;
    private Button[] answers = new Button[4];
    private Button finish;

    private int correctOption;
    private int chosenOption = -1;

    private boolean answered = false;

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

        HelpManager.showHelpDialog(getContext(), new QuizHelpDialog(getContext()));

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
        finish = (Button) rootView.findViewById(R.id.f_words_quiz_finish);
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
    }

    private void addListeners() {
        for(int i = 0; i < 4; i++) {
            final int option = i;
            answers[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!answered) {
                        answered = true;
                        chosenOption = option;
                        if (chosenOption == correctOption) {
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
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, new WordsQuizResultFragment(), redirectTo(F_START)).commit();
            }
        });
    }

    private void setAnswers() {
        WordsQuizData.getQuizData().nextQuestion();
        correctOption = WordsQuizData.Util.getRandomCorrectOption();
        correctWord = quizData.currentWord;
        incorrectWords = quizData.getRandomIncorrectAnswers();

        switch(quizData.getParams().laguageWay) {
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
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        question.setTypeface(typeface);
        questionNumber.setTypeface(typeface);
        answers[0].setTypeface(typeface);
        answers[1].setTypeface(typeface);
        answers[2].setTypeface(typeface);
        answers[3].setTypeface(typeface);
        finish.setTypeface(typeface);
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

    private class ShowAnswerTask implements Runnable {

        @Override
        public void run() {

            if(getActivity() != null) {
                int showCorrectOptionDelay = 1000;
                int hideIncorrectOptionsDelay = 1500;

                answers[chosenOption].setBackgroundResource(R.drawable.field_rounded_red);

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
                            transaction.replace(R.id.layout_for_fragments, new WordsQuizFragment(), redirectTo(F_START)).commit();
                        } else {
                            transaction.replace(R.id.layout_for_fragments, new WordsQuizResultFragment(), redirectTo(F_START)).commit();
                        }
                    }
                }, 3000);
            }
        }
    }

}
