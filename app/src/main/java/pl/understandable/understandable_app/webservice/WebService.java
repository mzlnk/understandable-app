package pl.understandable.understandable_app.webservice;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.database.entity.CustomWordsSetEntity;
import pl.understandable.understandable_app.database.repository.CustomWordsSetsRepository;
import pl.understandable.understandable_app.fragments.custom_words.other.CustomWordsSetsListFragment;
import pl.understandable.understandable_app.utils.FragmentUtil;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Marcin on 2017-07-25.
 */

public class WebService {

    public static class DownloadWordsSetTask extends AsyncTask<String, Void, Integer> {

        private Context context;
        private FragmentManager fragmentManager;

        public DownloadWordsSetTask(Context context, FragmentManager fragmentManager) {
            this.context = context;
            this.fragmentManager = fragmentManager;
        }

        @Override
        protected Integer doInBackground(String... ids) {
            String id = ids[0].toUpperCase();
            if(!idExists(id)) {
                return 1;
            }
            if(!downloadFile(id)) {
                return 2;
            }
            if(!downloadWordsSetData(id)) {
                return 3;
            }

            System.out.println("TEST");
            System.out.println("Files:");
            for(File f : new File(context.getFilesDir(), "/words_sets/").listFiles()) {
                System.out.println(f.getName());
            }

            return 0;
        }

        @Override
        protected void onPostExecute(Integer result) {
            switch(result) {
                case 1:
                    System.out.println("==============================");
                    System.out.println("Code does not exist in database");
                    System.out.println("==============================");
                    Toast.makeText(context, "Podany kod nie istnieje", Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    System.out.println("==============================");
                    System.out.println("");
                    System.out.println("File not downloaded");
                    System.out.println("");
                    System.out.println("==============================");
                    Toast.makeText(context, "Pobranie zestawu nie powiodło się", Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    System.out.println("==============================");
                    System.out.println("");
                    System.out.println("File data not downloaded/saved");
                    System.out.println("");
                    System.out.println("==============================");
                    Toast.makeText(context, "Pobranie zestawu nie powiodło się", Toast.LENGTH_LONG).show();
                    break;
                case 0:
                    System.out.println("==============================");
                    System.out.println("");
                    System.out.println("File downloaded and JSON file created");
                    System.out.println("");
                    System.out.println("==============================");
                    CustomWordsSetsListFragment fragment = new CustomWordsSetsListFragment();
                    fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, FragmentUtil.F_CUSTOM_WORDS_SETS_LIST).commit();
                    Toast.makeText(context, "Zestaw słówek został pobrany", Toast.LENGTH_LONG).show();
                    break;
            }
        }

        private boolean idExists(String id) {
            System.out.println("==============================");
            System.out.println("Checking if id exists...");
            System.out.println("==============================");
            try {
                URI uri = new URI("http://www.understandable.pl/resources/script/words_set_exist.php?id=" + id);
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(uri);
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
            System.out.println("==============================");
            System.out.println("Downloading file...");
            System.out.println("==============================");

            File dataDirectory = new File(context.getFilesDir(), "/words_sets");
            File output = new File(dataDirectory, id + ".json");

            try {
                URL url = new URL("http://www.understandable.pl/resources/script/download_file.php?id=" + id);

                BufferedInputStream in = new BufferedInputStream(url.openStream());
                FileOutputStream out = new FileOutputStream(output);

                byte[] buffer = new byte[1024];
                int bytesRead = 0;
                while ((bytesRead = in.read(buffer, 0, buffer.length)) >= 0)
                {
                    out.write(buffer, 0, bytesRead);
                }
                out.close();

                if(in != null) {
                    in.close();
                }
                if(out != null) {
                    out.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            System.out.println("==============================");
            System.out.println("File name: " + output.getName());
            System.out.println("File size: " + output.length() + " bytes");
            System.out.println("==============================");

            return true;
        }

        private boolean downloadWordsSetData(String id) {
            System.out.println("==============================");
            System.out.println("Downloading words set data...");
            System.out.println("==============================");
            try {
                URI uri = new URI("http://www.understandable.pl/resources/script/get_words_set_info.php?id=" + id);
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(uri);
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

    }

}
