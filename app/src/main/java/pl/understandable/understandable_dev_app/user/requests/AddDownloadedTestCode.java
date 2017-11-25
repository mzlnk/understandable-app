package pl.understandable.understandable_dev_app.user.requests;

import pl.understandable.understandable_dev_app.user.Request;
import pl.understandable.understandable_dev_app.user.SyncHandler;
import pl.understandable.understandable_dev_app.user.data.User;

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
        if(SyncHandler.getSyncStatus()) {
            User.getUser().addDownloadedTest(code);
            User.getUser().setSyncRequired(true);
        }
    }

}
