package net.heliantum.ziedic.fragments.choice;


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
import android.widget.SeekBar;
import android.widget.TextView;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.data.DataParams;
import net.heliantum.ziedic.data.ListData;
import net.heliantum.ziedic.data.QuizData;
import net.heliantum.ziedic.data.RepetitionData;
import net.heliantum.ziedic.fragments.error.NoWordsErrorFragment;
import net.heliantum.ziedic.fragments.quiz.QuizFragment;
import net.heliantum.ziedic.fragments.list.WordsListFragment;
import net.heliantum.ziedic.fragments.repetition.WordsRepetitionFragment;
import net.heliantum.ziedic.utils.font.Font;

/**
 * A simple {@link Fragment} subclass.
 */

public class WordsChoiceLengthFragment extends Fragment {

    private static final String DATA_PARAM = "category.dataParam";

    private RelativeLayout mainLayout;
    private TextView title, amountInfo;
    private Button submit, back;
    private SeekBar amountAdjust;

    private DataParams dataParams;

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
            dataParams = DataParams.fromString(getArguments().getString(DATA_PARAM));
        }
        if(dataParams == null) {
            dataParams = new DataParams();
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
        amountAdjust = (SeekBar) rootView.findViewById(R.id.f_words_choice_length_size_adjust);
        title = (TextView) rootView.findViewById(R.id.f_words_choice_length_title);
        back = (Button) rootView.findViewById(R.id.f_words_choice_length_back);
        submit = (Button) rootView.findViewById(R.id.f_words_choice_length_submit);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareSeekBar();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        title.setTypeface(Font.TYPEFACE_MONTSERRAT);
        back.setTypeface(Font.TYPEFACE_MONTSERRAT);
        submit.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    private void prepareSeekBar() {
        final StartPosition startPos = new StartPosition();
        switch (dataParams.mode) {
            case QUIZ:
                startPos.setPos(4);
                break;
            default:
                startPos.setPos(1);
        }

        amountAdjust.setMax(dataParams.getMaximumAvailableWordsAmount() - startPos.getPos());
        dataParams.setSize(startPos.getPos());
        amountAdjust.setProgress(dataParams.size - startPos.getPos());
        amountInfo.setText(String.valueOf(dataParams.size));

        amountAdjust.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                amountInfo.setText(String.valueOf(i + startPos.getPos()));
                dataParams.setSize(i + startPos.getPos());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void addListeners() {
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
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                if(dataParams.getMaximumAvailableWordsAmount() > 0) {
                    switch (dataParams.mode) {
                        case REPETITION:
                            RepetitionData.createRepetitionDataFromParams(dataParams);
                            transaction.replace(R.id.layout_for_fragments, new WordsRepetitionFragment());
                            break;
                        case LIST:
                            ListData.createListDataFromParams(dataParams);
                            transaction.replace(R.id.layout_for_fragments, new WordsListFragment());
                            break;
                        case QUIZ:
                            if(dataParams.getMaximumAvailableWordsAmount() >= 4) {
                                QuizData.createQuizDataFromParams(dataParams);
                                transaction.replace(R.id.layout_for_fragments, new QuizFragment());
                            } else {
                                transaction.replace(R.id.layout_for_fragments, new NoWordsErrorFragment());
                                transaction.addToBackStack(null);
                            }
                            break;
                    }
                } else {
                    transaction.replace(R.id.layout_for_fragments, new NoWordsErrorFragment());
                    transaction.addToBackStack(null);
                }
                transaction.commit();
            }
        });
    }

    private class StartPosition {

        private int pos;

        public void setPos(int pos) {
            this.pos = pos;
        }

        public int getPos() {
            return pos;
        }
    }

}
