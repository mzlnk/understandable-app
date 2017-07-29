package net.heliantum.understandable.data.entities_data.custom_words_data;

import net.heliantum.understandable.data.params.CustomWordsDataParams;
import net.heliantum.understandable.database.entity.CustomWordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2017-07-29.
 */

public class CustomWordsQuizData extends CustomWordsBaseData {

    private static CustomWordsQuizData quizData;

    public static CustomWordsQuizData getQuizData() {
        return quizData;
    }

    public static void createQuizDataFromParams(CustomWordsDataParams params) {
        quizData = new CustomWordsQuizData(params);
    }

    public List<CustomWordEntity> wordsLeft = new ArrayList<>();
    public List<CustomWordEntity> correctAnswers = new ArrayList<>();
    public List<CustomWordEntity> incorrectAnswers = new ArrayList<>();
    public CustomWordEntity currentWord;
    public int currentQuestion = 1;

    public CustomWordsQuizData(CustomWordsDataParams params) {
        super(params);
        wordsLeft = new ArrayList<>(words);
    }

    public void nextQuestion() {
        currentQuestion++;
        wordsLeft.remove(currentWord);
        currentWord = wordsLeft.get(r.nextInt(wordsLeft.size()));
    }

    public CustomWordEntity[] getRandomIncorrectAnswers() {
        CustomWordEntity[] incorrectAnswers = new CustomWordEntity[3];
        CustomWordEntity word;
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

    public CustomWordsQuizData resetStats() {
        return new CustomWordsQuizData(params);
    }

    public static class Util {

        public static int getRandomCorrectOption() {
            return r.nextInt(4);
        }

    }

}
