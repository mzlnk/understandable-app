package net.heliantum.understandable.data.enums;

import net.heliantum.understandable.R;

/**
 * Created by Marcin on 2017-07-05.
 */

public enum ThemeType implements Identifiable {

    THEME_1("Nigrum et album", "GrayTheme", R.drawable.f_words_choice_base, R.style.GrayTheme),
    THEME_2("Nox atra", "NightTheme", R.drawable.f_words_choice_base, R.style.NightTheme),
    THEME_3("Ver", "GreenTheme", R.drawable.f_words_choice_base, R.style.GreenTheme),
    THEME_4("Aestas", "OrangeTheme", R.drawable.f_words_choice_base, R.style.OrangeTheme),
    THEME_5("Autumnum", "RedTheme", R.drawable.f_words_choice_base, R.style.RedTheme),
    THEME_6("Hiems", "BlueTheme", R.drawable.f_words_choice_base, R.style.BlueTheme);

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
