package pl.understandable.understandable_app.data.enums.words;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;

/**
 * Created by Marcin on 2017-08-25.
 */

public enum WordsLearningMethod implements Identifiable {

    TYPES("rodzaje"),
    SUBCATEGORIES("podkategorie"),
    ALL("wszystko");

    private String name;

    private WordsLearningMethod(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getResId() {
        return R.drawable.f_words_choice_unchecked;
    }

}
