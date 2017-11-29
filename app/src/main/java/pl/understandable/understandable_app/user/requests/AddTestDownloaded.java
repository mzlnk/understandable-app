package pl.understandable.understandable_app.user.requests;

import pl.understandable.understandable_app.user.Request;
import pl.understandable.understandable_app.user.SyncManager;
import pl.understandable.understandable_app.user.UserManager;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class AddTestDownloaded implements Request {

    @Override
    public void executeRequest() {
        if(SyncManager.getSyncStatus()) {
            UserManager.getUser().getStats().addTestDownloaded();
            UserManager.setSyncRequired(true);
        }
    }

}