package net.heliantum.ziedic.data.enums;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */


public enum LanguageType {

    VERB("czasownik"),
    NOUN("rzeczownik"),
    ADJECTIVE("przymiotnik"),
    ADVERB("przysłówek"),
    PREPOSITION("przyimek"),
    EXPRESSION("wyrażenie"),
    OTHER("inne");

    private String name;

    private LanguageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
