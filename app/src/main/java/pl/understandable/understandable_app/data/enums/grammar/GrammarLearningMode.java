package pl.understandable.understandable_app.data.enums.grammar;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;

/**
 * Created by Marcin on 2017-08-12.
 */

public enum GrammarLearningMode implements Identifiable {

    QUIZ("quiz", R.drawable.f_words_choice_unchecked),
    SENTENCE("zdania",R.drawable.f_words_choice_unchecked),
    FILL_GAPS("uzupełnianie luk", R.drawable.f_words_choice_unchecked);

    private String name;
    private int resId;

    private GrammarLearningMode(String name, int resId) {
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
