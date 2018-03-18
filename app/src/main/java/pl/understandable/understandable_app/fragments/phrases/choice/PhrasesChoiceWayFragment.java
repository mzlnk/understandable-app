package pl.understandable.understandable_app.fragments.phrases.choice;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import pl.understandable.understandable_app.data.enums.phrases.PhrasesLearningLanguageWay;
import pl.understandable.understandable_app.data.enums.phrases.PhrasesLearningOrderWay;
import pl.understandable.understandable_app.data.enums.phrases.PhrasesLearningWordsWay;
import pl.understandable.understandable_app.data.params.PhrasesDataParams;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.buttons.phrases.PhrasesLanguageWayButton;
import pl.understandable.understandable_app.utils.buttons.phrases.PhrasesOrderWayButton;
import pl.understandable.understandable_app.utils.buttons.phrases.PhrasesWordsWayButton;
import pl.understandable.understandable_app.utils.font.Font;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class PhrasesChoiceWayFragment extends Fragment {

    private static final String DATA_PARAM = "phrases.choice.languageWay.dataParam";

    private RelativeLayout mainLayout;
    private TableLayout languageWaysLayout;
    private TableLayout wordsAndOrderWaysLayout;
    private TextView title;
    private Button back, submit;

    private List<PhrasesLanguageWayButton> languageWays = new ArrayList<>();
    private List<PhrasesWordsWayButton> wordWays = new ArrayList<>();
    private PhrasesOrderWayButton orderWay;
    private PhrasesDataParams dataParams;

    public PhrasesChoiceWayFragment() {
        // Required empty public constructor
    }

    public static PhrasesChoiceWayFragment newInstance(String param) {
        PhrasesChoiceWayFragment fragment = new PhrasesChoiceWayFragment();
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
        // Inflate the languageWaysLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_phrases_choice_way, container, false);
        loadViewFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        initWayButtons();
        addWayButtonsToTable();
    }

    private void loadViewFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_phrases_choice_way);
        languageWaysLayout = (TableLayout) rootView.findViewById(R.id.f_phrases_choice_language_way_names_layout);
        wordsAndOrderWaysLayout = (TableLayout) rootView.findViewById(R.id.f_phrases_choice_words_and_order_way_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_phrases_choice_way_title);
        back = (Button) rootView.findViewById(R.id.f_phrases_choice_way_back);
        submit = (Button) rootView.findViewById(R.id.f_phrases_choice_way_submit);
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
            submit.setBackgroundResource(R.drawable.field_rounded_pink);
        } else {
            back.setBackgroundResource(R.drawable.field_rounded_gray);
            submit.setBackgroundResource(R.drawable.field_rounded_gray);
        }
    }

    private void initWayButtons() {
        for(PhrasesLearningLanguageWay way : PhrasesLearningLanguageWay.values()) {
            languageWays.add(new PhrasesLanguageWayButton(getContext(), dataParams, way, languageWays));
        }
        for(PhrasesLearningWordsWay way : PhrasesLearningWordsWay.values()) {
            wordWays.add(new PhrasesWordsWayButton(getContext(), dataParams, way, wordWays));
        }
        orderWay = new PhrasesOrderWayButton(getContext(), dataParams, PhrasesLearningOrderWay.ALPHABETICAL);
    }

    private void addWayButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (PhrasesLanguageWayButton languageWayButton : languageWays) {
            currentImageRow.addView(languageWayButton.getImage());
            currentTextRow.addView(languageWayButton.getText());
            if (x == 3) {
                languageWaysLayout.addView(currentImageRow);
                languageWaysLayout.addView(currentTextRow);
                currentImageRow = new TableRow(getContext());
                currentTextRow = new TableRow(getContext());
                x = 0;
            } else {
                x++;
            }
        }
        if (x != 0) {
            languageWaysLayout.addView(currentImageRow);
            languageWaysLayout.addView(currentTextRow);
        }

        currentImageRow = new TableRow(getContext());
        currentTextRow = new TableRow(getContext());
        x = 0;

        for(PhrasesWordsWayButton wordWayButton : wordWays) {
            currentImageRow.addView(wordWayButton.getImage());
            currentTextRow.addView(wordWayButton.getText());
            if (x == 3) {
                wordsAndOrderWaysLayout.addView(currentImageRow);
                wordsAndOrderWaysLayout.addView(currentTextRow);
                currentImageRow = new TableRow(getContext());
                currentTextRow = new TableRow(getContext());
                x = 0;
            } else {
                x++;
            }
        }
        currentImageRow.addView(orderWay.getImage());
        currentTextRow.addView(orderWay.getText());
        x++;
        if (x != 0) {
            wordsAndOrderWaysLayout.addView(currentImageRow);
            wordsAndOrderWaysLayout.addView(currentTextRow);
        }

    }

    private void addListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhrasesChoiceCategoryFragment categoryFragment = PhrasesChoiceCategoryFragment.newInstance(dataParams.toString());
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, categoryFragment, redirectTo(F_START)).commit();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhrasesChoiceModeFragment modeFragment = PhrasesChoiceModeFragment.newInstance(dataParams.toString());
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, modeFragment).commit();
            }
        });
    }

}
