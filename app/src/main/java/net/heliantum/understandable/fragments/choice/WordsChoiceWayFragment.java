package net.heliantum.understandable.fragments.choice;

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
import net.heliantum.understandable.data.DataParams;
import net.heliantum.understandable.data.enums.LearningWay;
import net.heliantum.understandable.fragments.utils.choice.WayButton;
import net.heliantum.understandable.utils.font.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class WordsChoiceWayFragment extends Fragment {

    private static final String DATA_PARAM = "mode.dataParam";

    private RelativeLayout mainLayout;
    private TableLayout waysLayout;
    private TextView title;
    private Button submit, back;

    private List<WayButton> ways = new ArrayList<>();
    private DataParams dataParams;

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
            dataParams = DataParams.fromString(getArguments().getString(DATA_PARAM));
        }
        if(dataParams == null) {
            dataParams = new DataParams();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        title.setTypeface(Font.TYPEFACE_MONTSERRAT);
        submit.setTypeface(Font.TYPEFACE_MONTSERRAT);
        back.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    private void initWayButtons() {
        for(LearningWay way : LearningWay.values()) {
            ways.add(new WayButton(getContext(), dataParams, way, ways));
        }
    }

    private void addWayButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (WayButton wayButton : ways) {
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
                WordsChoiceTypeFragment typeFragment = WordsChoiceTypeFragment.newInstance(dataParams.toString());
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, typeFragment).commit();
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
