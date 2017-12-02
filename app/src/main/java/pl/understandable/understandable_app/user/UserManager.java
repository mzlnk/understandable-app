package pl.understandable.understandable_app.user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-29.
 */

public class UserManager {

    private static User user;

    private static UserStatus userStatus = UserStatus.NO_ACCOUNT;
    private static boolean isSyncRequired = false;
    private static List<SyncElement> elementsToSync = new ArrayList<>();

    public static void init() {
        user = new User();
    }

    public static User getUser() {
        return user;
    }

    public static boolean isSyncRequired() {
        return isSyncRequired;
    }

    public static boolean isUserLoggedIn() {
        return userStatus.equals(UserStatus.LOGGED_IN);
    }

    public static List<SyncElement> getElementsToSync() {
        return elementsToSync;
    }

    public static void setSyncRequired(boolean status) {
        isSyncRequired = status;
    }

    public static void setUserStatus(UserStatus status) {
        userStatus = status;
    }

    public static void addElementToSync(SyncElement element) {
        if(!elementsToSync.contains(element)) {
            elementsToSync.add(element);
        }
    }

    public static void clearElementsToSync() {
        elementsToSync.clear();
    }

    public static JSONObject getJsonData() {
        JSONObject result = user.toJson();
        JSONArray elementsToSync = new JSONArray();
        for(SyncElement element : UserManager.elementsToSync) {
            elementsToSync.put(element.name());
        }
        try {
            result.put("elementsToSync", elementsToSync);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static enum UserStatus {
        LOGGED_IN,
        NO_ACCOUNT;
    }

    public static enum SyncElement {
        GENERAL,
        TESTS,
        ACHIEVEMENTS;
    }

}
