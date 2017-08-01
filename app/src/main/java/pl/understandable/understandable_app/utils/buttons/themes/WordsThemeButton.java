package pl.understandable.understandable_app.utils.buttons.themes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.themes.ThemeType;
import pl.understandable.understandable_app.fragments.theme.ThemeChoiceFragment;
import pl.understandable.understandable_app.utils.FragmentUtil;
import pl.understandable.understandable_app.utils.buttons.words.WordsBaseButton;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Marcin on 2017-07-05.
 */

public class WordsThemeButton extends ThemeBaseButton {

    private List<WordsThemeButton> allThemes;
    private ThemeType theme;

    public WordsThemeButton(Context context, ThemeType theme, List<WordsThemeButton> allThemes) {
        super(context, theme, false);
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
            this.setChecked(true);
        } else {
            image.setImageAlpha(ITEM_NOT_CHOSEN);
            this.setChecked(false);
        }
    }

    @Override
    protected void setOnClickListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isChecked()) {
                    image.setImageAlpha(ITEM_CHOSEN);
                    setChecked(true);
                    context.setTheme(theme.getThemeId());
                    refreshCurrentComponents();
                    setCurrentThemeInSharedPreferences(theme.getThemeId());
                    for(WordsThemeButton t : allThemes) {
                        if(t.getTheme().equals(theme)) {
                            continue;
                        }
                        t.getImage().setImageAlpha(ITEM_NOT_CHOSEN);
                        t.setChecked(false);
                    }
                }
            }
        });
    }

    private void setSize() {
        int imageSize = (int) super.context.getResources().getDimension(R.dimen.f_theme_choice_icon_size);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(imageSize, imageSize);
        super.image.setLayoutParams(layoutParams);
        TypedValue outValue = new TypedValue();
        context.getResources().getValue(R.dimen.f_choice_icon_text_factor, outValue, true);
        float factor = outValue.getFloat();
        float textSizeInPixels = super.text.getTextSize() * factor;
        super.text.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeInPixels);
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
        changeBackgroundColor(activity, R.id.toolbar, R.attr.accent_color);
        changeBackgroundColor(activity, R.id.nav_view, R.attr.background_color);
        changeBackgroundColor(activity, R.id.layout_for_fragments, R.attr.background_color);
        ThemeChoiceFragment newFragment = new ThemeChoiceFragment();
        ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction().replace(R.id.layout_for_fragments, newFragment, FragmentUtil.F_THEME_CHOICE).commit();
        changeComponentsInNavigationDrawer(activity);
    }

    private void changeBackgroundColor(Activity activity, int id, int attrColor) {
        activity.findViewById(id).setBackgroundColor(colorUtil.getColor(attrColor));
    }

    private void changeComponentsInNavigationDrawer(Activity activity) {
        NavigationView navigationView = (NavigationView)activity.findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setItemTextColor(ColorStateList.valueOf(colorUtil.getColor(R.attr.text_1_color)));
        headerView.findViewById(R.id.nav_header_view).setBackgroundColor(colorUtil.getColor(R.attr.accent_color));
    }

    private void setCurrentThemeInSharedPreferences(int themeId) {
        String sharedPrefFileName = context.getResources().getString(R.string.sp_preferences_file_key);
        String sharedPrefThemeKey = context.getResources().getString(R.string.sp_theme_key);
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPrefFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(sharedPrefThemeKey, themeId);
        editor.commit();
    }

}
