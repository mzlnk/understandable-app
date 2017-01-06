package net.heliantum.ziedic.data;

/**
 * Created by Marcin on 2017-01-06.
 */

public class QuizData extends ChosenWordsData {

    private static int questions = 0;
    private static int correctAnswers = 0;
    private static int incorrectAnswers = 0;

    private static int getQuestions() {
        return questions;
    }

    private static int getCorrectAnswers() {
        return correctAnswers;
    }

    private static int getIncorrectAnswers() {
        return incorrectAnswers;
    }

    private static void addCorrectAnswer() {
        correctAnswers++;
    }

    private static void addIncorrectAnswer() {
        incorrectAnswers++;
    }

    private static void setQuestions(int amount) {
        questions = amount;
    }

}
