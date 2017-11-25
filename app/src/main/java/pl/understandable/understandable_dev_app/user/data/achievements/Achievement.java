package pl.understandable.understandable_dev_app.user.data.achievements;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public abstract class Achievement {

    private boolean achieved = false;

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    public abstract boolean isAchievable();

}
