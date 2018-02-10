package pl.understandable.understandable_app.webservice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.dialogs.AnnouncementDialog;
import pl.understandable.understandable_app.fragments.grammar.preview.GrammarSetPreviewFragment;
import pl.understandable.understandable_app.utils.NetworkUtil;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_GRAMMAR_SETS_LIST;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka on 2018-02-10.
 */

public class ShowGrammarSetPreviewTask extends AsyncTask<Void, Void, Integer> {

    private Context context;
    private FragmentManager fragmentManager;
    private String id;
    private String name;

    public ShowGrammarSetPreviewTask(Context context, FragmentManager fragmentManager, String id, String name) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.id = id;
        this.name = name;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        if(!NetworkUtil.isNetworkAvailable((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))) {
            return 1;
        }
        return 0;
    }

    @Override
    protected void onPostExecute(Integer result) {
        switch(result) {
            case 1:
                Toast.makeText(context, "Aby wyświetlić zawartość, wymagane jest połączenie z Internetem", Toast.LENGTH_SHORT).show();
                break;
            case 0:
                GrammarSetPreviewFragment fragment = GrammarSetPreviewFragment.newInstance(id, name);
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_GRAMMAR_SETS_LIST)).commit();
                break;
        }
    }

}
