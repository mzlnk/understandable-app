package net.heliantum.understandable.fragments.other;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import net.heliantum.understandable.R;
import net.heliantum.understandable.utils.Debug;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */

public class AboutAuthorFragment extends Fragment {

    private Button debug;

    public AboutAuthorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.f_about_author, container, false);

        debug = (Button) rootView.findViewById(R.id.debug);

        debug.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(Debug.toShow) Debug.toShow = false;
                else Debug.toShow = true;

                String status = "";
                if(Debug.toShow) status = "włączony";
                else status = "wyłączony";

                Toast.makeText(getContext(), "Tryb DEBUG: " + status, Toast.LENGTH_SHORT).show();
            }

        });

        return rootView;
    }

}
