package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.R;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class FirstIrregularVerbsTestSolved extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.FIRST_IRREGULAR_VERBS_TEST_SOLVED;
    }

    @Override
    public String getName() {
        return "Od-czasownikowy";
    }

    @Override
    public String getDescription() {
        return "Pierwszy rozwiązany test z czasowników nieregularnych";
    }

    @Override
    public boolean isAchievable() {
        return false;
    }

    @Override
    public int getResId() {
        return R.drawable.f_user_achievements_ftsirregular;
    }

}
