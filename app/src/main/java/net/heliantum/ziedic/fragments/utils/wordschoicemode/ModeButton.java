package net.heliantum.ziedic.fragments.utils.wordschoicemode;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.data.DataParams;
import net.heliantum.ziedic.data.enums.LearningMode;
import net.heliantum.ziedic.utils.font.Font;

import java.util.List;

/**
 * Created by Marcin on 2017-05-07.
 */

public class ModeButton {

    private ImageView image;
    private TextView text;

    private List<ModeButton> allModes;
    private LearningMode mode;
    private DataParams params;

    public ModeButton(Context context, LearningMode mode, List<ModeButton> allModes, DataParams params) {
        this.mode = mode;
        this.params = params;
        this.allModes = allModes;
        this.image = new ImageView(context);
        this.text = new TextView(context);
        prepare();
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getText() {
        return text;
    }

    public LearningMode getMode() {
        return mode;
    }

    private void prepare() {
        prepareImage();
        prepareText();
    }

    private void prepareImage() {
        image.setImageResource(R.drawable.f_words_choice_base_test_selected);
        image.setClickable(true);
        setChoiceState();
        setOnClickListener();
    }

    private void prepareText() {
        text.setText(mode.getName());
        text.setGravity(Gravity.CENTER);
        text.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    private void setChoiceState() {
        if(params.isChosen(mode)) {
            image.setImageAlpha(255);
        } else {
            image.setImageAlpha(150);
        }
    }

    private void setOnClickListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(image.getImageAlpha() == 150) {
                    image.setImageAlpha(255);
                    params.setMode(mode);
                    for(ModeButton m : allModes) {
                        if(m.getMode().equals(mode)) {
                            continue;
                        }
                        m.getImage().setImageAlpha(150);
                    }
                }
            }
        });
    }

}
