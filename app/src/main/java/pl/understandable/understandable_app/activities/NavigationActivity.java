package pl.understandable.understandable_app.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.themes.ThemeType;
import pl.understandable.understandable_app.fragments.start.StartFragment;
import pl.understandable.understandable_app.listeners.BackButtonListener;
import pl.understandable.understandable_app.listeners.NavigationListener;
import pl.understandable.understandable_app.utils.FragmentUtil;

/**
 * Created by Marcin Zielonka on 2017-11-07.
 */

public class NavigationActivity extends AppCompatActivity {

    public static NavigationActivity activity;

    public DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setTheme();
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.rgb(32, 32, 32));

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationListener(this));

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, new StartFragment()).commit();
        displayInfo();
    }

    @Override
    public void onBackPressed() {
        new BackButtonListener(getSupportFragmentManager()).onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setTheme() {
        String sharedPrefFileName = getString(R.string.sp_preferences_file_key);
        String sharedPrefThemeKey = getString(R.string.sp_theme_key);
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPrefFileName, Context.MODE_PRIVATE);
        int themeId = sharedPreferences.getInt(sharedPrefThemeKey, ThemeType.THEME_DAY.getThemeId());
        setTheme(themeId);
    }

    private void displayInfo() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        int width = Math.round(dm.widthPixels / dm.density);
        int height = Math.round(dm.heightPixels / dm.density);
        Toast.makeText(getApplicationContext(), "width: " + width + "dp\nheight: " + height + "dp", Toast.LENGTH_LONG).show();
    }

}
