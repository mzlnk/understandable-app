package net.heliantum.understandable.data.enums;

import net.heliantum.understandable.R;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */


public enum LanguageType implements Identifiable {

    VERB("czasownik", R.drawable.f_words_choice_base),
    NOUN("rzeczownik", R.drawable.f_words_choice_base),
    ADJECTIVE("przymiotnik", R.drawable.f_words_choice_base),
    ADVERB("przysłówek", R.drawable.f_words_choice_base),
    PREPOSITION("przyimek", R.drawable.f_words_choice_base),
    EXPRESSION("wyrażenie", R.drawable.f_words_choice_base),
    OTHER("inne", R.drawable.f_words_choice_base);

    private String name;
    private int resId;

    private LanguageType(String name, int resId) {
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

    public static LanguageType getEnum(String name) {
        LanguageType result;

        try {
            result = LanguageType.valueOf(name);
        } catch(IllegalArgumentException e) {
            result = LanguageType.OTHER;
        }

        return result;
    }

}
