package net.heliantum.understandable.fragments.irregular_verbs.list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.heliantum.understandable.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IrregularVerbsListFragment extends Fragment {


    public IrregularVerbsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.f_irregular_verbs_list, container, false);
    }

}
