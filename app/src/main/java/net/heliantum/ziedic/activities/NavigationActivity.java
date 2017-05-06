package net.heliantum.ziedic.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import net.heliantum.ziedic.fragments.AboutAuthorFragment;
import net.heliantum.ziedic.R;
import net.heliantum.ziedic.fragments.WordsChoiceCategoryFragment;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.rgb(0, 46, 99));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.words) {
            WordsChoiceCategoryFragment fragment = new WordsChoiceCategoryFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.layout_for_fragments, fragment).commit();
        }
        else if(id == R.id.irregular_verbs) {
            Toast.makeText(getApplicationContext(), "Obecnie niedostępne", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.tenses) {
            Toast.makeText(getApplicationContext(), "Obecnie niedostępne", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.reported_speech) {
            Toast.makeText(getApplicationContext(), "Obecnie niedostępne", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.passive_voice) {
            Toast.makeText(getApplicationContext(), "Obecnie niedostępne", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.download_your_words) {
            Toast.makeText(getApplicationContext(), "Obecnie niedostępne", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.your_words) {
            Toast.makeText(getApplicationContext(), "Obecnie niedostępne", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.about_app) {
            AboutAuthorFragment fragment = new AboutAuthorFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.layout_for_fragments, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
