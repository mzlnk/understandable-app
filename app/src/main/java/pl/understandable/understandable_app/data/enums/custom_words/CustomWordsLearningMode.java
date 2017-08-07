package pl.understandable.understandable_app.data.enums.custom_words;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;

/**
 * Created by Marcin on 2017-07-29.
 */

public enum CustomWordsLearningMode implements Identifiable {

    REPETITION("powtarzanie",R.drawable.f_words_choice_mode_repetition),
    QUIZ("quiz", R.drawable.f_words_choice_mode_quiz),
    SPELLING("pisownia", R.drawable.f_words_choice_mode_spelling),
    LIST("lista", R.drawable.f_words_choice_mode_list);

    private String name;
    private int resId;

    private CustomWordsLearningMode(String name, int resId) {
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
