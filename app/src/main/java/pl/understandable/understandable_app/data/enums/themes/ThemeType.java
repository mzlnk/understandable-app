package pl.understandable.understandable_app.data.enums.themes;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2017-07-05.
 */

public enum ThemeType implements Identifiable {

    THEME_DAY("Dzień", "GrayTheme", R.drawable.f_theme_choice_default, R.style.DefaultTheme),
    THEME_NIGHT("Noc", "NightTheme", R.drawable.f_theme_choice_night, R.style.NightTheme);

    private String name;
    private String resName;
    private int resId;
    private int styleId;

    private ThemeType(String name, String resName, int resId, int styleId) {
        this.name = name;
        this.resName = resName;
        this.resId = resId;
        this.styleId = styleId;
    }

    public String getName() {
        return name;
    }

    public String getResName() {
        return resName;
    }

    public int getResId() {
        return resId;
    }

    public int getThemeId() {
        return styleId;
    }

}
