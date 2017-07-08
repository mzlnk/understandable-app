package net.heliantum.understandable.data.enums.words;


import net.heliantum.understandable.R;
import net.heliantum.understandable.data.enums.Identifiable;

/**
 * Created by Lotos_ on 2016-11-12.
 */


public enum WordsLearningMode implements Identifiable {

    REPETITION("powtarzanie", R.drawable.f_words_choice_mode_repetition),
    QUIZ("quiz", R.drawable.f_words_choice_mode_quiz),
    LIST("lista", R.drawable.f_words_choice_mode_list);

    private String name;
    private int resId;

    private WordsLearningMode(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getResId() {
        return resId;
    }

}
