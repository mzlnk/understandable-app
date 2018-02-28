package pl.understandable.understandable_app.fragments.custom_words.choice;

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
import pl.understandable.understandable_app.data.enums.custom_words.CustomWordsLearningLanguageWay;
import pl.understandable.understandable_app.data.enums.custom_words.CustomWordsLearningOrderWay;
import pl.understandable.understandable_app.data.enums.custom_words.CustomWordsLearningWordsWay;
import pl.understandable.understandable_app.data.params.CustomWordsDataParams;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.buttons.custom_words.CustomWordsLanguageWayButton;
import pl.understandable.understandable_app.utils.buttons.custom_words.CustomWordsOrderWayButton;
import pl.understandable.understandable_app.utils.buttons.custom_words.CustomWordsWordsWayButton;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin Zielonka
 */

public class CustomWordsChoiceWayFragment extends Fragment {

    private static final String DATA_PARAM = "custom.words.choice.languageWay.dataParam";
    private static final String ID_PARAM = "custom.words.choice.languageWay.idParam";

    private RelativeLayout mainLayout;
    private TableLayout laguageWaysLayout;
    private TableLayout wordsAndOrderWaysLayout;
    private TextView title;
    private Button submit;

    private List<CustomWordsLanguageWayButton> laguageWays = new ArrayList<>();
    private List<CustomWordsWordsWayButton> wordWays = new ArrayList<>();
    private CustomWordsOrderWayButton orderWay;
    private CustomWordsDataParams dataParams;

    public CustomWordsChoiceWayFragment() {
        // Required empty public constructor
    }

    public static CustomWordsChoiceWayFragment newInstance(String id, String param) {
        CustomWordsChoiceWayFragment fragment = new CustomWordsChoiceWayFragment();
        Bundle args = new Bundle();
        args.putString(DATA_PARAM, param);
        args.putString(ID_PARAM, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            String params = getArguments().getString(DATA_PARAM);
            String id = getArguments().getString(ID_PARAM);
            dataParams = new CustomWordsDataParams(id);
            if(params != null) {
                dataParams = dataParams.fromString(params);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the laguageWaysLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_custom_words_choice_way, container, false);
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
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_custom_words_choice_way);
        laguageWaysLayout = (TableLayout) rootView.findViewById(R.id.f_custom_words_choice_language_way_names_layout);
        wordsAndOrderWaysLayout = (TableLayout) rootView.findViewById(R.id.f_custom_words_choice_words_and_order_way_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_custom_words_choice_way_title);
        submit = (Button) rootView.findViewById(R.id.f_custom_words_choice_way_submit);
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
        for(CustomWordsLearningLanguageWay way : CustomWordsLearningLanguageWay.values()) {
            laguageWays.add(new CustomWordsLanguageWayButton(getContext(), dataParams, way, laguageWays));
        }
        for(CustomWordsLearningWordsWay way : CustomWordsLearningWordsWay.values()) {
            wordWays.add(new CustomWordsWordsWayButton(getContext(), dataParams, way, wordWays));
        }
        orderWay = new CustomWordsOrderWayButton(getContext(), dataParams, CustomWordsLearningOrderWay.ALPHABETICAL);
    }

    private void addWayButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (CustomWordsLanguageWayButton languageWayButton : laguageWays) {
            currentImageRow.addView(languageWayButton.getImage());
            currentTextRow.addView(languageWayButton.getText());
            if (x == 3) {
                laguageWaysLayout.addView(currentImageRow);
                laguageWaysLayout.addView(currentTextRow);
                currentImageRow = new TableRow(getContext());
                currentTextRow = new TableRow(getContext());
                x = 0;
            } else {
                x++;
            }
        }
        if (x != 0) {
            laguageWaysLayout.addView(currentImageRow);
            laguageWaysLayout.addView(currentTextRow);
        }

        currentImageRow = new TableRow(getContext());
        currentTextRow = new TableRow(getContext());
        x = 0;

        for(CustomWordsWordsWayButton wordWayButton : wordWays) {
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
                CustomWordsChoiceModeFragment modeFragment = CustomWordsChoiceModeFragment.newInstance(dataParams.id, dataParams.toString());
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, modeFragment).commit();
            }
        });
    }

}
