package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.user.UserManager;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class FirstPhrasesTestSolved extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.FIRST_PHRASES_TEST_SOLVED;
    }

    @Override
    public String getName() {
        return "Że się tak wyrażę";
    }

    @Override
    public String getDescription() {
        return "Pierwszy rozwiązany test z wyrażeń";
    }

    @Override
    public boolean isAchievable() {
        boolean result = false;
        for(int i = 0; i < 3; i++) {
            result = UserManager.getUser().getStats().getPhrasesTestsSolved(i) >= 1;
            if(result) {
                break;
            }
        }
        return result;
    }

    @Override
    public int getResId() {
        return R.drawable.f_user_achievements_ftsphrases;
    }

}
