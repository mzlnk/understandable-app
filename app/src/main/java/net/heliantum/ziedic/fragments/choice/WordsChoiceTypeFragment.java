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
import net.heliantum.ziedic.data.enums.LanguageType;
import net.heliantum.ziedic.fragments.utils.choice.TypeButton;
import net.heliantum.ziedic.utils.font.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsChoiceTypeFragment extends Fragment {

    private static final String DATA_PARAM = "type.dataParam";

    private RelativeLayout mainLayout;
    private TableLayout typesLayout;
    private TextView title;
    private Button submit, back;

    private List<TypeButton> types = new ArrayList<>();
    private DataParams dataParams;

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
            dataParams = DataParams.fromString(DATA_PARAM);
        }
        if(dataParams == null) {
            dataParams = new DataParams();
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
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.fragment_words_choice_type_fragment_layout);
        typesLayout = (TableLayout) rootView.findViewById(R.id.f_words_choice_type_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_words_choice_type_title);
        submit = (Button) rootView.findViewById(R.id.f_words_choice_type_submit);
        back = (Button) rootView.findViewById(R.id.f_words_choice_type_back);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
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

    private void initTypeButtons() {
        for(LanguageType type : LanguageType.values()) {
            types.add(new TypeButton(getContext(), dataParams, type));
        }
    }

    private void addTypeButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (TypeButton typeButton : types) {
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
                manager.beginTransaction().replace(R.id.layout_for_fragments, categoryFragment).commit();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataParams.types.size() > 0) {
                    WordsChoiceWayFragment modeFragment = new WordsChoiceWayFragment();
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.layout_for_fragments, modeFragment).commit();
                } else {
                    Toast.makeText(getContext(), "Wybierz przynajmniej 1 rodzaj", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
