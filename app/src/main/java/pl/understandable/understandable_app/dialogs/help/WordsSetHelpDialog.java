package pl.understandable.understandable_app.dialogs.help;

import android.content.Context;

import pl.understandable.understandable_app.R;

/**
 * Created by Marcin Zielonka on 2018-02-02.
 */

public class WordsSetHelpDialog extends DefaultHelpDialog {

    public WordsSetHelpDialog(Context context) {
        super(context);
    }

    @Override
    public String getHelpId() {
        return "d_help_words_set";
    }

    @Override
    public void addContent() {
        content.add(new HelpContent(0, R.drawable.h_words_set_name, R.string.h_words_set_name));
        content.add(new HelpContent(1, R.drawable.h_words_set_description, R.string.h_words_set_description));
        content.add(new HelpContent(2, R.drawable.h_words_set_delete, R.string.h_words_set_delete));
        content.add(new HelpContent(3, R.drawable.h_words_set_start_learning, R.string.h_words_set_start_learning));
        content.add(new HelpContent(4, R.drawable.h_help, R.string.h_help, true));
    }

}
