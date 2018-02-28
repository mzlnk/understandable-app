package pl.understandable.understandable_app.data.enums.words;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */


public enum WordsType implements Identifiable {

    VERB("czasownik", R.drawable.f_words_choice_checked),
    NOUN("rzeczownik", R.drawable.f_words_choice_checked),
    ADJECTIVE("przymiotnik", R.drawable.f_words_choice_checked),
    ADVERB("przysłówek", R.drawable.f_words_choice_checked),
    PHRASE("wyrażenie", R.drawable.f_words_choice_checked),
    IDIOM("idiom", R.drawable.f_words_choice_checked);


    private String name;
    private int resId;

    private WordsType(String name, int resId) {
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

    public static WordsType getEnum(String name) {
        WordsType result;

        try {
            result = WordsType.valueOf(name);
        } catch(IllegalArgumentException e) {
            result = WordsType.PHRASE;
        }

        return result;
    }

}
