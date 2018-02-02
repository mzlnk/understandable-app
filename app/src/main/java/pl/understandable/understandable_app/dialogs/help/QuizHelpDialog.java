package pl.understandable.understandable_app.dialogs.help;

import android.content.Context;

import pl.understandable.understandable_app.R;

/**
 * Created by Marcin Zielonka on 2018-02-02.
 */

public class QuizHelpDialog extends DefaultHelpDialog {

    public QuizHelpDialog(Context context) {
        super(context);
    }

    @Override
    public String getHelpId() {
        return "d_help_quiz";
    }

    @Override
    public void addContent() {
        content.add(new HelpContent(0, R.drawable.h_quiz_answers, R.string.h_quiz_answers));
        content.add(new HelpContent(1, R.drawable.h_quiz_finish, R.string.h_quiz_finish));
        content.add(new HelpContent(2, R.drawable.h_help, R.string.h_help, true));
    }

}
