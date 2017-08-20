package pl.understandable.understandable_dev_app.fragments.pro;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.utils.ThemeUtil;
import pl.understandable.understandable_dev_app.utils.font.Font;

/**
 * Created by Marcin Zielonka
 */

public class UnderstandableProInfoFragment extends Fragment {

    private RelativeLayout mainLayout;
    private TextView title, message;
    private Button submit;

    public UnderstandableProInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_understandable_pro_info, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        prepareButtons();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_understandable_pro_info);
        title = (TextView) rootView.findViewById(R.id.f_understandable_pro_info_title);
        message = (TextView) rootView.findViewById(R.id.f_understandable_pro_info_message);
        submit = (Button) rootView.findViewById(R.id.f_understandable_pro_info_button_submit);
    }

    private void prepareLayout() {
        setAnimation();
        prepareButtons();
        setFonts();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        message.setTypeface(typeface);
        submit.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            submit.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            submit.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
    }

    private void addListeners() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToPlayStore();
            }
        });
    }

    private void redirectToPlayStore() {
        String id = "pl.understandable.understandable_pro_app";
        Uri uri = Uri.parse("market://details?id=" + id);
        Intent toPlayStore = new Intent(Intent.ACTION_VIEW, uri);
        toPlayStore.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

        try {
            getContext().startActivity(toPlayStore);
        } catch(ActivityNotFoundException e) {
            getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + id)));
        }
    }

}
