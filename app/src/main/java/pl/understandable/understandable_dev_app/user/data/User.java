package pl.understandable.understandable_dev_app.user.data;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_dev_app.user.data.achievements.*;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class User {

    private static User user;

    static {
        user.achievements.add(new AppThemeChanged());
        user.achievements.add(new FiftyTestsSolved());
        user.achievements.add(new FirstDownloadedTestSolved());
        user.achievements.add(new FirstIrregularVerbsTestSolved());
        user.achievements.add(new FirstPhrasesTestSolved());
        user.achievements.add(new FirstTestDownloaded());
        user.achievements.add(new FirstWordsTestSolved());
        user.achievements.add(new FiveHundredTestsSolved());
        user.achievements.add(new FiveThousandTestsSolved());
        user.achievements.add(new HundredTestsSolved());
        user.achievements.add(new LearningAtNight());
        user.achievements.add(new LearningInTheMorning());
        user.achievements.add(new OneDayLearning());
        user.achievements.add(new OneHourLearning());
        user.achievements.add(new OneWeekLearning());
        user.achievements.add(new QuizCorrectlySolved());
        user.achievements.add(new SixHoursLearning());
        user.achievements.add(new TenThousandTestsSolved());
        user.achievements.add(new ThousandTestsSolved());
        user.achievements.add(new TwelveHoursLearning());
        user.achievements.add(new TwoHundredAndAHalfTestsSolved());
        user.achievements.add(new TwoThousandFiveHundredTestsSolved());
    }

    public static User getUser() {
        return user;
    }

    //******************************************************************************************************

    private boolean syncRequired = false;

    private String name;
    private long exp;
    private UserStatistics stats;
    private List<Achievement> achievements = new ArrayList<>();
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

    public List<Achievement> getAllAchievements() {
        return achievements;
    }

    public List<String> getAllDownloadedTests() {
        return downloadedTests;
    }

    public boolean isSyncRequired() {
        return syncRequired;
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
        for(Achievement a : achievements) {
            if(a.getId().equals(id)) {
                return a;
            }
        }
        return null;
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

    public void setSyncRequired(boolean status) {
        syncRequired = status;
    }

}
