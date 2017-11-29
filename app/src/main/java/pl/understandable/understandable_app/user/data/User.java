package pl.understandable.understandable_app.user.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.understandable.understandable_app.user.data.achievements.*;

import static pl.understandable.understandable_app.user.data.achievements.AchievementId.*;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class User {

    public User() {
        this.achievements.put(APP_THEME_CHANGED, new AppThemeChanged());
        this.achievements.put(FIFTY_TESTS_SOLVED, new FiftyTestsSolved());
        this.achievements.put(FIRST_DOWNLOADED_TEST_SOLVED, new FirstDownloadedTestSolved());
        this.achievements.put(FIRST_IRREGULAR_VERBS_TEST_SOLVED, new FirstIrregularVerbsTestSolved());
        this.achievements.put(FIRST_PHRASES_TEST_SOLVED, new FirstPhrasesTestSolved());
        this.achievements.put(FIRST_TEST_DOWNLOADED, new FirstTestDownloaded());
        this.achievements.put(FIRST_WORDS_TEST_SOLVED, new FirstWordsTestSolved());
        this.achievements.put(FIVE_HUNDRED_TESTS_SOLVED, new FiveHundredTestsSolved());
        this.achievements.put(FIVE_THOUSAND_TESTS_SOLVED, new FiveThousandTestsSolved());
        this.achievements.put(HUNDRED_TESTS_SOLVED, new HundredTestsSolved());
        this.achievements.put(LEARNING_AT_NIGHT, new LearningAtNight());
        this.achievements.put(LEARNING_IN_THE_MORNING, new LearningInTheMorning());
        this.achievements.put(ONE_DAY_LEARNING, new OneDayLearning());
        this.achievements.put(ONE_HOUR_LEARNING, new OneHourLearning());
        this.achievements.put(ONE_WEEK_LEARNING, new OneWeekLearning());
        this.achievements.put(QUIZ_CORRECTLY_SOLVED, new QuizCorrectlySolved());
        this.achievements.put(SIX_HOURS_LEARNING, new SixHoursLearning());
        this.achievements.put(TEN_THOUSAND_TESTS_SOLVED, new TenThousandTestsSolved());
        this.achievements.put(THOUSAND_TESTS_SOLVED, new ThousandTestsSolved());
        this.achievements.put(TWELVE_HOURS_LEARNING, new TwelveHoursLearning());
        this.achievements.put(TWO_HUNDRED_AND_A_HALF_TESTS_SOLVED, new TwoHundredAndAHalfTestsSolved());
        this.achievements.put(TWO_THOUSAND_FIVE_HUNDRED_TESTS_SOLVED, new TwoThousandFiveHundredTestsSolved());
    }

    private String name;
    private long exp;
    private UserStatistics stats;
    private Map<AchievementId, Achievement> achievements = new HashMap<>();
    private List<String> downloadedTests = new ArrayList<>();

    public String getName() {
        return name;
    }

    public long getExp() {
        return exp;
    }

    public UserStatistics getStats() {
        return stats;
    }

    public Map<AchievementId, Achievement> getAllAchievements() {
        return achievements;
    }

    public List<String> getAllDownloadedTests() {
        return downloadedTests;
    }

    public int getLevel() {
        long currentExp = this.exp;
        int level = 1;
        while(true) {
            long expForLevel = (long) (Math.pow((double) level, 2.2D) + 20);
            if(currentExp >= expForLevel) {
                level++;
                currentExp -= expForLevel;
            } else {
                break;
            }
        }
        return level;
    }

    public long getTotalExpForLevel(int level) {
        long exp = 0;
        for(int i = 1; i < level; i++) {
            exp += (long)(Math.pow((double) level, 2.2D) + 20);
        }
        return exp;
    }

    public long getExpForLevel(int level) {
        return (long)(Math.pow((double) level, 2.2D) + 50);
    }

    public Achievement getAchievement(AchievementId id) {
        return achievements.get(id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addExp(long exp) {
        this.exp += exp;
    }

    public void addDownloadedTest(String code) {
        downloadedTests.add(code);
    }

    public void removeDownloadedTest(String code) {
        downloadedTests.remove(code);
    }

}
