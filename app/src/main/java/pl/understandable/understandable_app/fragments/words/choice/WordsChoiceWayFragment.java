package pl.understandable.understandable_app.fragments.words.choice;

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

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.words.WordsLearningLanguageWay;
import pl.understandable.understandable_app.data.enums.words.WordsLearningOrderWay;
import pl.understandable.understandable_app.data.enums.words.WordsLearningWordsWay;
import pl.understandable.understandable_app.data.params.WordsDataParams;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.buttons.words.WordsLanguageWayButton;
import pl.understandable.understandable_app.utils.buttons.words.WordsOrderWayButton;
import pl.understandable.understandable_app.utils.buttons.words.WordsWordsWayButton;
import pl.understandable.understandable_app.utils.font.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka
 */

public class WordsChoiceWayFragment extends Fragment {

    private static final String DATA_PARAM = "custom.words.choice.languageWay.dataParam";

    private RelativeLayout mainLayout;
    private TableLayout laguageWaysLayout;
    private TableLayout wordsAndOrderWaysLayout;
    private TextView title;
    private Button back, submit;

    private List<WordsLanguageWayButton> laguageWays = new ArrayList<>();
    private List<WordsWordsWayButton> wordWays = new ArrayList<>();
    private WordsOrderWayButton orderWay;
    private WordsDataParams dataParams;

    public WordsChoiceWayFragment() {
        // Required empty public constructor
    }

    public static WordsChoiceWayFragment newInstance(String param) {
        WordsChoiceWayFragment fragment = new WordsChoiceWayFragment();
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
            dataParams = new WordsDataParams();
            if(params != null) {
                dataParams = dataParams.fromString(params);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the laguageWaysLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_words_choice_way, container, false);
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
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_words_choice_way);
        laguageWaysLayout = (TableLayout) rootView.findViewById(R.id.f_words_choice_language_way_names_layout);
        wordsAndOrderWaysLayout = (TableLayout) rootView.findViewById(R.id.f_words_choice_words_and_order_way_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_words_choice_way_title);
        back = (Button) rootView.findViewById(R.id.f_words_choice_way_back);
        submit = (Button) rootView.findViewById(R.id.f_words_choice_way_submit);
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
            back.setBackgroundResource(R.drawable.field_rounded_pink);
            submit.setBackgroundResource(R.drawable.field_rounded_pink);
        } else {
            back.setBackgroundResource(R.drawable.field_rounded_pink);
            submit.setBackgroundResource(R.drawable.field_rounded_gray);
        }
    }

    private void initWayButtons() {
        for(WordsLearningLanguageWay way : WordsLearningLanguageWay.values()) {
            laguageWays.add(new WordsLanguageWayButton(getContext(), dataParams, way, laguageWays));
        }
        for(WordsLearningWordsWay way : WordsLearningWordsWay.values()) {
            wordWays.add(new WordsWordsWayButton(getContext(), dataParams, way, wordWays));
        }
        orderWay = new WordsOrderWayButton(getContext(), dataParams, WordsLearningOrderWay.ALPHABETICAL);
    }

    private void addWayButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (WordsLanguageWayButton languageWayButton : laguageWays) {
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

        for(WordsWordsWayButton wordWayButton : wordWays) {
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
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                switch(dataParams.method) {
                    case ALL:
                        WordsChoiceMethodFragment methodFragment = WordsChoiceMethodFragment.newInstance(dataParams.toString());
                        manager.beginTransaction().replace(R.id.layout_for_fragments, methodFragment).commit();
                        break;
                    case TYPES:
                        WordsChoiceTypeFragment typeFragment = WordsChoiceTypeFragment.newInstance(dataParams.toString());
                        manager.beginTransaction().replace(R.id.layout_for_fragments, typeFragment).commit();
                        break;
                    case SUBCATEGORIES:
                        WordsChoiceSubcategoryFragment subcategoryFragment = WordsChoiceSubcategoryFragment.newInstance(dataParams.toString());
                        manager.beginTransaction().replace(R.id.layout_for_fragments, subcategoryFragment).commit();
                        break;
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordsChoiceModeFragment modeFragment = WordsChoiceModeFragment.newInstance(dataParams.toString());
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, modeFragment).commit();
            }
        });
    }

}
