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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.database.entity.CustomWordsSetEntity;
import pl.understandable.understandable_app.database.repository.temp.AllCustomWordsSetsRepository;
import pl.understandable.understandable_app.fragments.custom_words.other.AllCustomWordsSetsListFragment;
import pl.understandable.understandable_app.utils.NetworkUtil;

import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class DownloadWordsSetsDataTask extends AsyncTask<Void, Void, Integer> {

    private Context context;
    private FragmentManager fragmentManager;
    private String redirect;

    public DownloadWordsSetsDataTask(Context context, FragmentManager fragmentManager, String redirect) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.redirect = redirect;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        if(!NetworkUtil.isNetworkAvailable((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))) {
            return 1;
        }
        if(!downloadWordsSetsData()) {
            return 2;
        }
        return 0;
    }

    @Override
    protected void onPostExecute(Integer result) {
        switch(result) {
            case 1:
                Toast.makeText(context, "Brak połączenia z Internetem", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(context, "Wczytywanie listy zestawów nie powiodło się", Toast.LENGTH_SHORT).show();
                break;
            case 0:
                AllCustomWordsSetsListFragment fragment = AllCustomWordsSetsListFragment.newInstance("", 0);
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(redirect)).commit();
                break;
        }
    }

    private boolean downloadWordsSetsData() {
        try {
            URI uri = new URI("https://www.understandable.pl/resources/script/get_all_words_sets_info.php");
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(uri);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            String result = EntityUtils.toString(httpResponse.getEntity());
            if(result.equals("error")) {
                return false;
            }
            result = result.replaceAll("\"", "\\\"");
            JSONArray jsonArray = new JSONArray(result);
            AllCustomWordsSetsRepository.clearRepository();
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String description = jsonObject.getString("description");
                AllCustomWordsSetsRepository.addWordsSet(new CustomWordsSetEntity(id, name, description));
            }
            return true;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

}