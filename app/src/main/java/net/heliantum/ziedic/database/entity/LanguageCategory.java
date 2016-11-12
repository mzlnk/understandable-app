package net.heliantum.ziedic.database.entity;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */


public enum LanguageCategory {

    HUMAN("człowiek"),
    HOUSE("dom"),
    SCHOOL("szkoła"),
    WORK("praca"),
    FAMILY("życie rodzinne"),
    SOCIAL_LIFE("życie towarzyskie"),
    FOOD("jedzenie"),
    SHOPPING("zakupy"),
    TRAVELLING("podróżowanie"),
    CULTURE("kultura"),
    HEALTH("zdrowie"),
    SPORT("sport"),
    SCIENCE("nauka"),
    ENVIRONMENT("środowisko"),
    SOCIETY("społeczeństwo"),
    NATION("kraj"),
    OTHER("inne");

    private String name;

    private LanguageCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static LanguageCategory getEnum(String name) {
        LanguageCategory result;

        try {
            result = LanguageCategory.valueOf(name);
        }catch (IllegalArgumentException e) {
            result = LanguageCategory.OTHER;
        }

        return result;
    }

    public static LanguageCategory getEnumFromPolish(String name) {

        for(LanguageCategory c : LanguageCategory.values()) {
            if(c.getName().equals(name)) return c;
        }

        return LanguageCategory.OTHER;
    }

}
