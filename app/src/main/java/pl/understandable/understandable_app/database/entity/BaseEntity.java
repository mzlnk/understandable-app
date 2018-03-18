package pl.understandable.understandable_app.database.entity;

/**
 * Created by Marcin Zielonka on 2017-05-06.
 */

public abstract class BaseEntity {

    protected int id;
    protected boolean isLearnt = false;

    public int getId() {
        return id;
    }

    public boolean isLearnt() {
        return isLearnt;
    }

    public void setLearnt(boolean isLearnt) {
        this.isLearnt = isLearnt;
    }

}
