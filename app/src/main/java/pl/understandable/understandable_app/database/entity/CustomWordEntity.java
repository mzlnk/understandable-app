package pl.understandable.understandable_app.database.entity;

/**
 * Created by Marcin Zielonka on 2017-07-25.
 */

public class CustomWordEntity extends BaseWordEntity {

    private boolean isLearnt = false;

    public CustomWordEntity(int id, String polish, String english, boolean isLearnt) {
        this.id = id;
        this.polish = polish;
        this.english = english;
        this.isLearnt = isLearnt;
    }

    public boolean isLearnt() {
        return isLearnt;
    }

    public void setLearnt(boolean isLearnt) {
        this.isLearnt = isLearnt;
    }

}
