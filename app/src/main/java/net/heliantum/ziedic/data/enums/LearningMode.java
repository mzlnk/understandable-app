package net.heliantum.ziedic.data.enums;


/**
 * Created by Lotos_ on 2016-11-12.
 */


public enum LearningMode {

    REPETITION("powtarzanie"),
    QUIZ("quiz");

    private String name;

    private LearningMode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
