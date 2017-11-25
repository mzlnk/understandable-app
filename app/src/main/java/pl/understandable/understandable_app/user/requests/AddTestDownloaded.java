package pl.understandable.understandable_app.user.requests;

import pl.understandable.understandable_app.user.Request;
import pl.understandable.understandable_app.user.SyncHandler;
import pl.understandable.understandable_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class AddTestDownloaded implements Request {

    @Override
    public void executeRequest() {
        if(SyncHandler.getSyncStatus()) {
            User.getUser().getStats().addTestDownloaded();
            User.getUser().setSyncRequired(true);
        }
    }

}
