package pl.understandable.understandable_dev_app.data.entities_data.phrases;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_dev_app.data.params.PhrasesDataParams;
import pl.understandable.understandable_dev_app.database.entity.PhraseEntity;

/**
 * Created by Marcin Zielonka on 2017-08-11.
 */

public class PhrasesQuizData extends PhrasesBaseData {

    private static PhrasesQuizData quizData;

    public static PhrasesQuizData getQuizData() {
        return quizData;
    }

    public static void createQuizDataFromParams(PhrasesDataParams params) {
        quizData = new PhrasesQuizData(params);
    }

    public List<PhraseEntity> wordsLeft = new ArrayList<>();
    public List<PhraseEntity> correctAnswers = new ArrayList<>();
    public List<PhraseEntity> incorrectAnswers = new ArrayList<>();
    public PhraseEntity currentWord;

    public int currentQuestion = 1;
    public int wordsSeen = 0;

    public PhrasesQuizData(PhrasesDataParams params) {
        super(params);
        wordsLeft = new ArrayList<>(words);
    }

    public void nextQuestion() {
        wordsSeen++;
        currentQuestion++;
        wordsLeft.remove(currentWord);
        currentWord = wordsLeft.get(r.nextInt(wordsLeft.size()));
    }

    public PhraseEntity[] getRandomIncorrectAnswers() {
        PhraseEntity[] incorrectAnswers = new PhraseEntity[3];
        PhraseEntity word;
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

    public void resetStats() {
        quizData = new PhrasesQuizData(params);
    }

    public static class Util {

        public static int getRandomCorrectOption() {
            return r.nextInt(4);
        }

    }

}
