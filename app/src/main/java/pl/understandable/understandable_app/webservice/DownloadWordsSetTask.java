package pl.understandable.understandable_app.webservice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.database.entity.CustomWordsSetEntity;
import pl.understandable.understandable_app.database.repository.CustomWordsSetsRepository;
import pl.understandable.understandable_app.fragments.custom_words.other.CustomWordsSetsListFragment;
import pl.understandable.understandable_app.user.RequestExecutor;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.user.requests.AddFollowedTest;
import pl.understandable.understandable_app.user.requests.AddWordsSetDownloaded;
import pl.understandable.understandable_app.utils.NetworkUtil;

import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class DownloadWordsSetTask extends AsyncTask<String, Void, Integer> {

    private Context context;
    private FragmentManager fragmentManager;
    private String redirectAfterShowWordsSetsList;

    public DownloadWordsSetTask(Context context, FragmentManager fragmentManager, String redirectAfterShowWordsSetsList) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.redirectAfterShowWordsSetsList = redirectAfterShowWordsSetsList;
    }

    @Override
    protected Integer doInBackground(String... ids) {
        String id = ids[0].toUpperCase();
        if(!NetworkUtil.isNetworkAvailable((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))) {
            return 4;
        }
        if(!idExists(id)) {
            return 1;
        }
        if(!downloadWordsSetData(id)) {
            return 3;
        }
        if(!downloadFile(id)) {
            return 2;
        }
        addToFollowedWordsSets(id);

        return 0;
    }

    @Override
    protected void onPostExecute(Integer result) {
        switch(result) {
            case 1:
                Toast.makeText(context, "Podany kod nie istnieje", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(context, "Pobranie zestawu nie powiodło się", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(context, "Pobranie zestawu nie powiodło się", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(context, "Brak połączenia z Internetem", Toast.LENGTH_SHORT).show();
                break;
            case 0:
                RequestExecutor.offerRequest(new AddWordsSetDownloaded());
                CustomWordsSetsListFragment fragment = new CustomWordsSetsListFragment();
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(redirectAfterShowWordsSetsList)).commit();
                Toast.makeText(context, "Zestaw słówek został pobrany", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private boolean idExists(String id) {
        try {
            URI uri = new URI("https://www.understandable.pl/resources/script/words_set_exist.php?id=" + id);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            httpPost.setHeader("Pragma", "no-cache");
            httpPost.setHeader("User-Agent", "");

            HttpResponse httpResponse = httpClient.execute(httpPost);
            String s = EntityUtils.toString(httpResponse.getEntity());
            if(!s.equals("exists")) {
                return false;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private boolean downloadFile(String id) {
        File dataDirectory = new File(context.getFilesDir(), "/words_sets");
        File output = new File(dataDirectory, id + ".json");

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://www.understandable.pl/resources/script/download_file.php?id=" + id);
            httpGet.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            httpGet.setHeader("Pragma", "no-cache");
            httpGet.setHeader("User-Agent", "");

            HttpResponse response = httpClient.execute(httpGet);
            BufferedInputStream in = new BufferedInputStream(response.getEntity().getContent());

            FileOutputStream out = new FileOutputStream(output);

            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = in.read(buffer, 0, buffer.length)) >= 0)
            {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private boolean downloadWordsSetData(String id) {
        try {
            URI uri = new URI("http://www.understandable.pl/resources/script/get_words_set_info.php?id=" + id);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            httpPost.setHeader("Pragma", "no-cache");
            httpPost.setHeader("User-Agent", "");

            HttpResponse httpResponse = httpClient.execute(httpPost);
            String result = EntityUtils.toString(httpResponse.getEntity());
            if(result.equals("error")) {
                return false;
            }
            result = result.replaceAll("\"", "\\\"");
            JSONObject jsonObject = new JSONObject(result);
            String name = jsonObject.getString("name");
            String description = jsonObject.getString("description");
            System.out.println("Name: " + name);
            System.out.println("Description: " + description);
            CustomWordsSetsRepository.addEntity(new CustomWordsSetEntity(id, name, description));
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

    private void addToFollowedWordsSets(String id) {
        RequestExecutor.offerRequest(new AddFollowedTest(id));
    }

}