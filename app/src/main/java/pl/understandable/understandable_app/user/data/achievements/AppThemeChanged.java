package pl.understandable.understandable_app.user.data.achievements;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class AppThemeChanged extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.APP_THEME_CHANGED;
    }

    @Override
    public String getName() {
        return "Inna perspektywa";
    }

    @Override
    public String getDescription() {
        return "Zmieniono motyw po raz pierwszy";
    }

    @Override
    public boolean isAchievable() {
        return false;
    }

}
