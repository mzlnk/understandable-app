package pl.understandable.understandable_app.fragments.grammar;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.utils.ThemeUtil;

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
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular-PL.ttf");
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

        String url = "http://dl.understandable.pl?id=" + id + "&theme=" + theme;
        webView.loadUrl(url);

    }

    private void addListeners() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Obecnie niedostępne", Toast.LENGTH_LONG).show();
            }
        });
    }

}
