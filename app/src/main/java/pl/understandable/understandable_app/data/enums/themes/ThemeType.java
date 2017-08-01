package pl.understandable.understandable_app.data.enums.themes;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;

/**
 * Created by Marcin on 2017-07-05.
 */

public enum ThemeType implements Identifiable {

    THEME_1("Canus", "GrayTheme", R.drawable.f_words_choice_base, R.style.DefaultTheme),
    THEME_2("Nox Atra", "NightTheme", R.drawable.f_theme_choice_night_theme, R.style.NightTheme);

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
