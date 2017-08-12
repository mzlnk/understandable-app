package pl.understandable.understandable_app.data.entities_data.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.understandable.understandable_app.data.entities_data.DataUtil;
import pl.understandable.understandable_app.data.entities_data.words_data.WordsQuizData;
import pl.understandable.understandable_app.data.params.GrammarDataParams;
import pl.understandable.understandable_app.data.params.WordsDataParams;
import pl.understandable.understandable_app.database.entity.GrammarBaseEntity;
import pl.understandable.understandable_app.database.entity.GrammarQuizEntity;
import pl.understandable.understandable_app.database.entity.WordEntity;
import pl.understandable.understandable_app.database.repository.WordEntityRepository;

/**
 * Created by Marcin on 2017-08-12.
 */

public class GrammarQuizData extends GrammarBaseData<GrammarQuizEntity> {

    private static GrammarQuizData quizData;

    public static GrammarQuizData getQuizData() {
        return quizData;
    }

    public static void createQuizDataFromParams(GrammarDataParams params) {
        DataUtil.clearAllData();
        quizData = new GrammarQuizData(params);
    }

    public static void clearData() {
       quizData = null;
    }

    public List<GrammarQuizEntity> wordsLeft = new ArrayList<>();
    public GrammarQuizEntity currentWord;
    public int currentQuestion = 1;
    public int wordsSeen = 0;
    public int correctAnswers = 0;
    public int incorrectAnswers = 0;

    public GrammarQuizData(GrammarDataParams params) {
        super(params);
        wordsLeft = new ArrayList<>(words);
    }

    public void nextQuestion() {
        wordsSeen++;
        currentQuestion++;
        wordsLeft.remove(currentWord);
        currentWord = wordsLeft.get(r.nextInt(wordsLeft.size()));
    }

    public void correctAnswer() {
        correctAnswers++;
    }

    public void incorrectAnswer() {
        incorrectAnswers++;
    }

    public void resetStats() {
        quizData = new GrammarQuizData(params);
    }

    public static class Util {

        public static int getRandomCorrectOption() {
            return r.nextInt(4);
        }

    }

}
