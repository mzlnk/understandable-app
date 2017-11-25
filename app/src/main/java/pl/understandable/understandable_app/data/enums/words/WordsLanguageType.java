package pl.understandable.understandable_app.data.enums.words;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */


public enum WordsLanguageType implements Identifiable {

    VERB("czasownik", R.drawable.f_words_choice_checked),
    NOUN("rzeczownik", R.drawable.f_words_choice_checked),
    ADJECTIVE("przymiotnik", R.drawable.f_words_choice_checked),
    ADVERB("przysłówek", R.drawable.f_words_choice_checked),
    PHRASE("wyrażenie", R.drawable.f_words_choice_checked),
    IDIOM("idiom", R.drawable.f_words_choice_checked);


    private String name;
    private int resId;

    private WordsLanguageType(String name, int resId) {
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

    public static WordsLanguageType getEnum(String name) {
        WordsLanguageType result;

        try {
            result = WordsLanguageType.valueOf(name);
        } catch(IllegalArgumentException e) {
            result = WordsLanguageType.PHRASE;
        }

        return result;
    }

}
