package pl.understandable.understandable_app.utils.announcement;

import android.content.Context;
import android.os.Handler;

import pl.understandable.understandable_app.webservice.ShowAnnouncementTask;

/**
 * Created by Marcin Zielonka on 2018-02-05.
 */

public class AnnouncementManager {

    public static void showAnnouncement(final Context context) {
        new ShowAnnouncementTask(context).execute();

    }
}
