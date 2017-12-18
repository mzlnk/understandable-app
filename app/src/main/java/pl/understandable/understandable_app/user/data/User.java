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
    private String name = "";
    private long exp = 0;
    private UserStatistics stats = new UserStatistics();
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
            long expForLevel = (long) (Math.pow((double) level, 2.2D) + 50);
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
        for(int i = 1; i <= level; i++) {
            exp += (long)(Math.pow((double) i, 2.2D) + 50);
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

    public AddExpResponse addExp(long exp) {
        int previousLevel = getLevel();
        this.exp += exp;
        int currentLevel = getLevel();

        boolean levelUp = currentLevel != previousLevel;
        return new AddExpResponse(levelUp, previousLevel, currentLevel);
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
            user.put("timeLearnt", this.stats.getTimeLearnt());
            user.put("wordsSetsDownloaded", this.stats.getWordsSetsDownloaded());
            user.put("allTestsSolved", this.stats.getAllTestsSolved());

            JSONArray testsSolved = new JSONArray();
            testsSolved.put(new JSONObject().put("id", "w_tests_solved_0").put("value", this.stats.getWordsTestsSolved(UserStatistics.LIST)));
            testsSolved.put(new JSONObject().put("id", "w_tests_solved_1").put("value", this.stats.getWordsTestsSolved(UserStatistics.REPETITION)));
            testsSolved.put(new JSONObject().put("id", "w_tests_solved_2").put("value", this.stats.getWordsTestsSolved(UserStatistics.QUIZ)));
            testsSolved.put(new JSONObject().put("id", "w_tests_solved_3").put("value", this.stats.getWordsTestsSolved(UserStatistics.SPELLING)));

            testsSolved.put(new JSONObject().put("id", "ir_tests_solved_0").put("value", this.stats.getIrregularVerbsTestsSolved(UserStatistics.LIST)));
            testsSolved.put(new JSONObject().put("id", "ir_tests_solved_1").put("value", this.stats.getIrregularVerbsTestsSolved(UserStatistics.REPETITION)));

            testsSolved.put(new JSONObject().put("id", "p_tests_solved_0").put("value", this.stats.getPhrasesTestsSolved(UserStatistics.LIST)));
            testsSolved.put(new JSONObject().put("id", "p_tests_solved_1").put("value", this.stats.getPhrasesTestsSolved(UserStatistics.REPETITION)));
            testsSolved.put(new JSONObject().put("id", "p_tests_solved_2").put("value", this.stats.getPhrasesTestsSolved(UserStatistics.QUIZ)));

            testsSolved.put(new JSONObject().put("id", "cw_tests_solved_0").put("value", this.stats.getCustomWordsTestsSolved(UserStatistics.LIST)));
            testsSolved.put(new JSONObject().put("id", "cw_tests_solved_1").put("value", this.stats.getCustomWordsTestsSolved(UserStatistics.REPETITION)));
            testsSolved.put(new JSONObject().put("id", "cw_tests_solved_2").put("value", this.stats.getCustomWordsTestsSolved(UserStatistics.QUIZ)));
            testsSolved.put(new JSONObject().put("id", "cw_tests_solved_3").put("value", this.stats.getCustomWordsTestsSolved(UserStatistics.SPELLING)));

            user.put("testsSolved", testsSolved);

            JSONArray achievements = new JSONArray();
            for(AchievementId id : AchievementId.values()) {
                JSONObject a = new JSONObject();
                a.put("id", id.getId2());
                a.put("value", this.achievements.get(id).isAchieved() ? 1 : 0);
                achievements.put(a);
            }
            user.put("achievements", achievements);

            JSONArray followedWordsSets = new JSONArray();
            for(String wordsSet : this.followedWordsSets) {
                followedWordsSets.put(wordsSet);
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
            this.stats.setTimeLearnt(user.getLong("timeLearnt"));
            this.stats.setWordsSetsDownloaded(user.getInt("wordsSetsDownloaded"));
            this.stats.setAllTestsSolved(user.getInt("allTestsSolved"));

            JSONArray testsSolved = user.getJSONArray("testsSolved");
            Map<String, Integer> testsStats = new HashMap<>();
            for(int i = 0; i < testsSolved.length(); i++) {
                JSONObject test = testsSolved.getJSONObject(i);
                testsStats.put(test.getString("id"), test.getInt("value"));
            }

            this.stats.setWordsTestsSolved(UserStatistics.LIST, testsStats.get("w_tests_solved_0"));
            this.stats.setWordsTestsSolved(UserStatistics.REPETITION, testsStats.get("w_tests_solved_1"));
            this.stats.setWordsTestsSolved(UserStatistics.QUIZ, testsStats.get("w_tests_solved_2"));
            this.stats.setWordsTestsSolved(UserStatistics.SPELLING, testsStats.get("w_tests_solved_3"));

            this.stats.setIrregularVerbsTestsSolved(UserStatistics.LIST, testsStats.get("ir_tests_solved_0"));
            this.stats.setIrregularVerbsTestsSolved(UserStatistics.REPETITION, testsStats.get("ir_tests_solved_1"));

            this.stats.setPhrasesTestsSolved(UserStatistics.LIST, testsStats.get("p_tests_solved_0"));
            this.stats.setPhrasesTestsSolved(UserStatistics.REPETITION, testsStats.get("p_tests_solved_1"));
            this.stats.setPhrasesTestsSolved(UserStatistics.QUIZ, testsStats.get("p_tests_solved_2"));

            this.stats.setCustomWordsTestsSolved(UserStatistics.LIST, testsStats.get("cw_tests_solved_0"));
            this.stats.setCustomWordsTestsSolved(UserStatistics.REPETITION, testsStats.get("cw_tests_solved_1"));
            this.stats.setCustomWordsTestsSolved(UserStatistics.QUIZ, testsStats.get("cw_tests_solved_2"));
            this.stats.setCustomWordsTestsSolved(UserStatistics.SPELLING, testsStats.get("cw_tests_solved_3"));

            JSONArray achievements = user.getJSONArray("achievements");
            Map<String, Integer> achievementsStats = new HashMap<>();
            for(int i = 0; i < achievements.length(); i++) {
                JSONObject a = achievements.getJSONObject(i);
                achievementsStats.put(a.getString("id"), a.getInt("value"));
            }
            for(AchievementId id : AchievementId.values()) {
                if(achievementsStats.get(id.getId2()) == null) {
                    continue;
                }
                boolean achieved = achievementsStats.get(id.getId2()) == 1;
                this.achievements.get(id).setAchieved(achieved);
            }

            String followedWordsSets = user.getString("followedWordsSets");
            this.followedWordsSets.clear();
            if(followedWordsSets != null) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < followedWordsSets.length(); i++) {
                    if (followedWordsSets.charAt(i) != ';') {
                        sb.append(followedWordsSets.charAt(i));
                    } else {
                        this.followedWordsSets.add(sb.toString());
                        sb.setLength(0);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static class AddExpResponse {

        private AddExpReponseType response;
        private int previousLevel;
        private int currentLevel;

        public AddExpResponse(boolean levelUp, int previousLevel, int currentLevel) {
            this.response = levelUp ? AddExpReponseType.LEVEL_UP : AddExpReponseType.ONLY_EXP_ADDED;
            this.previousLevel = previousLevel;
            this.currentLevel = currentLevel;
        }

        public AddExpReponseType getResponse() {
            return response;
        }

        public int getPreviousLevel() {
            return previousLevel;
        }

        public int getCurrentLevel() {
            return currentLevel;
        }

        public static enum AddExpReponseType {
            LEVEL_UP,
            ONLY_EXP_ADDED;
        }

    }

}
