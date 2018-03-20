package pl.understandable.understandable_app.fragments.about_app;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.activities.MainActivity;
import pl.understandable.understandable_app.utils.RateAppUtil;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin Zielonka
 */

public class AboutAppFragment extends Fragment {

    private RelativeLayout mainLayout;
    private TextView appName, motd, statuteInfo, googleAnalyticsInfo, foot1, foot2;

    public AboutAppFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_about_app, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_about_app);
        appName = (TextView) rootView.findViewById(R.id.f_about_app_app_name);
        motd = (TextView) rootView.findViewById(R.id.f_about_app_motd);
        statuteInfo = (TextView) rootView.findViewById(R.id.f_about_app_statute_info);
        googleAnalyticsInfo = (TextView) rootView.findViewById(R.id.f_about_app_google_analytics_info);
        foot1 = (TextView) rootView.findViewById(R.id.f_about_app_foot_1);
        foot2 = (TextView) rootView.findViewById(R.id.f_about_app_foot_2);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareHyperLink();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        appName.setTypeface(typeface);
        motd.setTypeface(typeface);
        statuteInfo.setTypeface(typeface);
        googleAnalyticsInfo.setTypeface(typeface);
        foot1.setTypeface(typeface);
        foot2.setTypeface(typeface);
    }

    private void prepareHyperLink() {
        String text = "Korzystając z aplikacji, akceptujesz\n<style='text-decoration:none;color:#f78aae;><a href='https://m.understandable.pl/regulamin.php'>regulamin</a></style>";
        statuteInfo.setText(Html.fromHtml(text));
        statuteInfo.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
