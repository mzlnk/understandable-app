package pl.understandable.understandable_dev_app.user.requests;

import pl.understandable.understandable_dev_app.user.Request;
import pl.understandable.understandable_dev_app.user.SyncHandler;
import pl.understandable.understandable_dev_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class AddTestSolved implements Request {

    private int which;
    private int mode;

    public AddTestSolved(int which, int mode) {
        this.which = which;
        this.mode = mode;
    }

    @Override
    public void executeRequest() {
        if(SyncHandler.getSyncStatus()) {
            User.getUser().getStats().addTestSolved(which, mode);
            User.getUser().setSyncRequired(true);
        }
    }

}
