package pl.understandable.understandable_app.user.data;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class UserStatistics {

    public static final int WORDS = 0;
    public static final int IRREGULAR_VERBS = 1;
    public static final int PHRASES = 2;

    public static final int LIST = 0;
    public static final int REPETITION = 1;
    public static final int QUIZ = 2;
    public static final int SPELLING = 3;

    private long timeLearnt;
    private int testsDownloaded;
    private int allTestsSolved;
    private int[] wordsTestsSolved = new int[4];
    private int[] irregularVerbsTestsSolved = new int[2];
    private int[] phrasesTestsSolved = new int[3];

    public long getTimeLearnt() {
        return timeLearnt;
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

    public void setTimeLearnt(long timeLearnt) {
        this.timeLearnt = timeLearnt;
    }

    public void setTestsDownloaded(int testsDownloaded) {
        this.testsDownloaded = testsDownloaded;
    }

    public void setAllTestsSolved(int allTestsSolved) {
        this.allTestsSolved = allTestsSolved;
    }

    public void setWordsTestsSolved(int mode, int value) {
        this.wordsTestsSolved[mode] = value;
    }

    public void setIrregularVerbsTestsSolved(int mode, int value) {
        this.irregularVerbsTestsSolved[mode] = value;
    }

    public void setPhrasesTestsSolved(int mode, int value) {
        this.phrasesTestsSolved[mode] = value;
    }

    public void addTimeLearnt(long time) {
        timeLearnt += time;
    }

    public void addTestDownloaded() {
        testsDownloaded++;
    }

    public void addTestSolved(int which, int mode) {
        switch(which) {
            case WORDS:
                wordsTestsSolved[mode]++;
                break;
            case IRREGULAR_VERBS:
                irregularVerbsTestsSolved[mode]++;
                break;
            case PHRASES:
                phrasesTestsSolved[mode]++;
                break;
        }
        allTestsSolved++;
    }

}
