package pl.understandable.understandable_app.user.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private String tokenId;
    private String name;
    private long exp;
    private UserStatistics stats;
    private Map<AchievementId, Achievement> achievements = new HashMap<>();
    private List<String> followedWordsSets = new ArrayList<>();

    public String getTokenId() {
        return tokenId;
    }

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

    public List<String> getAllDownloadedWordsSets() {
        return followedWordsSets;
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

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addExp(long exp) {
        this.exp += exp;
    }

    public void addDownloadedTest(String code) {
        followedWordsSets.add(code);
    }

    public void removeDownloadedTest(String code) {
        followedWordsSets.remove(code);
    }

    public JSONObject toJson() {
        JSONObject user = new JSONObject();
        try {
            user.put("name", this.name);
            user.put("exp", this.exp);

            JSONObject stats = new JSONObject();
            stats.put("timeLearnt", this.stats.getTimeLearnt());
            stats.put("wordsSetsDownloaded", this.stats.getWordsSetsDownloaded());
            stats.put("allTestsSolved", this.stats.getAllTestsSolved());
            JSONArray wordsTestsSolved = new JSONArray();
            for(int i = 0; i < 4; i++) {
                wordsTestsSolved.put(this.stats.getWordsTestsSolved(i));
            }
            JSONArray irregularVerbsTestsSolved = new JSONArray();
            for(int i = 0; i < 2; i++) {
                irregularVerbsTestsSolved.put(i);
            }
            JSONArray phrasesTestsSolved = new JSONArray();
            for(int i = 0; i < 3; i++) {
                phrasesTestsSolved.put(i);
            }
            stats.put("wordsTestsSolved", wordsTestsSolved);
            stats.put("irregularVerbsTestsSolved", irregularVerbsTestsSolved);
            stats.put("phrasesTestsSolved", phrasesTestsSolved);
            user.put("stats", stats);

            JSONArray achievements = new JSONArray();
            for(AchievementId id : AchievementId.values()) {
                JSONObject a = new JSONObject();
                a.put("id", id.getId2());
                a.put("value", this.achievements.get(a).isAchieved() ? 1 : 0);
                achievements.put(a);
            }
            user.put("achievements", achievements);

            JSONArray followedWordsSets = new JSONArray();
            for(String id : this.followedWordsSets) {
                followedWordsSets.put(id);
            }
            user.put("followedWordsSets", followedWordsSets);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateFromJson(JSONObject user) {
        try {
            this.name = user.getString("name");
            this.exp = user.getLong("exp");

            JSONObject stats = user.getJSONObject("stats");
            this.stats.setTimeLearnt(stats.getLong("timeLearnt"));
            this.stats.setWordsSetsDownloaded(stats.getInt("wordsSetsDownloaded"));
            this.stats.setAllTestsSolved(stats.getInt("allTestsSolved"));
            JSONArray wordsTestsSolved = stats.getJSONArray("wordsTestsSolved");
            for(int i = 0; i < 4; i++) {
                this.stats.setWordsTestsSolved(i, wordsTestsSolved.getInt(i));
            }
            JSONArray irregularVerbsTestsSolved = stats.getJSONArray("irregularVerbsTestsSolved");
            for(int i = 0; i < 2; i++) {
                this.stats.setIrregularVerbsTestsSolved(i, irregularVerbsTestsSolved.getInt(i));
            }
            JSONArray phrasesTestsSolved = stats.getJSONArray("phrasesTestsSolved");
            for(int i = 0; i < 3; i++) {
                this.stats.setPhrasesTestsSolved(i, phrasesTestsSolved.getInt(i));
            }

            JSONObject achievements = user.getJSONObject("achievements");
            for(AchievementId id : AchievementId.values()) {
                this.achievements.get(id).setAchieved(achievements.getBoolean(id.name()));
            }

            JSONArray followedWordsSets = user.getJSONArray("followedWordsSets");
            this.followedWordsSets.clear();
            for(int i = 0; i < followedWordsSets.length(); i++) {
                this.followedWordsSets.add(followedWordsSets.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
