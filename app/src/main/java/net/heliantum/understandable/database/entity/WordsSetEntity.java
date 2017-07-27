package net.heliantum.understandable.database.entity;

/**
 * Created by Marcin on 2017-07-27.
 */

public class WordsSetEntity {

    private String id;
    private String name;
    private String description;

    public WordsSetEntity(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
