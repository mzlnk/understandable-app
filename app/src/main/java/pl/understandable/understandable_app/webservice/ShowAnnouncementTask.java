package pl.understandable.understandable_app.webservice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import pl.understandable.understandable_app.dialogs.AnnouncementDialog;
import pl.understandable.understandable_app.utils.NetworkUtil;

/**
 * Created by Marcin Zielonka on 2018-02-05.
 */

public class ShowAnnouncementTask extends AsyncTask<Void, Void, Integer> {

    private Context context;

    public ShowAnnouncementTask(Context context) {
        this.context = context;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        if(!NetworkUtil.isNetworkAvailable((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))) {
            return 1;
        }
        if(!announcementExists()) {
            return 2;
        }
        return 0;
    }

    @Override
    protected void onPostExecute(Integer result) {
        if(result == 0) {
            new AnnouncementDialog(context).show();
        }
    }

    private boolean announcementExists() {
        try {
            URI uri = new URI("https://dl.understandable.pl/announcement.php");
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            httpPost.setHeader("Pragma", "no-cache");

            HttpResponse httpResponse = httpClient.execute(httpPost);
            String result = EntityUtils.toString(httpResponse.getEntity());
            if(result.equals("not available")) {
                return false;
            } else {
                return true;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
