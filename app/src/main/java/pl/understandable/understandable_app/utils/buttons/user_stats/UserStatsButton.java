package pl.understandable.understandable_app.utils.buttons.user_stats;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.user.data.enums.buttons_data.UserStats;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin Zielonka on 2017-12-10.
 */

public class UserStatsButton extends UserStatsBaseButton {

    private UserStats stat;
    private boolean large;

    private TextView text2;

    public UserStatsButton(Context context, UserStats stat, String statValue, boolean large) {
        super(context, stat);
        text2 = new TextView(context);
        text2.setText(statValue);
        this.stat = stat;
        this.large = large;
        prepare();
    }

    public TextView getText2() {
        return text2;
    }

    @Override
    protected void setChoiceState() {
        image.setAlpha(ITEM_CHOSEN);
        image.setImageResource(stat.getResId());
    }

    @Override
    protected void setOnClickListener() {}

    @Override
    protected void prepareText() {
        super.prepareText();

        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = context.getResources().getDimensionPixelSize(R.dimen.f_user_stats_margin);
        params.setMargins(0, margin, 0, margin);

        text2.setLayoutParams(params);

        text2.setGravity(Gravity.CENTER);
        text2.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    @Override
    protected void setTextColor() {
        super.setTextColor();
        text2.setTextColor(colorUtil.getColor(R.attr.text_1_color));
    }

    @Override
    protected void setSize() {
        int imageSize;
        TypedValue outValue;

        if(large) {
            imageSize = (int) context.getResources().getDimension(R.dimen.f_user_stats_icon_large_size);
            outValue = new TypedValue();
            context.getResources().getValue(R.dimen.f_user_stats_text_large_factor, outValue, true);

        } else {
            imageSize = (int) context.getResources().getDimension(R.dimen.f_user_stats_icon_medium_size);
            outValue = new TypedValue();
            context.getResources().getValue(R.dimen.f_user_stats_text_medium_factor, outValue, true);
        }

        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(imageSize, imageSize);
        image.setLayoutParams(layoutParams);

        float factor = outValue.getFloat();
        float text2SizeInPixels = text.getTextSize() * factor;
        float textSizeInPixels = text2SizeInPixels * 0.6F;
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeInPixels);
        text2.setTextSize(TypedValue.COMPLEX_UNIT_PX, text2SizeInPixels);
    }

}
