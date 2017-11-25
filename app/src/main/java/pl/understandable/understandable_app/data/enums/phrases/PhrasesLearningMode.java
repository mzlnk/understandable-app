package pl.understandable.understandable_app.data.enums.phrases;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2017-08-11.
 */

public enum PhrasesLearningMode implements Identifiable {

    REPETITION("powtarzanie", R.drawable.f_words_choice_mode_repetition),
    QUIZ("quiz", R.drawable.f_words_choice_mode_quiz),
    LIST("lista",R.drawable.f_words_choice_mode_list);

    private String name;
    private int resId;

    private PhrasesLearningMode(String name, int resId) {
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
