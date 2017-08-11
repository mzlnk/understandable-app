package pl.understandable.understandable_app.database.entity;

/**
 * Created by Marcin on 2017-07-25.
 */

public class CustomWordEntity extends BaseEntity {

    private String polish;
    private String english;

    public CustomWordEntity(int id, String polish, String english) {
        this.id = id;
        this.polish = polish;
        this.english = english;
    }

    public String getPolish() {
        return polish;
    }

    public String getEnglish() {
        return english;
    }

}
