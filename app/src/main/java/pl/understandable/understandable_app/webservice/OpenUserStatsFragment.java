package pl.understandable.understandable_app.webservice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.fragments.user.UserStatsFragment;
import pl.understandable.understandable_app.utils.NetworkUtil;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka on 2017-12-09.
 */

public class OpenUserStatsFragment extends AsyncTask<Void, Void, Integer> {

    private Context context;
    private FragmentManager fragmentManager;

    public OpenUserStatsFragment(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
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
                Toast.makeText(context, "Brak dostępu do Internetu", Toast.LENGTH_SHORT).show();
                break;
            case 0:
                UserStatsFragment fragment = new UserStatsFragment();
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_START)).commit();
                break;
        }
    }

}
