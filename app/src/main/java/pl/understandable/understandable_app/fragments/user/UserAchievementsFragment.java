package pl.understandable.understandable_app.fragments.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.understandable.understandable_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserAchievementsFragment extends Fragment {


    public UserAchievementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.f_user_achievements, container, false);
    }

}
