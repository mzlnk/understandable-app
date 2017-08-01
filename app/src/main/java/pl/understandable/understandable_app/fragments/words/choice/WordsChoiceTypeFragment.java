package pl.understandable.understandable_app.fragments.words.choice;

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
import pl.understandable.understandable_app.data.params.WordsDataParams;
import pl.understandable.understandable_app.data.enums.words.WordsLanguageType;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.buttons.words.WordsTypeButton;
import pl.understandable.understandable_app.utils.FragmentUtil;
import pl.understandable.understandable_app.utils.font.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsChoiceTypeFragment extends Fragment {

    private static final String DATA_PARAM = "words.type.dataParam";

    private RelativeLayout mainLayout;
    private TableLayout typesLayout;
    private TextView title;
    private Button submit, back;

    private List<WordsTypeButton> types = new ArrayList<>();
    private WordsDataParams dataParams;

    public WordsChoiceTypeFragment() {
        // Required empty public constructor
    }

    public static WordsChoiceTypeFragment newInstance(String param) {
        WordsChoiceTypeFragment fragment = new WordsChoiceTypeFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the typesLayout for this fragment
        View rootView =  inflater.inflate(R.layout.f_words_choice_type, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_words_choice_type);
        typesLayout = (TableLayout) rootView.findViewById(R.id.f_words_choice_type_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_words_choice_type_title);
        submit = (Button) rootView.findViewById(R.id.f_words_choice_type_submit);
        back = (Button) rootView.findViewById(R.id.f_words_choice_type_back);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        initTypeButtons();
        addTypeButtonsToTable();
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

    private void initTypeButtons() {
        for(WordsLanguageType type : WordsLanguageType.values()) {
            types.add(new WordsTypeButton(getContext(), dataParams, type));
        }
    }

    private void addTypeButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (WordsTypeButton typeButton : types) {
            currentImageRow.addView(typeButton.getImage());
            currentTextRow.addView(typeButton.getText());
            if (x == 3) {
                typesLayout.addView(currentImageRow);
                typesLayout.addView(currentTextRow);
                currentImageRow = new TableRow(getContext());
                currentTextRow = new TableRow(getContext());
                x = 0;
            } else {
                x++;
            }
        }
        if (x != 0) {
            typesLayout.addView(currentImageRow);
            typesLayout.addView(currentTextRow);
        }
    }

    private void addListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordsChoiceCategoryFragment categoryFragment = WordsChoiceCategoryFragment.newInstance(dataParams.toString());
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, categoryFragment, FragmentUtil.F_WORDS_CHOICE_CATEGORY).commit();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataParams.types.size() > 0) {
                    WordsChoiceWayFragment wayFragment = WordsChoiceWayFragment.newInstance(dataParams.toString());
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.layout_for_fragments, wayFragment, FragmentUtil.F_WORDS_CHOICE_WAY).commit();
                } else {
                    Toast.makeText(getContext(), "Wybierz przynajmniej 1 rodzaj", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
