package net.heliantum.ziedic.fragments.choice;


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

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.data.DataParams;
import net.heliantum.ziedic.data.enums.LanguageCategory;
import net.heliantum.ziedic.fragments.utils.choice.CategoryButton;
import net.heliantum.ziedic.utils.font.Font;

import java.util.ArrayList;
import java.util.List;

public class WordsChoiceCategoryFragment extends Fragment {

    private static final String DATA_PARAM = "category.dataParam";

    private RelativeLayout mainLayout;
    private TableLayout categoriesLayout;
    private TextView title;
    private Button submit;

    private List<CategoryButton> categories = new ArrayList<>();
    private DataParams dataParams;

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
            dataParams = DataParams.fromString(DATA_PARAM);
        }
        if(dataParams == null) {
            dataParams = new DataParams();
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
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_words_choice_category_fragment_layout);
        categoriesLayout = (TableLayout) rootView.findViewById(R.id.f_words_choice_category_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_words_choice_category_title);
        submit = (Button) rootView.findViewById(R.id.f_words_choice_category_submit);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        initCategoriesButtons();
        addCategoryButtonsToTable();
    }

    public void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    public void setFonts() {
        title.setTypeface(Font.TYPEFACE_MONTSERRAT);
        submit.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    private void initCategoriesButtons() {
        for(LanguageCategory category : LanguageCategory.values()) {
            categories.add(new CategoryButton(getContext(), dataParams, category));
        }
    }

    private void addCategoryButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (CategoryButton categoryButton : categories) {
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
                    WordsChoiceTypeFragment typeFragment = WordsChoiceTypeFragment.newInstance(dataParams.toString());
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.layout_for_fragments, typeFragment).commit();
                } else {
                    Toast.makeText(getContext(), "Wybierz przynajmniej 1 kategorię", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
