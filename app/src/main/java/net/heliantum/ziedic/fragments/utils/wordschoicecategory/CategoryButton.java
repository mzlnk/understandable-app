package net.heliantum.ziedic.fragments.utils.wordschoicecategory;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.corrupted.data.BaseWordsData;
import net.heliantum.ziedic.data.enums.LanguageCategory;
import net.heliantum.ziedic.utils.SizeUtil;
import net.heliantum.ziedic.utils.font.Font;
import net.heliantum.ziedic.utils.font.FontUtil;

/**
 * Created by Marcin on 2017-04-08.
 */

public class CategoryButton {

    private ImageView image;
    private TextView text;

    private LanguageCategory category;

    public CategoryButton(Context context, LanguageCategory category) {
        this.category = category;
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

        SizeUtil sizeUtil = new SizeUtil(TableRow.class);
        Point size = new Point(216, 216);
        sizeUtil.setSize(image, size);

        setChoiceState();
        setOnClickListener();
    }

    private void prepareText() {
        text.setText(category.getName());
        text.setGravity(Gravity.CENTER);

        Font font = new Font(Font.TYPEFACE_MONTSERRAT, 8.0F, Color.BLACK);
        FontUtil.setFont(text, font);

        SizeUtil sizeUtil = new SizeUtil(TableRow.class);
        Point size = new Point(86, 43);
        sizeUtil.setSize(text, size );
    }

    private void setChoiceState() {
        if(BaseWordsData.isChosen(category)) {
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
                    BaseWordsData.addCategory(category);
                } else {
                    image.setImageAlpha(150);
                    BaseWordsData.removeCategory(category);
                }
            }
        });
    }

}
