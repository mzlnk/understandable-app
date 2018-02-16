package pl.understandable.understandable_app.fragments.phrases.choice;

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
import pl.understandable.understandable_app.data.enums.phrases.PhrasesLearningWay;
import pl.understandable.understandable_app.data.params.PhrasesDataParams;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.buttons.phrases.PhrasesWayButton;
import pl.understandable.understandable_app.utils.font.Font;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class PhrasesChoiceWayFragment extends Fragment {

    private static final String DATA_PARAM = "phrases.choice.way.dataParam";

    private RelativeLayout mainLayout;
    private TableLayout waysLayout;
    private TextView title;
    private Button back, submit;

    private List<PhrasesWayButton> ways = new ArrayList<>();
    private PhrasesDataParams dataParams;

    public PhrasesChoiceWayFragment() {
        // Required empty public constructor
    }

    public static PhrasesChoiceWayFragment newInstance(String param) {
        PhrasesChoiceWayFragment fragment = new PhrasesChoiceWayFragment();
        Bundle args = new Bundle();
        args.putString(DATA_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            dataParams = new PhrasesDataParams().fromString(getArguments().getString(DATA_PARAM));
        }
        if(dataParams == null) {
            dataParams = new PhrasesDataParams();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the waysLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_phrases_choice_way, container, false);
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
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_phrases_choice_way);
        waysLayout = (TableLayout) rootView.findViewById(R.id.f_phrases_choice_way_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_phrases_choice_way_title);
        back = (Button) rootView.findViewById(R.id.f_phrases_choice_way_back);
        submit = (Button) rootView.findViewById(R.id.f_phrases_choice_way_submit);
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
        for(PhrasesLearningWay way : PhrasesLearningWay.values()) {
            ways.add(new PhrasesWayButton(getContext(), dataParams, way, ways));
        }
    }

    private void addWayButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (PhrasesWayButton wayButton : ways) {
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
            public void onClick(View v) {
                PhrasesChoiceCategoryFragment categoryFragment = PhrasesChoiceCategoryFragment.newInstance(dataParams.toString());
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, categoryFragment, redirectTo(F_START)).commit();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhrasesChoiceModeFragment modeFragment = PhrasesChoiceModeFragment.newInstance(dataParams.toString());
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, modeFragment).commit();
            }
        });
    }

}
