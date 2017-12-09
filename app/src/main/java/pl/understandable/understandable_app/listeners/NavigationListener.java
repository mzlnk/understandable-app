package pl.understandable.understandable_app.listeners;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.activities.MainActivity;
import pl.understandable.understandable_app.fragments.custom_words.other.CustomWordsSetsListFragment;
import pl.understandable.understandable_app.fragments.custom_words.other.DownloadCustomWordsSetFragment;
import pl.understandable.understandable_app.fragments.grammar.preview.GrammarSetsListFragment;
import pl.understandable.understandable_app.fragments.irregular_verbs.choice.IrregularVerbsChoiceModeFragment;
import pl.understandable.understandable_app.fragments.phrases.choice.PhrasesChoiceCategoryFragment;
import pl.understandable.understandable_app.fragments.words.choice.WordsChoiceCategoryFragment;
import pl.understandable.understandable_app.fragments.theme.ThemeChoiceFragment;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.webservice.OpenUserStatsFragment;

import static pl.understandable.understandable_app.utils.FragmentUtil.*;

/**
 * Created by Marcin Zielonka on 2017-06-15.
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
            fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_START)).commit();
        }
        else if(id == R.id.navigation_irregular_verbs) {
            IrregularVerbsChoiceModeFragment fragment = new IrregularVerbsChoiceModeFragment();
            fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_START)).commit();
        }
        else if(id == R.id.navigation_phrases) {
            PhrasesChoiceCategoryFragment fragment = new PhrasesChoiceCategoryFragment();
            fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_START)).commit();
        }
        else if(id == R.id.navigation_grammar) {
            GrammarSetsListFragment fragment = new GrammarSetsListFragment();
            fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_START)).commit();
        }
        else if(id == R.id.navigation_download_custom_words_set) {
            DownloadCustomWordsSetFragment fragment = new DownloadCustomWordsSetFragment();
            fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_START)).commit();
        }
        else if(id == R.id.navigation_custom_words_sets) {
            CustomWordsSetsListFragment fragment = new CustomWordsSetsListFragment();
            fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_START)).commit();
        } else if(id == R.id.navigation_user) {
            if(!UserManager.isUserSignedIn()) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(context.getString(R.string.server_client_id)).build();
                GoogleSignInClient client = GoogleSignIn.getClient(context, gso);
                Intent signInIntent = client.getSignInIntent();
                activity.startActivityForResult(signInIntent, ((MainActivity) activity).RC_SIGN_IN);
            } else {
                new OpenUserStatsFragment(context, fragmentManager).execute();
            }
        } else if(id== R.id.navigation_help) {
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://understandable.pl/help.php"));
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(browserIntent);
            } catch(ActivityNotFoundException e) {
                Toast.makeText(context, "Wystąpił problem, skontaktuj się z działem pomocy", Toast.LENGTH_SHORT).show();
            }

        } else if(id == R.id.navigation_themes) {
            ThemeChoiceFragment fragment = new ThemeChoiceFragment();
            fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_START)).setCustomAnimations(R.anim.fade01, FragmentTransaction.TRANSIT_NONE).commit();
        }

        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}