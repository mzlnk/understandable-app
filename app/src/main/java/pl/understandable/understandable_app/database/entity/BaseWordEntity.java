package pl.understandable.understandable_app.database.entity;

/**
 * Created by Marcin on 2017-08-19.
 */

public abstract class BaseWordEntity extends BaseEntity {

    protected String polish;
    protected String english;
    protected boolean isLearnt = false;

    public String getPolish() {
        return polish;
    }

    public String getEnglish() {
        return english;
    }

    public boolean isLearnt() {
        return isLearnt;
    }

    public void setLearnt(boolean isLearnt) {
        this.isLearnt = isLearnt;
    }

}
