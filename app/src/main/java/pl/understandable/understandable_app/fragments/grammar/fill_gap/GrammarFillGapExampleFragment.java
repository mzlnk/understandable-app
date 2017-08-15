package pl.understandable.understandable_app.fragments.grammar.fill_gap;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.grammar.GrammarFillGapData;
import pl.understandable.understandable_app.utils.ColorUtil;

/**
 * Created by Marcin Zielonka
 */

public class GrammarFillGapExampleFragment extends Fragment {

    public static final String POSITION_PARAM = "grammar.fill_gap.example.positionParam";
    public static Map<Integer, TextView> sentences = new HashMap<>();

    private GrammarFillGapData fillGapData;

    private TextView sentence;

    public int position;

    public GrammarFillGapExampleFragment() {
        // Required empty public constructor
    }

    public static GrammarFillGapExampleFragment newInstance(int position) {
        GrammarFillGapExampleFragment fragment = new GrammarFillGapExampleFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION_PARAM, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            position = getArguments().getInt(POSITION_PARAM);
        }
        fillGapData = GrammarFillGapData.getFillGapData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the wordLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_grammar_fill_gap_example, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sentences.remove(position);
    }

    private void loadViewsFromXml(View rootView) {
        sentence = (TextView) rootView.findViewById(R.id.f_grammar_fill_gap_example_sentence);
        sentences.put(position, sentence);
    }

    private void prepareLayout() {
        setFonts();
        setWords();
    }

    private void setFonts() {
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular-PL.ttf");
        sentence.setTypeface(typeFace);
    }

    private void setWords() {
        sentence.setText(fillGapData.getEntities().get(position).getSentence());
        ColorUtil colorUtil = new ColorUtil(getContext());
        sentence.setTextColor(colorUtil.getColor(R.attr.text_1_color));
    }

}
