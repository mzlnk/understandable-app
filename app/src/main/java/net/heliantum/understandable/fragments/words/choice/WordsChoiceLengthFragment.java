package net.heliantum.understandable.fragments.words.choice;


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

import net.heliantum.understandable.R;
import net.heliantum.understandable.data.params.WordsDataParams;
import net.heliantum.understandable.data.entities_data.words_data.WordsListData;
import net.heliantum.understandable.data.entities_data.words_data.WordsQuizData;
import net.heliantum.understandable.data.entities_data.words_data.WordsRepetitionData;
import net.heliantum.understandable.fragments.error.NoWordsErrorFragment;
import net.heliantum.understandable.fragments.words.quiz.WordsQuizFragment;
import net.heliantum.understandable.fragments.words.list.WordsListFragment;
import net.heliantum.understandable.fragments.words.repetition.WordsRepetitionFragment;
import net.heliantum.understandable.utils.FragmentUtil;
import net.heliantum.understandable.utils.font.Font;

/**
 * A simple {@link Fragment} subclass.
 */

public class WordsChoiceLengthFragment extends Fragment {

    private static final String DATA_PARAM = "words.length.dataParam";

    private RelativeLayout mainLayout;
    private TextView title, amountInfo;
    private Button submit, back;
    private SeekBar amountAdjust;

    private WordsDataParams dataParams;

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
                manager.beginTransaction().replace(R.id.layout_for_fragments, modeFragment, FragmentUtil.F_WORDS_CHOICE_MODE).commit();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                if(dataParams.getMaximumAvailableWordsAmount() > 0) {
                    switch (dataParams.mode) {
                        case REPETITION:
                            WordsRepetitionData.createRepetitionDataFromParams(dataParams);
                            transaction.replace(R.id.layout_for_fragments, new WordsRepetitionFragment(), FragmentUtil.F_WORDS_REPETITION);
                            break;
                        case LIST:
                            WordsListData.createListDataFromParams(dataParams);
                            transaction.replace(R.id.layout_for_fragments, new WordsListFragment(), FragmentUtil.F_WORDS_LIST);
                            break;
                        case QUIZ:
                            if(dataParams.getMaximumAvailableWordsAmount() >= 4) {
                                WordsQuizData.createQuizDataFromParams(dataParams);
                                transaction.replace(R.id.layout_for_fragments, new WordsQuizFragment(), FragmentUtil.F_WORDS_QUIZ);
                            } else {
                                transaction.replace(R.id.layout_for_fragments, new NoWordsErrorFragment(), FragmentUtil.F_NO_WORDS_ERROR);
                                transaction.addToBackStack(null);
                            }
                            break;
                    }
                } else {
                    transaction.replace(R.id.layout_for_fragments, new NoWordsErrorFragment(), FragmentUtil.F_NO_WORDS_ERROR);
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
