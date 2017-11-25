package pl.understandable.understandable_dev_app.user.data.achievements;

import java.util.Calendar;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class LearningAtNight extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.LEARNING_AT_NIGHT;
    }

    @Override
    public String getName() {
        return "Nocny marek";
    }

    @Override
    public String getDescription() {
        return "Nauka w nocy";
    }

    @Override
    public boolean isAchievable() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= 23 || hour <= 5;
    }

}
