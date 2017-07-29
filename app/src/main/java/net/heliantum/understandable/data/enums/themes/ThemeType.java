package net.heliantum.understandable.data.enums.themes;

import net.heliantum.understandable.R;
import net.heliantum.understandable.data.enums.Identifiable;

/**
 * Created by Marcin on 2017-07-05.
 */

public enum ThemeType implements Identifiable {

    THEME_1("Canus", "GrayTheme", R.drawable.f_words_choice_base, R.style.DefaultTheme),
    THEME_2("Nox Atra", "NightTheme", R.drawable.f_theme_choice_night_theme, R.style.NightTheme);
    //THEME_3("Ver", "GreenTheme", R.drawable.f_theme_choice_green_theme, R.style.GreenTheme),
    //THEME_4("Aestas", "OrangeTheme", R.drawable.f_theme_choice_orange_theme, R.style.OrangeTheme),
    //THEME_5("Autumnum", "RedTheme", R.drawable.f_theme_choice_red_theme, R.style.RedTheme),
    //THEME_6("Hiems", "BlueTheme", R.drawable.f_theme_choice_blue_theme, R.style.BlueTheme);

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
