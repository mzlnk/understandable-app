package pl.understandable.understandable_dev_app.fragments.irregular_verbs.choice;

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
import android.widget.SeekBar;
import android.widget.TextView;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.entities_data.irregular_verbs_data.IrregularVerbsListData;
import pl.understandable.understandable_dev_app.data.entities_data.irregular_verbs_data.IrregularVerbsRepetitionData;
import pl.understandable.understandable_dev_app.data.params.IrregularVerbsDataParams;
import pl.understandable.understandable_dev_app.fragments.irregular_verbs.list.IrregularVerbsListFragment;
import pl.understandable.understandable_dev_app.fragments.irregular_verbs.repetition.IrregularVerbsRepetitionFragment;
import pl.understandable.understandable_dev_app.utils.AdUtil;
import pl.understandable.understandable_dev_app.utils.FragmentUtil;
import pl.understandable.understandable_dev_app.utils.ThemeUtil;
import pl.understandable.understandable_dev_app.utils.font.Font;

import static pl.understandable.understandable_dev_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_dev_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class IrregularVerbsChoiceLengthFragment extends Fragment {

    private static final String DATA_PARAM = "irregular.verbs.choice.length.dataParam";

    private RelativeLayout mainLayout;
    private TextView title, amountInfo;
    private Button submit, back;
    private SeekBar amountAdjust;

    private IrregularVerbsDataParams dataParams;

    public IrregularVerbsChoiceLengthFragment() {
        // Required empty public constructor
    }

    public static IrregularVerbsChoiceLengthFragment newInstance(String param) {
        IrregularVerbsChoiceLengthFragment fragment = new IrregularVerbsChoiceLengthFragment();
        Bundle args = new Bundle();
        args.putString(DATA_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            dataParams = new IrregularVerbsDataParams().fromString(getArguments().getString(DATA_PARAM));
        }
        if(dataParams == null) {
            dataParams = new IrregularVerbsDataParams();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.f_irregular_verbs_choice_length, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_irregular_verbs_choice_length);
        amountInfo = (TextView) rootView.findViewById(R.id.f_irregular_verbs_choice_length_size_info);
        amountAdjust = (SeekBar) rootView.findViewById(R.id.f_irregular_verbs_choice_length_size_adjust);
        title = (TextView) rootView.findViewById(R.id.f_irregular_verbs_choice_length_title);
        back = (Button) rootView.findViewById(R.id.f_irregular_verbs_choice_length_back);
        submit = (Button) rootView.findViewById(R.id.f_irregular_verbs_choice_length_submit);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        prepareSeekBar();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        back.setTypeface(typeface);
        submit.setTypeface(typeface);
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

    private void prepareSeekBar() {
        final IrregularVerbsChoiceLengthFragment.StartPosition startPos = new IrregularVerbsChoiceLengthFragment.StartPosition();
        startPos.setPos(1);
        amountAdjust.setMax(dataParams.getMaximumAvailableWordsAmount() - startPos.getPos());
        dataParams.setSize(startPos.getPos());
        amountAdjust.setProgress(dataParams.size - startPos.getPos());
        amountInfo.setText(String.valueOf(dataParams.size));

        amountAdjust.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                amountInfo.setText(String.valueOf(i + startPos.getPos()));
                dataParams.setSize(i + startPos.getPos());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void addListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrregularVerbsChoiceModeFragment modeFragment = IrregularVerbsChoiceModeFragment.newInstance(dataParams.toString());
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, modeFragment, redirectTo(F_START)).commit();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdUtil.showAd();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                switch (dataParams.mode) {
                    case REPETITION:
                        IrregularVerbsRepetitionData.createRepetitionDataFromParams(dataParams);
                        transaction.replace(R.id.layout_for_fragments, new IrregularVerbsRepetitionFragment(), redirectTo(F_START));
                        break;
                    case LIST:
                        IrregularVerbsListData.createListDataFromParams(dataParams);
                        transaction.replace(R.id.layout_for_fragments, new IrregularVerbsListFragment(), redirectTo(F_START));
                        break;
                }
                transaction.commit();
            }
        });
    }

    private class StartPosition {

        private int pos;

        public void setPos(int pos) {
            this.pos = pos;
        }

        public int getPos() {
            return pos;
        }
    }

}
