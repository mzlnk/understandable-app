package net.heliantum.ziedic.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import net.heliantum.ziedic.utils.OnSwipeTouchListener;

import java.util.List;
import java.util.Random;

public class WordsRepetitionFragment extends Fragment {

    private List<LanguageEntity> words;
    private List<LanguageEntity> wordToRepeat;
    private LanguageLearningDirection direction;
    private LanguageEntity currentWord;
    private int currentPosition = 0;

    private TextView word0, word1;
    private Button repeat;
    private ProgressBar progress;

    private static Random r = new Random();

    public WordsRepetitionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        words = CurrentlyChosenWordsData.chosenWords;
        direction = CurrentlyChosenWordsData.direction;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_words_repetition, container, false);

        word0 = (TextView) rootView.findViewById(R.id.word_0);
        word1 = (TextView) rootView.findViewById(R.id.word_1);
        repeat = (Button) rootView.findViewById(R.id.repeat);
        progress = (ProgressBar) rootView.findViewById(R.id.progress);

        nextWord();
        progress.setMax(words.size());
        progress.setProgress(0);

        switch(direction) {
            case POLISH_TO_ENGLISH:
                word0.setText(currentWord.getPolishWord());
                word1.setText(currentWord.getEnglishWord());
                break;
            case ENGLISH_TO_POLISH:
                word0.setText(currentWord.getEnglishWord());
                word1.setText(currentWord.getPolishWord());
                break;
            case RANDOM:
                boolean choice = r.nextBoolean();
                if(choice) {
                    word0.setText(currentWord.getPolishWord());
                    word1.setText(currentWord.getEnglishWord());
                }
                else {
                    word0.setText(currentWord.getEnglishWord());
                    word1.setText(currentWord.getPolishWord());
                }
                break;
        }

        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                words.add(currentWord);
                progress.setMax(words.size());
                Toast.makeText(getContext(), "Dodano do powtórzenia", Toast.LENGTH_SHORT).show();
            }
        });

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(word1.getCurrentTextColor() == Color.BLACK) word1.setTextColor(Color.WHITE);
                if(word1.getCurrentTextColor() == Color.WHITE) word1.setTextColor(Color.BLACK);
            }
        });

        rootView.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeLeft() {
                previousWord();

                switch(direction) {
                    case POLISH_TO_ENGLISH:
                        word0.setText(currentWord.getPolishWord());
                        word1.setText(currentWord.getEnglishWord());
                        break;
                    case ENGLISH_TO_POLISH:
                        word0.setText(currentWord.getEnglishWord());
                        word1.setText(currentWord.getPolishWord());
                        break;
                    case RANDOM:
                        boolean choice = r.nextBoolean();
                        if(choice) {
                            word0.setText(currentWord.getPolishWord());
                            word1.setText(currentWord.getEnglishWord());
                        }
                        else {
                            word0.setText(currentWord.getEnglishWord());
                            word1.setText(currentWord.getPolishWord());
                        }
                        break;
                }
            }

            @Override
            public void onSwipeRight() {
                nextWord();

                switch(direction) {
                    case POLISH_TO_ENGLISH:
                        word0.setText(currentWord.getPolishWord());
                        word1.setText(currentWord.getEnglishWord());
                        break;
                    case ENGLISH_TO_POLISH:
                        word0.setText(currentWord.getEnglishWord());
                        word1.setText(currentWord.getPolishWord());
                        break;
                    case RANDOM:
                        boolean choice = r.nextBoolean();
                        if(choice) {
                            word0.setText(currentWord.getPolishWord());
                            word1.setText(currentWord.getEnglishWord());
                        }
                        else {
                            word0.setText(currentWord.getEnglishWord());
                            word1.setText(currentWord.getPolishWord());
                        }
                        break;
                }
            }
        });

        return rootView;
    }

    private boolean nextWord() {
        if(currentPosition < words.size()) {
            currentWord = words.get(currentPosition);
            progress.setProgress(currentPosition);
            currentPosition++;
            return true;
        }
        return false;
    }

    private boolean previousWord() {
        currentPosition--;
        if(currentPosition >= 0) {
            currentWord = words.get(currentPosition);
            progress.setProgress(currentPosition);
            return true;
        }
        return false;
    }
}
