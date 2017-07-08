package net.heliantum.understandable.data.enums.words;

import net.heliantum.understandable.R;
import net.heliantum.understandable.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */


public enum WordsLanguageType implements Identifiable {

    VERB("czasownik", R.drawable.f_words_choice_type_verb),
    NOUN("rzeczownik", R.drawable.f_words_choice_type_noun),
    ADJECTIVE("przymiotnik", R.drawable.f_words_choice_type_adjective),
    ADVERB("przysłówek", R.drawable.f_words_choice_type_adverb),
    PREPOSITION("przyimek", R.drawable.f_words_choice_type_preposition),
    IDIOM("idiom", R.drawable.f_words_choice_type_idiom),
    PHRASAL_VERB("frazal", R.drawable.f_words_choice_type_phrasal_verb),
    OTHER("inne", R.drawable.f_words_choice_other);

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
            result = WordsLanguageType.OTHER;
        }

        return result;
    }

}
