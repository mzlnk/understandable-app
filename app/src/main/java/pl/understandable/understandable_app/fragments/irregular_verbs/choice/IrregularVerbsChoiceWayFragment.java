package pl.understandable.understandable_app.fragments.irregular_verbs.choice;


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
import pl.understandable.understandable_app.data.enums.irregular_verbs.IrregularVerbsLearningOrderWay;
import pl.understandable.understandable_app.data.enums.irregular_verbs.IrregularVerbsLearningWordsWay;
import pl.understandable.understandable_app.data.enums.words.WordsLearningLanguageWay;
import pl.understandable.understandable_app.data.enums.words.WordsLearningOrderWay;
import pl.understandable.understandable_app.data.enums.words.WordsLearningWordsWay;
import pl.understandable.understandable_app.data.params.IrregularVerbsDataParams;
import pl.understandable.understandable_app.data.params.WordsDataParams;
import pl.understandable.understandable_app.fragments.words.choice.WordsChoiceMethodFragment;
import pl.understandable.understandable_app.fragments.words.choice.WordsChoiceModeFragment;
import pl.understandable.understandable_app.fragments.words.choice.WordsChoiceSubcategoryFragment;
import pl.understandable.understandable_app.fragments.words.choice.WordsChoiceTypeFragment;
import pl.understandable.understandable_app.fragments.words.choice.WordsChoiceWayFragment;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.buttons.irregular_verbs.IrregularVerbsOrderWayButton;
import pl.understandable.understandable_app.utils.buttons.irregular_verbs.IrregularVerbsWordsWayButton;
import pl.understandable.understandable_app.utils.buttons.words.WordsLanguageWayButton;
import pl.understandable.understandable_app.utils.buttons.words.WordsOrderWayButton;
import pl.understandable.understandable_app.utils.buttons.words.WordsWordsWayButton;
import pl.understandable.understandable_app.utils.font.Font;

import static pl.understandable.understandable_app.data.enums.words.WordsLearningMethod.ALL;
import static pl.understandable.understandable_app.data.enums.words.WordsLearningMethod.SUBCATEGORIES;
import static pl.understandable.understandable_app.data.enums.words.WordsLearningMethod.TYPES;

/**
 * Created by Marcin Zielonka
 */

public class IrregularVerbsChoiceWayFragment extends Fragment {

    private static final String DATA_PARAM = "irregular.verbs.choice.languageWay.dataParam";

    private RelativeLayout mainLayout;
    private TableLayout wordsAndOrderWaysLayout;
    private TextView title;
    private Button submit;

    private List<IrregularVerbsWordsWayButton> wordWays = new ArrayList<>();
    private IrregularVerbsOrderWayButton orderWay;
    private IrregularVerbsDataParams dataParams;

    public IrregularVerbsChoiceWayFragment() {
        // Required empty public constructor
    }

    public static IrregularVerbsChoiceWayFragment newInstance(String param) {
        IrregularVerbsChoiceWayFragment fragment = new IrregularVerbsChoiceWayFragment();
        Bundle args = new Bundle();
        args.putString(DATA_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            String params = getArguments().getString(DATA_PARAM);
            dataParams = new IrregularVerbsDataParams();
            if(params != null) {
                dataParams = dataParams.fromString(params);
            }
        }
        if(dataParams == null) {
            dataParams = new IrregularVerbsDataParams();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the laguageWaysLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_irregular_verbs_choice_way, container, false);
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
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_irregular_verbs_choice_way);
        wordsAndOrderWaysLayout = (TableLayout) rootView.findViewById(R.id.f_irregular_verbs_choice_words_and_order_way_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_irregular_verbs_choice_way_title);
        submit = (Button) rootView.findViewById(R.id.f_irregular_verbs_choice_way_submit);
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        submit.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            submit.setBackgroundResource(R.drawable.field_rounded_pink);
        } else {
            submit.setBackgroundResource(R.drawable.field_rounded_gray);
        }
    }

    private void initWayButtons() {
        for(IrregularVerbsLearningWordsWay way : IrregularVerbsLearningWordsWay.values()) {
            wordWays.add(new IrregularVerbsWordsWayButton(getContext(), dataParams, way, wordWays));
        }
        orderWay = new IrregularVerbsOrderWayButton(getContext(), dataParams, IrregularVerbsLearningOrderWay.ALPHABETICAL);
    }

    private void addWayButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for(IrregularVerbsWordsWayButton wordWayButton : wordWays) {
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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrregularVerbsChoiceModeFragment modeFragment = IrregularVerbsChoiceModeFragment.newInstance(dataParams.toString());
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, modeFragment).commit();
            }
        });
    }
}
