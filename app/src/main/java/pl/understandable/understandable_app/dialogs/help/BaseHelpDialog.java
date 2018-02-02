package pl.understandable.understandable_app.dialogs.help;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marcin Zielonka on 2018-02-02.
 */

public abstract class BaseHelpDialog extends Dialog implements View.OnClickListener {

    protected Context context;

    protected Map<Integer, Integer> content = new HashMap<>();

    public BaseHelpDialog(Context context) {
        super(context);
        this.context = context;
    }

    public abstract void addContent();

    protected static class ResourceParser {

        public static String getString(Context context, int resId) {
            return context.getResources().getString(resId);
        }

    }

}
