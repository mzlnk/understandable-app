package pl.understandable.understandable_dev_app.fragments.words.choice;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.entities_data.words_data.WordsSpellingData;
import pl.understandable.understandable_dev_app.data.enums.words.WordsLearningMode;
import pl.understandable.understandable_dev_app.data.params.WordsDataParams;
import pl.understandable.understandable_dev_app.data.entities_data.words_data.WordsListData;
import pl.understandable.understandable_dev_app.data.entities_data.words_data.WordsQuizData;
import pl.understandable.understandable_dev_app.data.entities_data.words_data.WordsRepetitionData;
import pl.understandable.understandable_dev_app.fragments.words.quiz.WordsQuizFragment;
import pl.understandable.understandable_dev_app.fragments.words.list.WordsListFragment;
import pl.understandable.understandable_dev_app.fragments.words.repetition.WordsRepetitionFragment;
import pl.understandable.understandable_dev_app.fragments.words.spelling.WordsSpellingFragment;
import pl.understandable.understandable_dev_app.utils.AdUtil;
import pl.understandable.understandable_dev_app.utils.ThemeUtil;
import pl.understandable.understandable_dev_app.utils.font.Font;

import static pl.understandable.understandable_dev_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_dev_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class WordsChoiceLengthFragment extends Fragment {

    private static final String DATA_PARAM = "words.choice.length.dataParam";

    private RelativeLayout mainLayout;
    private TextView title, amountInfo;
    private Button decrement, increment;
    private Button submit, back;

    private WordsDataParams dataParams;

    private Handler amountUpdater = new Handler();
    private boolean autoIncrement = false;
    private boolean autoDecrement = false;
    private int incrementedElements = 0;
    private int decrementedElements = 0;
    private int minAmount = 0;
    private int maxAmount = 0;

    public WordsChoiceLengthFragment() {
        // Required empty public constructor
    }

    public static WordsChoiceLengthFragment newInstance(String param) {
        WordsChoiceLengthFragment fragment = new WordsChoiceLengthFragment();
        Bundle args = new Bundle();
        args.putString(DATA_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            dataParams = new WordsDataParams().fromString(getArguments().getString(DATA_PARAM));
        }
        if(dataParams == null) {
            dataParams = new WordsDataParams();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.f_word_choice_length, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_words_choice_length);
        amountInfo = (TextView) rootView.findViewById(R.id.f_words_choice_length_size_info);
        title = (TextView) rootView.findViewById(R.id.f_words_choice_length_title);
        decrement = (Button) rootView.findViewById(R.id.f_words_choice_length_button_decrease);
        increment = (Button) rootView.findViewById(R.id.f_words_choice_length_button_increase);
        back = (Button) rootView.findViewById(R.id.f_words_choice_length_back);
        submit = (Button) rootView.findViewById(R.id.f_words_choice_length_submit);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        prepareAmountInfo();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        back.setTypeface(typeface);
        submit.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            decrement.setBackgroundResource(R.drawable.field_rounded_pink);
            increment.setBackgroundResource(R.drawable.field_rounded_pink);
            back.setBackgroundResource(R.drawable.field_rounded_pink);
            submit.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            decrement.setBackgroundResource(R.drawable.field_rounded_gray);
            increment.setBackgroundResource(R.drawable.field_rounded_gray);
            back.setBackgroundResource(R.drawable.field_rounded_gray);
            submit.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
    }

    private void prepareAmountInfo() {
        minAmount = 1;
        if(dataParams.mode.equals(WordsLearningMode.QUIZ)) {
            minAmount = 4;
        }
        maxAmount = dataParams.getMaximumAvailableWordsAmount();
        if(maxAmount > 1000) {
            maxAmount = 1000;
        }
        if(dataParams.size < minAmount) {
            dataParams.size = minAmount;
        }
        amountInfo.setText(String.valueOf(dataParams.size));
    }

    private void addListeners() {
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementAmount();
            }
        });
        increment.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                autoIncrement = true;
                amountUpdater.post(new AmountUpdater());
                return false;
            }
        });
        increment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)  && autoIncrement) {
                    autoIncrement = false;
                    incrementedElements = 0;
                }
                return false;
            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementAmount();;
            }
        });
        decrement.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                autoDecrement = true;
                amountUpdater.post(new AmountUpdater());
                return false;
            }
        });
        decrement.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)  && autoDecrement) {
                    autoDecrement = false;
                    decrementedElements = 0;
                }
                return false;
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordsChoiceModeFragment modeFragment = WordsChoiceModeFragment.newInstance(dataParams.toString());
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, modeFragment).commit();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdUtil.showAd();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                switch (dataParams.mode) {
                    case REPETITION:
                        WordsRepetitionData.createRepetitionDataFromParams(dataParams);
                        transaction.replace(R.id.layout_for_fragments, new WordsRepetitionFragment());
                        break;
                    case LIST:
                        WordsListData.createListDataFromParams(dataParams);
                        transaction.replace(R.id.layout_for_fragments, new WordsListFragment(), redirectTo(F_START));
                        break;
                    case QUIZ:
                        WordsQuizData.createQuizDataFromParams(dataParams);
                        transaction.replace(R.id.layout_for_fragments, new WordsQuizFragment());
                        break;
                    case SPELLING:
                        WordsSpellingData.createRepetitionDataFromParams(dataParams);
                        transaction.replace(R.id.layout_for_fragments, new WordsSpellingFragment());
                        break;
                }
                transaction.commit();
            }
        });
    }

    private void incrementAmount() {
        if(dataParams.size >= dataParams.getMaximumAvailableWordsAmount()) {
            return;
        }
        dataParams.size += (1 + (int)(incrementedElements / 2D));
        if(dataParams.size > maxAmount) {
            dataParams.size = maxAmount;
        }
        amountInfo.setText(String.valueOf(dataParams.size));
    }

    private void decrementAmount() {
        if(dataParams.size <= 1) {
            return;
        }
        if(dataParams.mode.equals(WordsLearningMode.QUIZ) && dataParams.size <= 4) {
            return;
        }
        dataParams.size -= (1 + (int)(decrementedElements / 2D));
        if(dataParams.size < minAmount) {
            dataParams.size = minAmount;
        }
        amountInfo.setText(String.valueOf(dataParams.size));
    }

    private class AmountUpdater implements Runnable {

        @Override
        public void run() {
            if(autoIncrement) {
                incrementAmount();
                incrementedElements++;
                amountUpdater.postDelayed(new AmountUpdater(), 5L);
            } else if(autoDecrement) {
                decrementAmount();
                decrementedElements++;
                amountUpdater.postDelayed(new AmountUpdater(), 5L);
            }
        }

    }

}
