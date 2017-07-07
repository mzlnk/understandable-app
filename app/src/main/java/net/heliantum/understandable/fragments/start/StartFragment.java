package net.heliantum.understandable.fragments.start;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.heliantum.understandable.R;
import net.heliantum.understandable.activities.NavigationActivity;
import net.heliantum.understandable.utils.font.Font;

public class StartFragment extends Fragment {

    private RelativeLayout mainLayout;
    private TextView title;

    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_start, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();
        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_start);
        title = (TextView) rootView.findViewById(R.id.f_start_title);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        title.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    private void addListeners() {
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationActivity.activity.drawer.openDrawer(GravityCompat.START);
            }
        });
    }

}
