package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public abstract class Achievement implements Identifiable {

    private boolean achieved = false;

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    public abstract AchievementId getId();
    public abstract String getName();
    public abstract String getDescription();
    public abstract boolean isAchievable();

    @Override
    public int getResId() {
        return R.drawable.f_user_achievements_not_done;
    }

}
