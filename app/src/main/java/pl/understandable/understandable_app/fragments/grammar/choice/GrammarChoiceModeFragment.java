package pl.understandable.understandable_app.fragments.grammar.choice;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.grammar.GrammarFillGapData;
import pl.understandable.understandable_app.data.entities_data.grammar.GrammarQuizData;
import pl.understandable.understandable_app.data.enums.grammar.GrammarLearningMode;
import pl.understandable.understandable_app.data.params.GrammarDataParams;
import pl.understandable.understandable_app.fragments.grammar.fill_gap.GrammarFillGapFragment;
import pl.understandable.understandable_app.fragments.grammar.preview.GrammarSetPreviewFragment;
import pl.understandable.understandable_app.fragments.grammar.quiz.GrammarQuizFragment;
import pl.understandable.understandable_app.utils.FragmentUtil;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.buttons.grammar.GrammarModeButton;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin Zielonka
 */

public class GrammarChoiceModeFragment extends Fragment {

    private static final String ID_PARAM = "grammar.choice.mode.idParam";
    private static final String NAME_PARAM = "grammar.choice.mode.nameParam";

    private RelativeLayout mainLayout;
    private TableLayout modesLayout;
    private TextView title;
    private Button submit, back;

    private List<GrammarModeButton> modes = new ArrayList<>();
    private GrammarDataParams dataParams = new GrammarDataParams();
    private String id;
    private String name;

    public GrammarChoiceModeFragment() {
        // Required empty public constructor
    }

    public static GrammarChoiceModeFragment newInstance(String id, String name) {
        GrammarChoiceModeFragment fragment = new GrammarChoiceModeFragment();
        Bundle args = new Bundle();
        args.putString(ID_PARAM, id);
        args.putString(NAME_PARAM, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            id = getArguments().getString(ID_PARAM);
            name = getArguments().getString(NAME_PARAM);
        }
        if(dataParams == null) {
            dataParams = new GrammarDataParams();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the modesLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_grammar_choice_mode, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_grammar_choice_mode);
        modesLayout = (TableLayout) rootView.findViewById(R.id.f_grammar_choice_mode_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_grammar_choice_mode_title);
        submit = (Button) rootView.findViewById(R.id.f_grammar_choice_mode_submit);
        back = (Button) rootView.findViewById(R.id.f_grammar_choice_mode_back);
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
            submit.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            back.setBackgroundResource(R.drawable.field_rounded_gray);
            submit.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
    }

    private void initModeButtons() {
        for(GrammarLearningMode mode : GrammarLearningMode.values()) {
            modes.add(new GrammarModeButton(getContext(), dataParams, mode, modes));
        }
    }

    private void addModeButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (GrammarModeButton modeButton : modes) {
            currentImageRow.addView(modeButton.getImage());
            currentTextRow.addView(modeButton.getText());
            if (x == 3) {
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
                GrammarSetPreviewFragment grammarSetPreviewFragment = GrammarSetPreviewFragment.newInstance(id, name);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, grammarSetPreviewFragment, FragmentUtil.F_PHRASES_CHOICE_WAY).commit();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataParams.getMaximumAvailableWordsAmount() <= 0) {
                    Toast.makeText(getContext(), "Ten tryb jest niedostępny w tym dziale", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(dataParams.mode.equals(GrammarLearningMode.QUIZ) && dataParams.getMaximumAvailableWordsAmount() < 4) {
                    Toast.makeText(getContext(), "Ten tryb jest niedostępny w tym dziale", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                switch (dataParams.mode) {
                    case QUIZ:
                        GrammarQuizData.createQuizDataFromParams(dataParams);
                        transaction.replace(R.id.layout_for_fragments, new GrammarQuizFragment(), FragmentUtil.F_PHRASES_QUIZ);
                        break;
                    case FILL_GAPS:
                        GrammarFillGapData.createRepetitionDataFromParams(dataParams);
                        transaction.replace(R.id.layout_for_fragments, new GrammarFillGapFragment());
                        break;
                }
                transaction.commit();
            }
        });
    }

}
