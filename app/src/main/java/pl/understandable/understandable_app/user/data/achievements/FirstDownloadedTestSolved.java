package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.user.UserManager;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class FirstDownloadedTestSolved extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.FIRST_DOWNLOADED_TEST_SOLVED;
    }

    @Override
    public String getName() {
        return "Inne doświadczenie";
    }

    @Override
    public String getDescription() {
        return "pierwszy rozwiązany pobrany test";
    }

    @Override
    public boolean isAchievable() {
        boolean result = false;
        for(int i = 0; i < 4; i++) {
            result = UserManager.getUser().getStats().getCustomWordsTestsSolved(i) >= 1;
            if(result) {
                break;
            }
        }
        return result;
    }

    @Override
    public int getResId() {
        return R.drawable.f_user_achievements_ftsdownload;
    }

}
