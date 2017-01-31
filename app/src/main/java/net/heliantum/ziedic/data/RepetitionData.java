package net.heliantum.ziedic.data;

import net.heliantum.ziedic.database.entity.LanguageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-01-29.
 */

public class RepetitionData extends BaseWordsData {

    public static List<LanguageEntity> wordsToRepeat = new ArrayList<>();
    public static List<LanguageEntity> wordsSeen = new ArrayList<>();
    public static LanguageEntity currentWord;

    public static boolean existsInToRepeatWords(LanguageEntity word) {
        return wordsToRepeat.contains(word);
    }

    public static void addCurrentWordToRepeat() {
        if(!wordsToRepeat.contains(currentWord)) {
            wordsToRepeat.add(currentWord);
        }
    }

    public static void addCurrentWordToSeen() {
        if(!wordsSeen.contains(currentWord)) {
            wordsSeen.add(currentWord);
        }
    }

    public static void removeCurrentWordFromRepeat() {
        wordsToRepeat.remove(currentWord);
    }

    public static void setCurrentWord(LanguageEntity word) {
        currentWord = word;
    }

    public static void resetStats() {
        currentWord = allChosenWords.get(0);
        wordsSeen.clear();
        addCurrentWordToSeen();
        wordsToRepeat.clear();
    }

}
