package pl.understandable.understandable_app.webservice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.database.entity.CustomWordsSetEntity;
import pl.understandable.understandable_app.database.repository.temp.AllCustomWordsSetsRepository;
import pl.understandable.understandable_app.database.repository.temp.FollowedCustomWordsSetsRepository;
import pl.understandable.understandable_app.fragments.user.UserFragment;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.utils.NetworkUtil;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_USER;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka on 2017-12-03.
 */

public class DownloadFollowedWordsSetsDataTask extends AsyncTask<Void, Void, Integer> {

    private Context context;
    private FragmentManager fragmentManager;

    public DownloadFollowedWordsSetsDataTask(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        if(!NetworkUtil.isNetworkAvailable((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE))) {
            return 1;
        }
        if(!downloadFollowedWordsSetsData()) {
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
                UserFragment fragment = new UserFragment();
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_USER)).commit();
                break;
        }
    }

    private boolean downloadFollowedWordsSetsData() {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("https://www.understandable.pl/resources/script/get_specific_words_sets_info.php");

            String data = "[";
            for(String code : UserManager.getUser().getAllDownloadedWordsSets()) {
                data += "\"" + code + "\",";
            }
            data = data.substring(0, data.length() - 1);
            data += "]";

            List valuePairs = new ArrayList(2);
            valuePairs.add(new BasicNameValuePair("data", data));
            httpPost.setEntity(new UrlEncodedFormEntity(valuePairs));

            HttpResponse httpResponse = client.execute(httpPost);
            String result = EntityUtils.toString(httpResponse.getEntity());

            result = result.replaceAll("\"", "\\\"");
            JSONArray jsonArray = new JSONArray(result);
            FollowedCustomWordsSetsRepository.clearRepository();
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String description = jsonObject.getString("description");
                AllCustomWordsSetsRepository.addWordsSet(new CustomWordsSetEntity(id, name, description));
            }
            return true;
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
