package pl.understandable.understandable_app.user.data.buttons_data;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2017-12-09.
 */

public enum UserOptions implements Identifiable {

    ACHIEVEMENTS("osiągnięcia", R.drawable.f_user_achievements),
    STATS("postępy", R.drawable.f_user_stats),
    FOLLOWED_WORDS_SETS("obserwowane\nzestawy", R.drawable.f_user_followed_words_sets);

    private String name;
    private int resId;

    private UserOptions(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getResId() {
        return resId;
    }

}
