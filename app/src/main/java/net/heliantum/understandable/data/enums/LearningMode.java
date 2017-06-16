package net.heliantum.understandable.data.enums;


/**
 * Created by Lotos_ on 2016-11-12.
 */


public enum LearningMode implements Nameable {

    REPETITION("powtarzanie"),
    QUIZ("quiz"),
    LIST("lista");

    private String name;

    private LearningMode(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
