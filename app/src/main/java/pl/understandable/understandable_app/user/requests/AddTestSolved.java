package pl.understandable.understandable_app.user.requests;

import pl.understandable.understandable_app.user.Request;
import pl.understandable.understandable_app.user.SyncManager;
import pl.understandable.understandable_app.user.UserManager;

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
        if(SyncManager.isSyncAvailable()) {
            UserManager.getUser().getStats().addTestSolved(which, mode);
            UserManager.setSyncRequired(true);
            UserManager.addElementToSync(UserManager.SyncElement.TESTS);
        }
    }

}
