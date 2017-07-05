package net.heliantum.understandable.fragments.utils.choice;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.annotation.ColorInt;
import android.support.design.widget.NavigationView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import net.heliantum.understandable.R;
import net.heliantum.understandable.data.enums.ThemeType;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Marcin on 2017-07-05.
 */

public class ThemeButton extends BaseButton {

    private List<ThemeButton> allThemes;
    private ThemeType theme;

    public ThemeButton(Context context, ThemeType theme, List<ThemeButton> allThemes) {
        super(context, null, theme);
        this.theme = theme;
        this.allThemes = allThemes;
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

    private ThemeType getTheme() {
        return theme;
    }

    @Override
    protected void setChoiceState() {
        if(theme.getThemeId() == getCurrentThemeId()) {
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
                    context.setTheme(theme.getThemeId());
                    refreshCurrentComponents();
                    for(ThemeButton t : allThemes) {
                        if(t.getTheme().equals(theme)) {
                            continue;
                        }
                        t.getImage().setImageAlpha(ITEM_NOT_CHOSEN);
                    }
                }
            }
        });
    }

    private void setSize() {
        int imageSize = (int) super.context.getResources().getDimension(R.dimen.f_words_choice_mode_icon_size);
        int textSize = (int) super.context.getResources().getDimension(R.dimen.f_words_choice_mode_icon_text);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(imageSize, imageSize);
        super.image.setLayoutParams(layoutParams);
        super.text.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    private void setImage() {
        super.image.setImageResource(theme.getResId());
    }

    private int getCurrentThemeId() {
        try {
            Class<?> wrapper = Context.class;
            Method method = wrapper.getMethod("getThemeResId");
            method.setAccessible(true);
            return (Integer) method.invoke(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void refreshCurrentComponents() {
        Activity activity = (Activity) context;
        activity.findViewById(R.id.toolbar).setBackgroundColor(getColor(R.attr.accent_color));
        activity.findViewById(R.id.f_theme_choice_theme_names_layout).setBackgroundColor(getColor(R.attr.background_color));
        activity.findViewById(R.id.nav_view).setBackgroundColor(getColor(R.attr.background_color));
        ((NavigationView)activity.findViewById(R.id.nav_view)).setItemTextColor(ColorStateList.valueOf(getColor(R.attr.text_1_color)));
        ((TextView)activity.findViewById(R.id.f_theme_choice_title)).setTextColor(getColor(R.attr.text_1_color));
        ((TextView)activity.findViewById(R.id.f_theme_choice_title)).setBackgroundColor(getColor(R.attr.background_color));
        for(ThemeButton themeButton : allThemes) {
            themeButton.updateTextColor();
        }
    }

}
