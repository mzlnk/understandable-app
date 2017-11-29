package pl.understandable.understandable_app.user.requests;

import pl.understandable.understandable_app.user.Request;
import pl.understandable.understandable_app.user.SyncManager;
import pl.understandable.understandable_app.user.UserManager;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class AddDownloadedTestCode implements Request {

    private String code;

    public AddDownloadedTestCode(String code) {
        this.code = code;
    }

    @Override
    public void executeRequest() {
        if(SyncManager.getSyncStatus()) {
            UserManager.getUser().addDownloadedTest(code);
            UserManager.setSyncRequired(true);
        }
    }

}
