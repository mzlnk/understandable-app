package pl.understandable.understandable_app.fragments.custom_words.other;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;
import pl.understandable.understandable_app.webservice.WebService;

/**
 * Created by Marcin Zielonka
 */

public class DownloadCustomWordsSetFragment extends Fragment {

    private RelativeLayout mainLayout;
    private EditText codeField;
    private Button download;
    private TextView title;

    private long lastClicked = 0;
    private static final long COOLDOWN_IN_MILLIS = 5000;

    public DownloadCustomWordsSetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_download_words_set, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_download_words_set);
        codeField = (EditText) rootView.findViewById(R.id.f_download_words_set_code_field);
        download = (Button) rootView.findViewById(R.id.f_download_words_set_button_download);
        title = (TextView) rootView.findViewById(R.id.f_download_words_set_title);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        prepareCodeField();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        codeField.setTypeface(typeface);
        download.setTypeface(typeface);
        title.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            download.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            download.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
    }

    private void prepareCodeField() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isNightTheme()) {
            Drawable bottomLine = codeField.getBackground();
            bottomLine.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            if(Build.VERSION.SDK_INT > 16) {
                codeField.setBackground(bottomLine);
            } else {
                codeField.setBackgroundDrawable(bottomLine);
            }
        }
    }

    private void addListeners() {
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long time = System.currentTimeMillis() - lastClicked;
                if(time < COOLDOWN_IN_MILLIS) {
                    Toast.makeText(getContext(), "Musisz odczekać " + (time / 1000) + " sekund", Toast.LENGTH_SHORT).show();
                    return;
                }
                lastClicked = System.currentTimeMillis();
                String code = codeField.getText().toString();
                code = code.toUpperCase();
                WebService.DownloadWordsSetTask task = (WebService.DownloadWordsSetTask) new WebService.DownloadWordsSetTask(getContext(), getFragmentManager()).execute(code);
                //todo: add task.cancel()
            }
        });
    }

}
