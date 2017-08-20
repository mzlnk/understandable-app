package pl.understandable.understandable_dev_app.fragments.grammar.preview;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.database.repository.GrammarEntitiesRepository;
import pl.understandable.understandable_dev_app.fragments.grammar.choice.GrammarChoiceModeFragment;
import pl.understandable.understandable_dev_app.utils.ThemeUtil;
import pl.understandable.understandable_dev_app.utils.font.Font;

import static android.content.Context.WINDOW_SERVICE;
import static pl.understandable.understandable_dev_app.utils.FragmentUtil.F_GRAMMAR_SET_PREVIEW;
import static pl.understandable.understandable_dev_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class GrammarSetPreviewFragment extends Fragment {

    public static final String ID_PARAM = "grammar.set.preview.idParam";
    public static final String NAME_PARAM = "grammar.set.preview.nameParam";

    private RelativeLayout mainLayout;
    private WebView webView;
    private TextView title;
    private Button start;

    private String id;
    private String name;

    public GrammarSetPreviewFragment() {
        // Required empty public constructor
    }

    public static GrammarSetPreviewFragment newInstance(String id, String name) {
        GrammarSetPreviewFragment fragment = new GrammarSetPreviewFragment();
        Bundle args = new Bundle();
        args.putString(ID_PARAM, id);
        args.putString(NAME_PARAM, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            id = getArguments().getString(ID_PARAM);
            name = getArguments().getString(NAME_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the wordLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_grammar_set_preview, container, false);
        loadViewsFromXml(rootView);
        setAnimation();
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_grammar_set_preview);
        webView = (WebView) rootView.findViewById(R.id.f_grammar_set_preview_webview);
        title = (TextView) rootView.findViewById(R.id.f_grammar_set_preview_title);
        start = (Button) rootView.findViewById(R.id.f_grammar_set_preview_button_start_learning);
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        mainLayout.setAnimation(anim);
    }

    private void prepareLayout() {
        setFonts();
        prepareButtons();
        setTitle();
        prepareWebView();
    }

    private void setFonts() {
        Typeface typeFace = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeFace);
        start.setTypeface(typeFace);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            start.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            start.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
    }

    private void setTitle() {
        title.setText(name);
    }

    private void prepareWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        ThemeUtil themeUtil = new ThemeUtil(getContext());
        String theme = themeUtil.isDefaultTheme() ? "default" : "night";
        String width = String.valueOf(getDisplayWidth());

        String url = "http://dl.understandable.pl?id=" + id + "&theme=" + theme + "&width=" + width;
        webView.loadUrl(url);

    }

    private int getDisplayWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) getContext().getSystemService(WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        int width = Math.round(dm.widthPixels / dm.density);
        if(width >= 900) {
            return 900;
        } else if(width >= 800) {
            return 800;
        } else if(width >= 720) {
            return 720;
        } else if(width >= 600) {
            return 600;
        } else {
            return 0;
        }
    }

    private void addListeners() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GrammarEntitiesRepository.create(getContext(), id);
                GrammarChoiceModeFragment grammarChoiceModeFragment = GrammarChoiceModeFragment.newInstance(id, name);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, grammarChoiceModeFragment, redirectTo(F_GRAMMAR_SET_PREVIEW, id, name)).commit();
            }
        });
    }

}
