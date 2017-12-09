package pl.understandable.understandable_app.utils.buttons.user;

import android.content.Context;

import pl.understandable.understandable_app.user.data.buttons_data.UserOptions;

/**
 * Created by Marcin Zielonka on 2017-12-09.
 */

public class UserButton extends UserBaseButton {

    private UserOptions option;

    public UserButton(Context context, UserOptions option) {
        super(context, option);
        this.option = option;
        prepare();
    }

    @Override
    protected void setChoiceState() {
        image.setAlpha(ITEM_CHOSEN);
        image.setImageResource(option.getResId());
    }

    @Override
    protected void setOnClickListener() {}

}
