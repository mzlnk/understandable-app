package pl.understandable.understandable_app.data.entities_data.words_data;

import pl.understandable.understandable_app.data.params.WordsDataParams;
import pl.understandable.understandable_app.database.entity.WordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2017-05-06.
 */

public class WordsQuizData extends WordsBaseData {

    private static WordsQuizData quizData;

    public static WordsQuizData getQuizData() {
        return quizData;
    }

    public static void createQuizDataFromParams(WordsDataParams params) {
        quizData = new WordsQuizData(params);
    }

    public List<WordEntity> wordsLeft = new ArrayList<>();
    public List<WordEntity> correctAnswers = new ArrayList<>();
    public List<WordEntity> incorrectAnswers = new ArrayList<>();
    public WordEntity currentWord;
    public int currentQuestion = 1;

    public WordsQuizData(WordsDataParams params) {
        super(params);
        wordsLeft = new ArrayList<>(words);
    }

    public void nextQuestion() {
        currentQuestion++;
        wordsLeft.remove(currentWord);
        currentWord = wordsLeft.get(r.nextInt(wordsLeft.size()));
    }

    public WordEntity[] getRandomIncorrectAnswers() {
        WordEntity[] incorrectAnswers = new WordEntity[3];
        WordEntity word;
        boolean check1, check2, check3, check4;
        for(int i = 0; i < 3; i++) {
            do {
                word = words.get(r.nextInt(words.size()));
                check1 = word.equals(currentWord);
                check2 = (incorrectAnswers[0] != null) && word.equals(incorrectAnswers[0]);
                check3 = (incorrectAnswers[1] != null) && word.equals(incorrectAnswers[1]);
                check4 = (incorrectAnswers[2] != null) && word.equals(incorrectAnswers[2]);
            } while(check1 || check2 || check3 || check4);
            incorrectAnswers[i] = word;
        }
        return incorrectAnswers;
    }

    public void correctAnswer() {
        correctAnswers.add(currentWord);
    }

    public void incorrectAnswer() {
        incorrectAnswers.add(currentWord);
    }

    public WordsQuizData resetStats() {
        return new WordsQuizData(params);
    }

    public static class Util {

        public static int getRandomCorrectOption() {
            return r.nextInt(4);
        }

    }

}
