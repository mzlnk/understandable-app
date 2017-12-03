package pl.understandable.understandable_app.utils.buttons.achievements;

import android.content.Context;
import android.view.View;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.user.data.achievements.Achievement;

/**
 * Created by Marcin Zielonka on 2017-12-03.
 */

public class AchievementButton extends AchievementBaseButton {

    private Achievement achievement;

    public AchievementButton(Context context, Achievement achievement) {
        super(context, achievement, false);
        this.achievement = achievement;
        prepare();
    }

    @Override
    protected void setChoiceState() {
        if(achievement.isAchieved()) {
            image.setAlpha(ITEM_CHOSEN);
            image.setImageResource(achievement.getResId());
        } else {
            image.setAlpha(ITEM_NOT_CHOSEN);
            image.setImageResource(R.drawable.f_user_achievements_not_done);
        }
    }

    @Override
    protected void setOnClickListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: add dialog box here
            }
        });
    }

}
