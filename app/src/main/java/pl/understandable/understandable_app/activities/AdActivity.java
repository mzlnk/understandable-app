package pl.understandable.understandable_app.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import pl.understandable.understandable_app.R;

public class AdActivity extends AppCompatActivity {

    private ImageView back;
    private LinearLayout mainRedirect;
    private View redirect1, redirect2, redirect3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        loadViewsFromXl();
        addListeners();    }

    private void loadViewsFromXl() {
        back = (ImageView) findViewById(R.id.f_ad_button_back);
        mainRedirect = (LinearLayout) findViewById(R.id.f_ad_main_redirect);
        redirect1 = findViewById(R.id.f_ad_redirect_1);
        redirect2 = findViewById(R.id.f_ad_redirect_2);
        redirect3 = findViewById(R.id.f_ad_redirect_3);
    }

    private void addListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdActivity.this.finish();
            }
        });

        mainRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCS1GVyWKMr2AuWLyCNuylRw"));
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(browserIntent);
                } catch(ActivityNotFoundException e) {
                }
            }
        });

        redirect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://soundcloud.com/partydestroyer"));
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(browserIntent);
                } catch(ActivityNotFoundException e) {
                }
            }
        });

        redirect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/PartyDestroyer/"));
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(browserIntent);
                } catch(ActivityNotFoundException e) {
                }
            }
        });

        redirect3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCS1GVyWKMr2AuWLyCNuylRw"));
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(browserIntent);
                } catch(ActivityNotFoundException e) {
                }
            }
        });
    }
}
