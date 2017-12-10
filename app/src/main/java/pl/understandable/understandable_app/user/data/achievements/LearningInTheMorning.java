package pl.understandable.understandable_app.user.data.achievements;

import java.util.Calendar;

import pl.understandable.understandable_app.R;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class LearningInTheMorning extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.LEARNING_IN_THE_MORNING;
    }

    @Override
    public String getName() {
        return "Ranny ptaszek";
    }

    @Override
    public String getDescription() {
        return "Nauka rano";
    }

    @Override
    public boolean isAchievable() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= 6 && hour <= 9;
    }

    @Override
    public int getResId() {
        return R.drawable.f_user_achievements_lmorning;
    }

}
