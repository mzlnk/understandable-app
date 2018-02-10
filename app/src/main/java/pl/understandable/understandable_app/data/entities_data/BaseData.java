package pl.understandable.understandable_app.data.entities_data;

/**
 * Created by Marcin Zielonka on 2018-02-10.
 */

public abstract class BaseData {

    private boolean statsUpdated = false;

    public boolean areStatsUpdated() {
        return statsUpdated;
    }

    public void setStatsUpdated(boolean statsUpdated) {
        this.statsUpdated = statsUpdated;
    }

}
