package net.heliantum.understandable.fragments.utils.choice;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import net.heliantum.understandable.R;
import net.heliantum.understandable.data.DataParams;
import net.heliantum.understandable.data.enums.LearningWay;

import java.util.List;

/**
 * Created by Marcin on 2017-05-07.
 */

public class WayButton extends BaseButton {

    private List<WayButton> allWays;
    private LearningWay way;

    public WayButton(Context context, DataParams dataParams, LearningWay way, List<WayButton> allWays) {
        super(context, dataParams, way);
        this.way = way;
        this.allWays = allWays;
        prepare();
        setSize();
        setImage();
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getText() {
        return text;
    }

    private LearningWay getWay() {
        return way;
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(way)) {
            image.setImageAlpha(ITEM_CHOSEN);
        } else {
            image.setImageAlpha(ITEM_NOT_CHOSEN);
        }
    }

    @Override
    protected void setOnClickListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(image.getImageAlpha() == ITEM_NOT_CHOSEN) {
                    image.setImageAlpha(ITEM_CHOSEN);
                    dataParams.setWay(way);
                    for(WayButton w : allWays) {
                        if(w.getWay().equals(way)) {
                            continue;
                        }
                        w.getImage().setImageAlpha(ITEM_NOT_CHOSEN);
                    }
                }
            }
        });
    }

    private void setSize() {
        int imageSize = (int) super.context.getResources().getDimension(R.dimen.f_words_choice_type_icon_size);
        int textSize = (int) super.context.getResources().getDimension(R.dimen.f_words_choice_type_icon_text);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(imageSize, imageSize);
        super.image.setLayoutParams(layoutParams);
        super.text.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    private void setImage() {
        super.image.setImageResource(way.getResId());
    }

}
