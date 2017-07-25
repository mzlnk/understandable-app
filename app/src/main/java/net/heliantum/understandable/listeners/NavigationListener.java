package net.heliantum.understandable.listeners;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import net.heliantum.understandable.R;
import net.heliantum.understandable.fragments.custom_words.DownloadWordsSet;
import net.heliantum.understandable.fragments.irregular_verbs.choice.IrregularVerbsChoiceModeFragment;
import net.heliantum.understandable.fragments.words.choice.WordsChoiceCategoryFragment;
import net.heliantum.understandable.fragments.theme.ThemeChoiceFragment;
import net.heliantum.understandable.utils.FragmentUtil;

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

        if(id == R.id.navigation_words) {
            WordsChoiceCategoryFragment fragment = new WordsChoiceCategoryFragment();
            fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, FragmentUtil.F_WORDS_CHOICE_CATEGORY).commit();
        }
        else if(id == R.id.navigation_irregular_verbs) {
            IrregularVerbsChoiceModeFragment fragment = new IrregularVerbsChoiceModeFragment();
            fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, FragmentUtil.F_IRREGULAR_VERBS_CHOICE_MODE).commit();
        }
        else if(id == R.id.navigation_grammar) {
            Toast.makeText(context, "Obecnie niedostępne", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.navigation_download_your_cards) {
            DownloadWordsSet fragment = new DownloadWordsSet();
            fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, FragmentUtil.F_IRREGULAR_VERBS_CHOICE_MODE).commit();
        }
        else if(id == R.id.navigation_your_words) {
            Toast.makeText(context, "Obecnie niedostępne", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.navigation_themes) {
            ThemeChoiceFragment fragment = new ThemeChoiceFragment();
            fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, FragmentUtil.F_THEME_CHOICE).setCustomAnimations(R.anim.fade01, FragmentTransaction.TRANSIT_NONE).commit();
        }

        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}