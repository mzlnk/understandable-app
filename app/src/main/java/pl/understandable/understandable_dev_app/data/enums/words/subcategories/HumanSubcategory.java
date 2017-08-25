package pl.understandable.understandable_dev_app.data.enums.words.subcategories;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2017-08-24.
 */

public enum HumanSubcategory implements Identifiable {

    BODY_PARTS("części ciała"),
    APPEARANCE("wygląd zewnętrzny"),
    CLOTHES("ubrania"),
    PERSONALITY("osobowość"),
    FEELING("uczucia"),
    PERSONAL_INFO("dane osobowe"),
    HUMAN_PHRASES("wyrażenia"),
    DAILY_ACTIVITIES("czynności codzienne");

    private String name;

    private HumanSubcategory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getResId() {
        return R.drawable.f_words_choice_unchecked;
    }

}
