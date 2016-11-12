package net.heliantum.ziedic.utils;


/**
 * Created by Lotos_ on 2016-11-12.
 */


public enum LearningOption {

    REPETITION("powtarzanie"),
    QUIZ("quiz");

    private String name;

    private LearningOption(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static LearningOption getEnumFromPolish(String name) {

        for(LearningOption o : LearningOption.values()) {
            if(o.getName().equalsIgnoreCase(name)) return o;
        }
        return LearningOption.REPETITION;
    }

}
