package net.heliantum.ziedic.utils;

/**
 * Created by Lotos_ on 2016-11-12.
 */


public enum LanguageLearningWay {

    POLISH_TO_ENGLISH(0, "z poskiego na angielski"),
    ENGLISH_TO_POLISH(1, "z angielskiego na polski"),
    RANDOM(2, "losowo");

    private int id;
    private String name;

    private LanguageLearningWay(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static LanguageLearningWay getEnumFromPolish(String name) {
        for(LanguageLearningWay lld : LanguageLearningWay.values()) {
            if(lld.getName().equalsIgnoreCase(name)) return lld;
        }
        return LanguageLearningWay.RANDOM;
    }

    public static LanguageLearningWay getEnumFromPolish(int id) {
        for(LanguageLearningWay lld : LanguageLearningWay.values()) {
            if(lld.getId() == id) return lld;
        }
        return LanguageLearningWay.RANDOM;
    }


}
