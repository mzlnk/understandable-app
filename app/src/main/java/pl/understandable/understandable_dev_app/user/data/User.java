package pl.understandable.understandable_dev_app.user.data;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class User {

    private static User user;

    public static User getUser() {
        return user;
    }

    private String name;
    private long exp;
    private UserStatistics stats;

    public String getName() {
        return name;
    }

    public long getExp() {
        return exp;
    }

    public UserStatistics getStats() {
        return stats;
    }

    public int getLevel() {
        long currentExp = this.exp;
        int level = 1;
        while(true) {
            long expForLevel = (long) (Math.pow((double) level, 2.2D) + 20);
            if(currentExp >= expForLevel) {
                level++;
                currentExp -= expForLevel;
            } else {
                break;
            }
        }
        return level;
    }

    public long getTotalExpForLevel(int level) {
        long exp = 0;
        for(int i = 1; i < level; i++) {
            exp += (long)(Math.pow((double) level, 2.2D) + 20);
        }
        return exp;
    }

    public long getExpForLevel(int level) {
        return (long)(Math.pow((double) level, 2.2D) + 50);
    }

}
