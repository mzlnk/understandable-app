package net.heliantum.understandable.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import net.heliantum.understandable.activities.NavigationActivity;

import java.util.List;

/**
 * Created by Marcin on 2017-07-07.
 */

public class FragmentUtil {

    public static Fragment getVisibleFragment() {
        FragmentManager fragmentManager = NavigationActivity.activity.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null) {
            for(Fragment fragment : fragments) {
                if(fragment != null && fragment.isVisible()) {
                    return fragment;
                }
            }
        }
        return null;
    }

}
