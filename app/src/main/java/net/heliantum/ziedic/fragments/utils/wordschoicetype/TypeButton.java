package net.heliantum.ziedic.fragments.utils.wordschoicetype;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.data.DataParams;
import net.heliantum.ziedic.data.enums.LanguageType;
import net.heliantum.ziedic.utils.font.Font;

/**
 * Created by Marcin on 2017-05-07.
 */

public class TypeButton {

    private ImageView image;
    private TextView text;

    private LanguageType type;
    private DataParams params;

    public TypeButton(Context context, LanguageType type, DataParams params) {
        this.type = type;
        this.params = params;
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
        text.setText(type.getName());
        text.setGravity(Gravity.CENTER);
        text.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    private void setChoiceState() {
        if(params.isChosen(type)) {
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
                    params.addType(type);
                } else {
                    image.setImageAlpha(150);
                    params.removeType(type);
                }
            }
        });
    }

}
