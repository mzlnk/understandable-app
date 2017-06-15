package net.heliantum.ziedic.listeners;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.fragments.choice.WordsChoiceCategoryFragment;
import net.heliantum.ziedic.fragments.other.AboutAuthorFragment;

/**
 * Created by Marcin on 2017-06-15.
 */

public class NavigationListener implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentActivity activity;
    private Context context;
    private FragmentManager fragmentManager;

    public NavigationListener(FragmentActivity activity) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.fragmentManager = activity.getSupportFragmentManager();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.words) {
            WordsChoiceCategoryFragment fragment = new WordsChoiceCategoryFragment();
            fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment).commit();
        }
        else if(id == R.id.irregular_verbs) {
            Toast.makeText(context, "Obecnie niedostępne", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.tenses) {
            Toast.makeText(context, "Obecnie niedostępne", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.reported_speech) {
            Toast.makeText(context, "Obecnie niedostępne", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.passive_voice) {
            Toast.makeText(context, "Obecnie niedostępne", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.download_your_words) {
            Toast.makeText(context, "Obecnie niedostępne", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.your_words) {
            Toast.makeText(context, "Obecnie niedostępne", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.about_app) {
            AboutAuthorFragment fragment = new AboutAuthorFragment();
            fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
