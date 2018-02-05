package pl.understandable.understandable_app.user.data.enums;

/**
 * Created by Marcin Zielonka on 2018-02-05.
 */

public enum UserTitle {

    TITLE_1("Początkujący", 1),
    TITLE_2("Uczeń", 5),
    TITLE_3("Amator", 10),
    TITLE_4("Spragniony Wiedzy", 15),
    TITLE_5("Pilny", 20),
    TITLE_6("Systematyczny", 25),
    TITLE_7("Obeznany", 30),
    TITLE_8("Umiejętny", 40),
    TITLE_9("Wyuczony", 50),
    TITLE_10("Gorliwy", 60),
    TITLE_11("Doświadczony", 70),
    TITLE_12("Dwujęzyczny", 80),
    TITLE_13("Bezbłędny", 90),
    TITLE_14("Mistrz", 100);

    private String title;
    private int minLevel;

    private UserTitle(String title, int minLevel) {
        this.title = title;
        this.minLevel = minLevel;
    }

    public String getTitle() {
        return title;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public static UserTitle getTitleByLevel(int level) {
        UserTitle title = TITLE_1;
        for(UserTitle t : UserTitle.values()) {
            if(level >= t.getMinLevel() && t.getMinLevel() > title.getMinLevel()) {
                title = t;
            }
        }
        return title;
    }

}
