package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.R;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class FirstTestDownloaded extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.FIRST_TEST_DOWNLOADED;
    }

    @Override
    public String getName() {
        return "Otwarty na innych";
    }

    @Override
    public String getDescription() {
        return "Pierwszy pobrany test";
    }

    @Override
    public boolean isAchievable() {
        return false;
    }

    @Override
    public int getResId() {
        return R.drawable.f_user_achievements_ftdownload;
    }

}
