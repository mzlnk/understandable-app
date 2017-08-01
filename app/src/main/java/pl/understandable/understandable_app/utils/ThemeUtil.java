package pl.understandable.understandable_app.utils;

import android.content.Context;

import java.lang.reflect.Method;

import pl.understandable.understandable_app.R;

/**
 * Created by Marcin on 2017-08-01.
 */

public class ThemeUtil {

    private Context context;

    public ThemeUtil(Context context) {
        this.context = context;
    }

    public int getCurrentThemeId() {
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

    public boolean isDefaultTheme() {
        return getCurrentThemeId() == R.style.DefaultTheme;
    }

    public boolean isNightTheme() {
        return getCurrentThemeId() == R.style.NightTheme;
    }

}
