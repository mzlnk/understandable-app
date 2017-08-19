package pl.understandable.understandable_app.database.entity;

/**
 * Created by Marcin Zielonka on 2017-07-25.
 */

public class CustomWordEntity extends BaseWordEntity {

    public CustomWordEntity(int id, String polish, String english) {
        this.id = id;
        this.polish = polish;
        this.english = english;
    }

}
