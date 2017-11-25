package pl.understandable.understandable_dev_app.user.data;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class UserStatistics {

    public static final int LIST = 0;
    public static final int REPETITION = 1;
    public static final int QUIZ = 2;
    public static final int SPELLING = 3;

    private long timePlayed;
    private int testsDownloaded;
    private int allTestsSolved;
    private int[] wordsTestsSolved = new int[4];
    private int[] irregularVerbsTestsSolved = new int[2];
    private int[] phrasesTestsSolved = new int[3];

    public long getTimeLearnt() {
        return timePlayed;
    }

    public int getTestsDownloaded() {
        return testsDownloaded;
    }

    public int getAllTestsSolved() {
        return allTestsSolved;
    }

    public int getWordsTestsSolved(int mode) {
        return wordsTestsSolved[mode];
    }

    public int getIrregularVerbsTestsSolved(int mode) {
        return irregularVerbsTestsSolved[mode];
    }

    public int getPhrasesTestsSolved(int mode) {
        return phrasesTestsSolved[mode];
    }

}
