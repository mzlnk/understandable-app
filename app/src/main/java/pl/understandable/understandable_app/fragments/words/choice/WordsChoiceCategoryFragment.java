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
import android.widget.Toast;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.words.WordsCategory;
import pl.understandable.understandable_app.data.params.WordsDataParams;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.buttons.words.WordsCategoryButton;
import pl.understandable.understandable_app.utils.font.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka
 */

public class WordsChoiceCategoryFragment extends Fragment {

    private static final String DATA_PARAM = "words.choice.category.dataParam";

    private RelativeLayout mainLayout;
    private TableLayout categoriesLayout;
    private TextView title;
    private Button submit;

    private List<WordsCategoryButton> categories = new ArrayList<>();
    private WordsDataParams dataParams;

    public WordsChoiceCategoryFragment() {
        // Required empty public constructor
    }

    public static WordsChoiceCategoryFragment newInstance(String param) {
        WordsChoiceCategoryFragment fragment = new WordsChoiceCategoryFragment();
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
        // Inflate the categoriesLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_words_choice_category, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();
        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_words_choice_category);
        categoriesLayout = (TableLayout) rootView.findViewById(R.id.f_words_choice_category_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_words_choice_category_title);
        submit = (Button) rootView.findViewById(R.id.f_words_choice_category_submit);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        initCategoriesButtons();
        addCategoryButtonsToTable();
    }

    public void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    public void setFonts() {
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

    private void initCategoriesButtons() {
        for(WordsCategory category : WordsCategory.values()) {
            categories.add(new WordsCategoryButton(getContext(), dataParams, category));
        }
    }

    private void addCategoryButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (WordsCategoryButton categoryButton : categories) {
            currentImageRow.addView(categoryButton.getImage());
            currentTextRow.addView(categoryButton.getText());
            if (x == 3) {
                categoriesLayout.addView(currentImageRow);
                categoriesLayout.addView(currentTextRow);
                currentImageRow = new TableRow(getContext());
                currentTextRow = new TableRow(getContext());
                x = 0;
            } else {
                x++;
            }
        }
        if (x != 0) {
            categoriesLayout.addView(currentImageRow);
            categoriesLayout.addView(currentTextRow);
        }
    }

    private void addListeners() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataParams.categories.size() > 0) {
                    WordsChoiceMethodFragment methodFragment = WordsChoiceMethodFragment.newInstance(dataParams.toString());
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.layout_for_fragments, methodFragment).commit();
                } else {
                    Toast.makeText(getContext(), "Wybierz przynajmniej 1 kategorię", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
