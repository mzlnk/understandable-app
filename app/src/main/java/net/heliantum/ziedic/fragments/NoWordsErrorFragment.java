package net.heliantum.ziedic.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import net.heliantum.ziedic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoWordsErrorFragment extends Fragment {

    private View rootView;
    private RelativeLayout mainLayout;

    private Button back;

    public NoWordsErrorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.f_no_words_error, container, false);
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.fragment_no_words_error_fragment_layout);
        back = (Button) rootView.findViewById(R.id.back);

        setAnimation();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        return rootView;
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        mainLayout.setAnimation(anim);
    }

}
