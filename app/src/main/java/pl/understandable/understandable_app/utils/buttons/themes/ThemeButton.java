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
import pl.understandable.understandable_app.utils.ThemeUtil;

import java.util.List;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka on 2017-07-05.
 */

public class ThemeButton extends ThemeBaseButton {

    private List<ThemeButton> allThemes;
    private ThemeType theme;

    public ThemeButton(Context context, ThemeType theme, List<ThemeButton> allThemes) {
        super(context, theme, false);
        this.theme = theme;
        this.allThemes = allThemes;
        prepare();
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
        if(theme.getThemeId() == new ThemeUtil(context).getCurrentThemeId()) {
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
                    for(ThemeButton t : allThemes) {
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

    private void setImage() {
        super.image.setImageResource(theme.getResId());
    }

    private void refreshCurrentComponents() {
        Activity activity = (Activity) context;
        changeBackgroundColor(activity, R.id.layout_for_fragments, R.attr.background_color);
        ThemeChoiceFragment newFragment = new ThemeChoiceFragment();
        ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction().replace(R.id.layout_for_fragments, newFragment, redirectTo(F_START)).commit();
        changeComponentsInNavigationDrawer(activity);
    }

    private void changeBackgroundColor(Activity activity, int id, int attrColor) {
        activity.findViewById(id).setBackgroundColor(colorUtil.getColor(attrColor));
    }

    private void changeComponentsInNavigationDrawer(Activity activity) {
        NavigationView navigationView = (NavigationView)activity.findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setItemTextColor(ColorStateList.valueOf(colorUtil.getColor(R.attr.text_1_color)));
        TextView title = (TextView) headerView.findViewById(R.id.nav_header_view).findViewById(R.id.navigation_title);
        title.setTextColor(colorUtil.getColor(R.attr.text_1_color));

        ThemeUtil themeUtil = new ThemeUtil(context);
        if(themeUtil.isDefaultTheme()) {
            navigationView.setBackgroundResource(R.drawable.field_rounded_white);
        } else {
            navigationView.setBackgroundResource(R.drawable.field_rounded_dark_dark_gray);
        }
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
