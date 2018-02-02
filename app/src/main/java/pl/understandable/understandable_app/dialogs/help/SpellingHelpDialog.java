package pl.understandable.understandable_app.dialogs.help;

import android.content.Context;

import pl.understandable.understandable_app.R;

/**
 * Created by Marcin Zielonka on 2018-02-02.
 */

public class SpellingHelpDialog extends DefaultHelpDialog {

    public SpellingHelpDialog(Context context) {
        super(context);
    }

    @Override
    public String getHelpId() {
        return "d_help_spelling";
    }

    @Override
    public void addContent() {
        content.add(new HelpContent(0, R.drawable.h_spelling_navigate, R.string.h_spelling_navigate));
        content.add(new HelpContent(1, R.drawable.h_spelling_text, R.string.h_spelling_text));
        content.add(new HelpContent(2, R.drawable.h_spelling_options, R.string.h_spelling_options));
        content.add(new HelpContent(3, R.drawable.h_spelling_finish, R.string.h_spelling_finish));
        content.add(new HelpContent(4, R.drawable.h_help, R.string.h_help, true));
    }

}
