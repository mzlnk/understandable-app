package net.heliantum.understandable.data.enums;


import net.heliantum.understandable.R;

/**
 * Created by Lotos_ on 2016-11-12.
 */


public enum LearningMode implements Identifiable {

    REPETITION("powtarzanie", R.drawable.f_words_choice_mode_repetition),
    QUIZ("quiz", R.drawable.f_words_choice_mode_quiz),
    LIST("lista", R.drawable.f_words_choice_mode_list);

    private String name;
    private int resId;

    private LearningMode(String name, int resId) {
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
