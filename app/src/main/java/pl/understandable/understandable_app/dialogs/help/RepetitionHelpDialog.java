package pl.understandable.understandable_app.dialogs.help;

import android.content.Context;

import pl.understandable.understandable_app.R;

/**
 * Created by Marcin Zielonka on 2018-02-02.
 */

public class RepetitionHelpDialog extends HelpDialog {

    public RepetitionHelpDialog(Context context) {
        super(context);
    }

    @Override
    public String getHelpId() {
        return "d_help_repetition";
    }

    @Override
    public void addContent() {
        content.add(new HelpContent(0, R.drawable.h_repetition_navigate, R.string.h_repetition_navigate));
        content.add(new HelpContent(1, R.drawable.h_repetition_options, R.string.h_repetition_options));
        content.add(new HelpContent(2, R.drawable.h_repetition_finish, R.string.h_repetition_finish));
        content.add(new HelpContent(3, R.drawable.h_help, R.string.h_help, true));
    }

}
