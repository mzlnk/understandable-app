package pl.understandable.understandable_app.fragments.phrases.choice;

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
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.phrases.PhrasesListData;
import pl.understandable.understandable_app.data.entities_data.phrases.PhrasesQuizData;
import pl.understandable.understandable_app.data.entities_data.phrases.PhrasesRepetitionData;
import pl.understandable.understandable_app.data.enums.phrases.PhrasesLearningMode;
import pl.understandable.understandable_app.data.params.PhrasesDataParams;
import pl.understandable.understandable_app.fragments.phrases.list.PhrasesListFragment;
import pl.understandable.understandable_app.fragments.phrases.quiz.PhrasesQuizFragment;
import pl.understandable.understandable_app.fragments.phrases.repetition.PhrasesRepetitionFragment;
import pl.understandable.understandable_app.utils.AdUtil;
import pl.understandable.understandable_app.utils.GoogleAnalyticsManager;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.buttons.phrases.PhrasesModeButton;
import pl.understandable.understandable_app.utils.font.Font;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_PHRASES_CHOICE_CATEGORY;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;
import static pl.understandable.understandable_app.utils.GoogleAnalyticsManager.Action.GA_A_PHRASES_LIST;
import static pl.understandable.understandable_app.utils.GoogleAnalyticsManager.Action.GA_A_PHRASES_QUIZ;
import static pl.understandable.understandable_app.utils.GoogleAnalyticsManager.Action.GA_A_PHRASES_REPETITION;
import static pl.understandable.understandable_app.utils.GoogleAnalyticsManager.Category.GA_C_OPEN;

/**
 * Created by Marcin Zielonka
 */

public class PhrasesChoiceModeFragment extends Fragment {

    private static final String DATA_PARAM = "phrases.choice.mode.dataParam";

    private RelativeLayout mainLayout;
    private TableLayout modesLayout;
    private TextView title;
    private Button submit, back;

    private List<PhrasesModeButton> modes = new ArrayList<>();
    private PhrasesDataParams dataParams;

    public PhrasesChoiceModeFragment() {
        // Required empty public constructor
    }

    public static PhrasesChoiceModeFragment newInstance(String param) {
        PhrasesChoiceModeFragment fragment = new PhrasesChoiceModeFragment();
        Bundle args = new Bundle();
        args.putString(DATA_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            dataParams = new PhrasesDataParams().fromString(getArguments().getString(DATA_PARAM));
        }
        if(dataParams == null) {
            dataParams = new PhrasesDataParams();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the modesLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_phrases_choice_mode, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_phrases_choice_mode);
        modesLayout = (TableLayout) rootView.findViewById(R.id.f_phrases_choice_mode_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_phrases_choice_mode_title);
        submit = (Button) rootView.findViewById(R.id.f_phrases_choice_mode_submit);
        back = (Button) rootView.findViewById(R.id.f_phrases_choice_mode_back);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        initModeButtons();
        addModeButtonsToTable();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        submit.setTypeface(typeface);
        back.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            back.setBackgroundResource(R.drawable.field_rounded_pink);
            submit.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            back.setBackgroundResource(R.drawable.field_rounded_gray);
            submit.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
    }

    private void initModeButtons() {
        for(PhrasesLearningMode mode : PhrasesLearningMode.values()) {
            modes.add(new PhrasesModeButton(getContext(), dataParams, mode, modes));
        }
    }

    private void addModeButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (PhrasesModeButton modeButton : modes) {
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
                PhrasesChoiceWayFragment wayFragment = PhrasesChoiceWayFragment.newInstance(dataParams.toString());
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, wayFragment).commit();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdUtil.showAd(getContext());

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                switch (dataParams.mode) {
                    case REPETITION:
                        GoogleAnalyticsManager.Tracker.trackEvent(getActivity().getApplication(), GA_A_PHRASES_REPETITION, GA_C_OPEN);
                        PhrasesRepetitionData.createRepetitionDataFromParams(dataParams);
                        transaction.replace(R.id.layout_for_fragments, new PhrasesRepetitionFragment(), redirectTo(F_PHRASES_CHOICE_CATEGORY));
                        break;
                    case LIST:
                        GoogleAnalyticsManager.Tracker.trackEvent(getActivity().getApplication(), GA_A_PHRASES_LIST, GA_C_OPEN);
                        PhrasesListData.createListDataFromParams(dataParams);
                        transaction.replace(R.id.layout_for_fragments, new PhrasesListFragment(), redirectTo(F_PHRASES_CHOICE_CATEGORY));
                        break;
                    case QUIZ:
                        GoogleAnalyticsManager.Tracker.trackEvent(getActivity().getApplication(), GA_A_PHRASES_QUIZ, GA_C_OPEN);
                        PhrasesQuizData.createQuizDataFromParams(dataParams);
                        transaction.replace(R.id.layout_for_fragments, new PhrasesQuizFragment(), redirectTo(F_PHRASES_CHOICE_CATEGORY));
                        break;
                }
                transaction.commit();
            }
        });
    }

}
