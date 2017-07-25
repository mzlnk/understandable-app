package net.heliantum.understandable.webservice;

import android.content.Context;
import android.os.AsyncTask;

import net.heliantum.understandable.utils.json_creator.CustomWordsSetJsonCreator;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

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

        public DownloadWordsSetTask(Context context) {
            this.context = context;
        }

        @Override
        protected Integer doInBackground(String... ids) {
            String id = ids[0].toUpperCase();
            System.out.println("==============================");
            System.out.println("Checking if id exists...");
            System.out.println("==============================");
            try {
                URI uri = new URI("http://www.understandable.pl/resources/script/words_set_exist.php/?id=" + id);
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(uri);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                String s = EntityUtils.toString(httpResponse.getEntity());
                if(!s.equals("exists")) {
                    return 1;
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("==============================");
            System.out.println("Downloading file...");
            System.out.println("==============================");

            File tempDataDirectory = new File(context.getFilesDir(), "/words_sets/tmp/");
            File output = new File(tempDataDirectory, id + ".xls");

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
                return 2;
            } catch (IOException e) {
                e.printStackTrace();
                return 2;
            }

            System.out.println("==============================");
            System.out.println("File name: " + output.getName());
            System.out.println("File size: " + output.length() + " bytes");
            System.out.println("Creating JSON file from XLS file...");
            System.out.println("==============================");
            CustomWordsSetJsonCreator creator = new CustomWordsSetJsonCreator(context, output);
            creator.create();
            output.delete();
            return 0;
        }

        @Override
        protected void onPostExecute(Integer result) {
            switch(result) {
                case 1:
                    System.out.println("==============================");
                    System.out.println("Code does not exist in database");
                    System.out.println("==============================");
                    break;
                case 2:
                    System.out.println("==============================");
                    System.out.println("");
                    System.out.println("File not downloaded");
                    System.out.println("");
                    System.out.println("==============================");
                    break;
                case 0:
                    System.out.println("==============================");
                    System.out.println("");
                    System.out.println("File downloaded and JSON file created");
                    System.out.println("");
                    System.out.println("==============================");
                    break;
            }
        }

    }

}
