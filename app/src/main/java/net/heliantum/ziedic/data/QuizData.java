package net.heliantum.ziedic.data;

import net.heliantum.ziedic.database.entity.LanguageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2017-05-06.
 */

public class QuizData extends BaseData {

    private static QuizData quizData;

    public static QuizData getQuizData() {
        return quizData;
    }

    public static void createQuizDataFromParams(DataParams params) {
        quizData = new QuizData(params);
    }

    public List<LanguageEntity> wordsLeft = new ArrayList<>();
    public List<LanguageEntity> correctAnswers = new ArrayList<>();
    public List<LanguageEntity> incorrectAnswers = new ArrayList<>();
    public LanguageEntity currentWord;
    public int currentQuestion = 1;

    public QuizData(DataParams params) {
        super(params);
        wordsLeft = new ArrayList<>(words);
    }

    public void nextQuestion() {
        currentQuestion++;
        wordsLeft.remove(currentWord);
        currentWord = wordsLeft.get(r.nextInt(wordsLeft.size()));
    }

    public LanguageEntity[] getRandomIncorrectAnswers() {
        LanguageEntity[] incorrectAnswers = new LanguageEntity[3];
        LanguageEntity word;
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

    public QuizData resetStats() {
        return new QuizData(params);
    }

}
