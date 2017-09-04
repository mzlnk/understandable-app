package pl.understandable.understandable_dev_app.fragments.words.choice;

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

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.enums.words.WordsLearningWay;
import pl.understandable.understandable_dev_app.data.params.WordsDataParams;
import pl.understandable.understandable_dev_app.utils.ThemeUtil;
import pl.understandable.understandable_dev_app.utils.buttons.words.WordsWayButton;
import pl.understandable.understandable_dev_app.utils.FragmentUtil;
import pl.understandable.understandable_dev_app.utils.font.Font;

import java.util.ArrayList;
import java.util.List;

import static pl.understandable.understandable_dev_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_dev_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class WordsChoiceWayFragment extends Fragment {

    private static final String DATA_PARAM = "words.choice.way.dataParam";

    private RelativeLayout mainLayout;
    private TableLayout waysLayout;
    private TextView title;
    private Button submit, back;

    private List<WordsWayButton> ways = new ArrayList<>();
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
            dataParams = new WordsDataParams().fromString(getArguments().getString(DATA_PARAM));
        }
        if(dataParams == null) {
            dataParams = new WordsDataParams();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the waysLayout for this fragment
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
        waysLayout = (TableLayout) rootView.findViewById(R.id.f_words_choice_way_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_words_choice_way_title);
        submit = (Button) rootView.findViewById(R.id.f_words_choice_way_submit);
        back = (Button) rootView.findViewById(R.id.f_words_choice_way_back);
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
        for(WordsLearningWay way : WordsLearningWay.values()) {
            ways.add(new WordsWayButton(getContext(), dataParams, way, ways));
        }
    }

    private void addWayButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (WordsWayButton wayButton : ways) {
            currentImageRow.addView(wayButton.getImage());
            currentTextRow.addView(wayButton.getText());
            if (x == 3) {
                waysLayout.addView(currentImageRow);
                waysLayout.addView(currentTextRow);
                currentImageRow = new TableRow(getContext());
                currentTextRow = new TableRow(getContext());
                x = 0;
            } else {
                x++;
            }
        }
        if (x != 0) {
            waysLayout.addView(currentImageRow);
            waysLayout.addView(currentTextRow);
        }
    }

    private void addListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                switch(dataParams.method) {
                    case ALL:
                        WordsChoiceCategoryFragment categoryFragment = WordsChoiceCategoryFragment.newInstance(dataParams.toString());
                        manager.beginTransaction().replace(R.id.layout_for_fragments, categoryFragment, redirectTo(F_START)).commit();
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
