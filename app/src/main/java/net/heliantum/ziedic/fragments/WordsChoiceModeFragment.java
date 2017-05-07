package net.heliantum.ziedic.fragments;


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

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.data.DataParams;
import net.heliantum.ziedic.data.enums.LearningMode;
import net.heliantum.ziedic.fragments.utils.wordschoice.ModeButton;
import net.heliantum.ziedic.utils.font.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsChoiceModeFragment extends Fragment {

    private static final String DATA_PARAM = "mode.dataParam";

    private RelativeLayout mainLayout;
    private TableLayout modesLayout;
    private TextView title;
    private Button submit, back;

    private List<ModeButton> modes = new ArrayList<>();
    private DataParams dataParams;

    public WordsChoiceModeFragment() {
        // Required empty public constructor
    }

    public static WordsChoiceModeFragment newInstance(String param) {
        WordsChoiceModeFragment fragment = new WordsChoiceModeFragment();
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
        // Inflate the modesLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_words_choice_mode, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.fragment_words_choice_mode_fragment_layout);
        modesLayout = (TableLayout) rootView.findViewById(R.id.f_words_choice_mode_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_words_choice_mode_title);
        submit = (Button) rootView.findViewById(R.id.f_words_choice_mode_submit);
        back = (Button) rootView.findViewById(R.id.f_words_choice_mode_back);
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
        back.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    private void initModeButtons() {
        for(LearningMode mode : LearningMode.values()) {
            modes.add(new ModeButton(getContext(), dataParams, mode, modes));
        }
    }

    private void addModeButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (ModeButton modeButton : modes) {
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
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordsChoiceLengthFragment typeFragment = new WordsChoiceLengthFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, typeFragment).addToBackStack(null).commit();
            }
        });
    }

}
