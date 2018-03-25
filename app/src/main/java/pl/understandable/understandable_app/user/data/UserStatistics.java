package pl.understandable.understandable_app.user.data;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class UserStatistics {

    private static double ONE_WEEK_IN_MINUTES = 10080D;
    private static double ONE_DAY_IN_MINUTES = 1440D;
    private static double ONE_HOUR_IN_MINUTES = 60D;

    public static final int WORDS = 0;
    public static final int IRREGULAR_VERBS = 1;
    public static final int PHRASES = 2;
    public static final int CUSTOM_WORDS = 3;

    public static final int LIST = 0;
    public static final int REPETITION = 1;
    public static final int QUIZ = 2;
    public static final int SPELLING = 3;

    private long timeLearnt = 0;
    private int wordsSetsDownloaded = 0;
    private int allTestsSolved = 0;
    private int[] wordsTestsSolved = new int[4];
    private int[] irregularVerbsTestsSolved = new int[2];
    private int[] phrasesTestsSolved = new int[3];
    private int[] customWordsTestsSolved = new int[4];

    public long getTimeLearnt() {
        return timeLearnt;
    }

    public int getWordsSetsDownloaded() {
        return wordsSetsDownloaded;
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

    public int getCustomWordsTestsSolved(int mode) {
        return customWordsTestsSolved[mode];
    }

    public void setTimeLearnt(long timeLearnt) {
        this.timeLearnt = timeLearnt;
    }

    public void setWordsSetsDownloaded(int wordsSetsDownloaded) {
        this.wordsSetsDownloaded = wordsSetsDownloaded;
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

    public void setCustomWordsTestsSolved(int mode, int value) {
        this.customWordsTestsSolved[mode] = value;
    }

    public void addTimeLearnt(long time) {
        timeLearnt += time;
    }

    public void addWordsSetDownloaded() {
        wordsSetsDownloaded++;
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
            case CUSTOM_WORDS:
                customWordsTestsSolved[mode]++;
                break;
        }
        allTestsSolved++;
    }

    public String getFormattedTimeLearnt() {
        StringBuilder sb = new StringBuilder();

        long time = this.timeLearnt;
        int weeks = (int)((double) time / ONE_WEEK_IN_MINUTES);
        int days = (int)((time - weeks * ONE_WEEK_IN_MINUTES) / ONE_DAY_IN_MINUTES);
        int hours = (int)((time - weeks * ONE_WEEK_IN_MINUTES - days * ONE_DAY_IN_MINUTES) / ONE_HOUR_IN_MINUTES);
        int minutes = (int)(time - weeks * ONE_WEEK_IN_MINUTES - days * ONE_DAY_IN_MINUTES - hours * ONE_HOUR_IN_MINUTES);

        sb.append(weeks == 0 ? "" : (weeks + "w ")).
           append(weeks == 0 && days == 0 ? "" : (days + "d ")).
           append(weeks == 0 && days == 0 && hours == 0 ? "" : (hours + "h ")).
           append(minutes + "min");

        return sb.toString();
    }

}
