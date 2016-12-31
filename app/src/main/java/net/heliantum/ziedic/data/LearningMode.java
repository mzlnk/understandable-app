package net.heliantum.ziedic.data;


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

    public static LearningMode getEnumFromPolish(String name) {

        for(LearningMode o : LearningMode.values()) {
            if(o.getName().equalsIgnoreCase(name)) return o;
        }
        return LearningMode.REPETITION;
    }

}
