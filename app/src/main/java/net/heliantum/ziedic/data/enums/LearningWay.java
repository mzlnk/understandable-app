package net.heliantum.ziedic.data.enums;

/**
 * Created by Lotos_ on 2016-11-12.
 */


public enum LearningWay implements Nameable {

    POLISH_TO_ENGLISH(0, "z poskiego na angielski"),
    ENGLISH_TO_POLISH(1, "z angielskiego na polski"),
    RANDOM(2, "losowo");

    private int id;
    private String name;

    private LearningWay(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

}
