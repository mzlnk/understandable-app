package pl.understandable.understandable_app.user;

import pl.understandable.understandable_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-29.
 */

public class UserManager {

    private static User user;

    private static boolean isSyncRequired = false;

    public static void init() {
        user = new User();
    }

    public static User getUser() {
        return user;
    }

    public static boolean isSyncRequired() {
        return isSyncRequired;
    }

    public static void setSyncRequired(boolean status) {
        isSyncRequired = status;
    }

}
