package pl.understandable.understandable_app.user.requests;

/**
 * Created by Marcin Zielonka on 2017-10-13.
 */

public interface Request {

    public void executeRequest();
    public long getCooldownInMillis();

}
