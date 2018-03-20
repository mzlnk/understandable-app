package pl.understandable.understandable_app.dialogs.help;

import android.content.Context;

import pl.understandable.understandable_app.R;

/**
 * Created by Marcin Zielonka on 2018-02-02.
 */

public class IrregularVerbsRepetitionHelpDialog extends HelpDialog {

    public IrregularVerbsRepetitionHelpDialog(Context context) {
        super(context);
    }

    @Override
    public String getHelpId() {
        return "d_help_irregular_verbs_repetition";
    }

    @Override
    public void addContent() {
        content.add(new HelpContent(0, R.drawable.h_irregular_verbs_show_word, R.string.h_irregular_verbs_show_word));
        content.add(new HelpContent(1, R.drawable.h_irregular_verbs_options, R.string.h_irregular_verbs_options));
        content.add(new HelpContent(2, R.drawable.h_help, R.string.h_help, true));
    }

}
