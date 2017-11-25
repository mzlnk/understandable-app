package pl.understandable.understandable_dev_app.user.requests;

import pl.understandable.understandable_dev_app.user.Request;
import pl.understandable.understandable_dev_app.user.SyncHandler;
import pl.understandable.understandable_dev_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class AddTimeLearnt implements Request {

    private long time;

    public AddTimeLearnt(long time) {
        this.time = time;
    }

    @Override
    public void executeRequest() {
        if(SyncHandler.getSyncStatus()) {
            User.getUser().getStats().addTimeLearnt(time);
            User.getUser().setSyncRequired(true);
        }
    }

}
