package net.heliantum.understandable.fragments.irregular_verbs.choice;


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

import net.heliantum.understandable.R;
import net.heliantum.understandable.data.enums.irregular_verbs.IrregularVerbsLearningMode;
import net.heliantum.understandable.data.enums.words.WordsLearningMode;
import net.heliantum.understandable.data.params.IrregularVerbsDataParams;
import net.heliantum.understandable.data.params.WordsDataParams;
import net.heliantum.understandable.fragments.irregular_verbs.utils.choice.IrregularVerbsModeButton;
import net.heliantum.understandable.fragments.words.choice.WordsChoiceLengthFragment;
import net.heliantum.understandable.fragments.words.choice.WordsChoiceModeFragment;
import net.heliantum.understandable.fragments.words.choice.WordsChoiceWayFragment;
import net.heliantum.understandable.fragments.words.utils.choice.WordsModeButton;
import net.heliantum.understandable.utils.FragmentUtil;
import net.heliantum.understandable.utils.font.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IrregularVerbsChoiceModeFragment extends Fragment {

    private static final String DATA_PARAM = "irregular.verbs.mode.dataParam";

    private RelativeLayout mainLayout;
    private TableLayout modesLayout;
    private TextView title;
    private Button submit;

    private List<IrregularVerbsModeButton> modes = new ArrayList<>();
    private IrregularVerbsDataParams dataParams;

    public IrregularVerbsChoiceModeFragment() {
        // Required empty public constructor
    }

    public static IrregularVerbsChoiceModeFragment newInstance(String param) {
        IrregularVerbsChoiceModeFragment fragment = new IrregularVerbsChoiceModeFragment();
        Bundle args = new Bundle();
        args.putString(DATA_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            dataParams = IrregularVerbsDataParams.fromString(getArguments().getString(DATA_PARAM));
        }
        if(dataParams == null) {
            dataParams = new IrregularVerbsDataParams();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the modesLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_irregular_verbs_choice_mode, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_irregular_verbs_choice_mode);
        modesLayout = (TableLayout) rootView.findViewById(R.id.f_irregular_verbs_choice_mode_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_irregular_verbs_choice_mode_title);
        submit = (Button) rootView.findViewById(R.id.f_irregular_verbs_choice_mode_submit);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        initModeButtons();
        addModeButtonsToTable();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        title.setTypeface(Font.TYPEFACE_MONTSERRAT);
        submit.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    private void initModeButtons() {
        for(IrregularVerbsLearningMode mode : IrregularVerbsLearningMode.values()) {
            modes.add(new IrregularVerbsModeButton(getContext(), dataParams, mode, modes));
        }
    }

    private void addModeButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (IrregularVerbsModeButton modeButton : modes) {
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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrregularVerbsChoiceLengthFragment lengthFragment = IrregularVerbsChoiceLengthFragment.newInstance(dataParams.toString());
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, lengthFragment, FragmentUtil.F_IRREGULAR_VERBS_CHOICE_LENGTH).commit();
            }
        });
    }

}
