package pl.understandable.understandable_app.user.data.achievements;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class QuizCorrectlySolved extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.QUIZ_CORRECTLY_SOLVED;
    }

    @Override
    public String getName() {
        return "Mistrz Poprawności";
    }

    @Override
    public String getDescription() {
        return "100% wynik uzyskany w quizie";
    }

    @Override
    public boolean isAchievable() {
        return false;
    }

}
