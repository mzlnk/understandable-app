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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.enums.words.WordsLearningLevel;
import pl.understandable.understandable_dev_app.data.params.WordsDataParams;
import pl.understandable.understandable_dev_app.utils.FragmentUtil;
import pl.understandable.understandable_dev_app.utils.ThemeUtil;
import pl.understandable.understandable_dev_app.utils.buttons.words.WordsLevelButton;
import pl.understandable.understandable_dev_app.utils.font.Font;

import static pl.understandable.understandable_dev_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_dev_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class WordsChoiceLevelFragment extends Fragment {

    private static final String DATA_PARAM = "words.choice.level.dataParam";

    private RelativeLayout mainLayout;
    private TableLayout modesLayout;
    private TextView title;
    private Button submit, back;

    private List<WordsLevelButton> levels = new ArrayList<>();
    private WordsDataParams dataParams;

    public WordsChoiceLevelFragment() {
        // Required empty public constructor
    }

    public static WordsChoiceLevelFragment newInstance(String param) {
        WordsChoiceLevelFragment fragment = new WordsChoiceLevelFragment();
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
        // Inflate the modesLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_words_choice_level, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_words_choice_level);
        modesLayout = (TableLayout) rootView.findViewById(R.id.f_words_choice_level_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_words_choice_level_title);
        submit = (Button) rootView.findViewById(R.id.f_words_choice_level_submit);
        back = (Button) rootView.findViewById(R.id.f_words_choice_level_back);
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
            submit.setBackgroundResource(R.drawable.field_rounded_pink);
        } else {
            back.setBackgroundResource(R.drawable.field_rounded_gray);
            submit.setBackgroundResource(R.drawable.field_rounded_gray);
        }
    }

    private void initModeButtons() {
        for(WordsLearningLevel level : WordsLearningLevel.values()) {
            levels.add(new WordsLevelButton(getContext(), dataParams, level));
        }
    }

    private void addModeButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (WordsLevelButton levelButton : levels) {
            currentImageRow.addView(levelButton.getImage());
            currentTextRow.addView(levelButton.getText());
            if (x == 2) {
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
                if(dataParams.levels.size() > 0) {
                    WordsChoiceWayFragment wayFragment = WordsChoiceWayFragment.newInstance(dataParams.toString());
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.layout_for_fragments, wayFragment).commit();
                } else {
                    Toast.makeText(getContext(), "Wybierz przynajmniej 1 poziom", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
