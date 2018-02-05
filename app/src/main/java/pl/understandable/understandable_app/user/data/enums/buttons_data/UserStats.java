package pl.understandable.understandable_app.user.data.enums.buttons_data;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2017-12-10.
 */

public enum UserStats implements Identifiable {

    TIME_LEARNT("czas spędzony\nna nauce", R.drawable.f_user_stats_time_learnt, -1),
    WORDS_SETS_DOWNLOADED("pobrane\nzestawy słówek", R.drawable.f_user_stats_words_sets_downloaded, -1),
    AS_LIST("lista", R.drawable.f_user_stats_as_list, 0),
    AS_REPETITION("powtarzanie", R.drawable.f_user_stats_as_repetition, 1),
    AS_QUIZ("quiz", R.drawable.f_user_stats_as_quiz, 2),
    AS_SPELLING("pisownia", R.drawable.f_user_stats_as_spelling, 3);

    private String name;
    private int resId;
    private int pos;

    private UserStats(String name, int resId, int pos) {
        this.name = name;
        this.resId = resId;
        this.pos = pos;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getResId() {
        return resId;
    }

    public static UserStats getEnumByPosInUserStatistics(int pos) {
        for(UserStats e : UserStats.values()) {
            if(e.pos == pos) {
                return e;
            }
        }
        return null;
    }

}
