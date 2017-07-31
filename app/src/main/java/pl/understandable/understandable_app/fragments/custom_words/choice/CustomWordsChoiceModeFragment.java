package pl.understandable.understandable_app.fragments.custom_words.choice;

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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.custom_words_data.CustomWordsListData;
import pl.understandable.understandable_app.data.entities_data.custom_words_data.CustomWordsQuizData;
import pl.understandable.understandable_app.data.entities_data.custom_words_data.CustomWordsRepetitionData;
import pl.understandable.understandable_app.data.entities_data.words_data.WordsListData;
import pl.understandable.understandable_app.data.entities_data.words_data.WordsQuizData;
import pl.understandable.understandable_app.data.entities_data.words_data.WordsRepetitionData;
import pl.understandable.understandable_app.data.enums.custom_words.CustomWordsLearningMode;
import pl.understandable.understandable_app.data.enums.words.WordsLearningMode;
import pl.understandable.understandable_app.data.params.CustomWordsDataParams;
import pl.understandable.understandable_app.data.params.WordsDataParams;
import pl.understandable.understandable_app.fragments.custom_words.list.CustomWordsListFragment;
import pl.understandable.understandable_app.fragments.custom_words.quiz.CustomWordsQuizFragment;
import pl.understandable.understandable_app.fragments.custom_words.repetition.CustomWordsRepetitionFragment;
import pl.understandable.understandable_app.fragments.error.NoWordsErrorFragment;
import pl.understandable.understandable_app.fragments.words.choice.WordsChoiceLengthFragment;
import pl.understandable.understandable_app.fragments.words.choice.WordsChoiceModeFragment;
import pl.understandable.understandable_app.fragments.words.choice.WordsChoiceWayFragment;
import pl.understandable.understandable_app.fragments.words.list.WordsListFragment;
import pl.understandable.understandable_app.fragments.words.quiz.WordsQuizFragment;
import pl.understandable.understandable_app.fragments.words.repetition.WordsRepetitionFragment;
import pl.understandable.understandable_app.utils.FragmentUtil;
import pl.understandable.understandable_app.utils.buttons.custom_words.CustomWordsModeButton;
import pl.understandable.understandable_app.utils.buttons.words.WordsModeButton;
import pl.understandable.understandable_app.utils.font.Font;

public class CustomWordsChoiceModeFragment extends Fragment {

    private static final String DATA_PARAM = "custom.words.mode.dataParam";

    private RelativeLayout mainLayout;
    private TableLayout modesLayout;
    private TextView title;
    private Button submit, back;

    private List<CustomWordsModeButton> modes = new ArrayList<>();
    private CustomWordsDataParams dataParams;

    public CustomWordsChoiceModeFragment() {
        // Required empty public constructor
    }

    public static CustomWordsChoiceModeFragment newInstance(String param) {
        CustomWordsChoiceModeFragment fragment = new CustomWordsChoiceModeFragment();
        Bundle args = new Bundle();
        args.putString(DATA_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            dataParams = new CustomWordsDataParams().fromString(getArguments().getString(DATA_PARAM));
        }
        if(dataParams == null) {
            dataParams = new CustomWordsDataParams();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the modesLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_custom_words_choice_mode, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_custom_words_choice_mode);
        modesLayout = (TableLayout) rootView.findViewById(R.id.f_custom_words_choice_mode_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_custom_words_choice_mode_title);
        submit = (Button) rootView.findViewById(R.id.f_custom_words_choice_mode_submit);
        back = (Button) rootView.findViewById(R.id.f_custom_words_choice_mode_back);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        initModeButtons();
        addModeButtonsToTable();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        title.setTypeface(Font.TYPEFACE_MONTSERRAT);
        submit.setTypeface(Font.TYPEFACE_MONTSERRAT);
        back.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    private void initModeButtons() {
        for(CustomWordsLearningMode mode : CustomWordsLearningMode.values()) {
            modes.add(new CustomWordsModeButton(getContext(), dataParams, mode, modes));
        }
    }

    private void addModeButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (CustomWordsModeButton modeButton : modes) {
            currentImageRow.addView(modeButton.getImage());
            currentTextRow.addView(modeButton.getText());
            if (x == 3) {
                modesLayout.addView(currentImageRow);
                modesLayout.addView(currentTextRow);
                currentImageRow = new TableRow(getContext());
                currentTextRow = new TableRow(getContext());
                x = 0;
            } else {
                x++;
            }
        }
        if (x != 0) {
            modesLayout.addView(currentImageRow);
            modesLayout.addView(currentTextRow);
        }
    }

    private void addListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomWordsChoiceWayFragment wayFragment = CustomWordsChoiceWayFragment.newInstance(dataParams.toString());
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, wayFragment, FragmentUtil.F_CUSTOM_WORDS_CHOICE_WAY).commit();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataParams.getMaximumAvailableWordsAmount() <= 0) {
                    Toast.makeText(getContext(), "Zbyt mała ilość fiszek, aby rozpocząć naukę w tym trybie", Toast.LENGTH_LONG).show();
                    return;
                }
                if(dataParams.mode.equals(CustomWordsLearningMode.QUIZ) && dataParams.getMaximumAvailableWordsAmount() < 4) {
                    Toast.makeText(getContext(), "Zbyt mała ilość fiszek, aby rozpocząć naukę w tym trybie", Toast.LENGTH_LONG).show();
                    return;
                }

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                switch (dataParams.mode) {
                    case REPETITION:
                        CustomWordsRepetitionData.createRepetitionDataFromParams(dataParams);
                        transaction.replace(R.id.layout_for_fragments, new CustomWordsRepetitionFragment(), FragmentUtil.F_CUSTOM_WORDS_REPETITION);
                        break;
                    case LIST:
                        CustomWordsListData.createListDataFromParams(dataParams);
                        transaction.replace(R.id.layout_for_fragments, new CustomWordsListFragment(), FragmentUtil.F_CUSTOM_WORDS_LIST);
                        break;
                    case QUIZ:
                        CustomWordsQuizData.createQuizDataFromParams(dataParams);
                        transaction.replace(R.id.layout_for_fragments, new CustomWordsQuizFragment(), FragmentUtil.F_CUSTOM_WORDS_QUIZ);
                        break;
                }
                transaction.commit();
            }
        });
    }

}
