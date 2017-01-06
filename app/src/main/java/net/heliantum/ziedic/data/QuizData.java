package net.heliantum.ziedic.data;

import net.heliantum.ziedic.database.entity.LanguageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2017-01-06.
 */

public class QuizData extends ChosenWordsData {

    private static List<LanguageEntity> chosenWordsLeft = new ArrayList<>();

    private static int currentQuestion = 1;
    private static int questions = 0;
    private static int correctAnswers = 0;
    private static int incorrectAnswers = 0;

    public static List<LanguageEntity> getChosenWordsLeft() {
        return chosenWordsLeft;
    }

    public static int getCurrentQuestion() {
        return currentQuestion;
    }

    public static int getQuestions() {
        return questions;
    }

    public static int getCorrectAnswers() {
        return correctAnswers;
    }

    public static int getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public static void removeWord(LanguageEntity entity) {
        chosenWordsLeft.remove(entity);
    }

    public static void nextQuestion() {
        currentQuestion++;
        questions++;
    }

    public static void addCorrectAnswer() {
        correctAnswers++;
    }

    public static void addIncorrectAnswer() {
        incorrectAnswers++;
    }

    public static void addChosenWordsToQuizData() {
        chosenWordsLeft = new ArrayList<>(getAllChosenWords());
    }

    public static void resetStats() {
        currentQuestion = 1;
        questions = 0;
        correctAnswers = 0;
        incorrectAnswers = 0;
    }

}
