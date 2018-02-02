package pl.understandable.understandable_app.dialogs.help;

import android.content.Context;

import pl.understandable.understandable_app.R;

/**
 * Created by Marcin Zielonka on 2018-02-02.
 */

public class ProfileHelpDialog extends DefaultHelpDialog {

    public ProfileHelpDialog(Context context) {
        super(context);
    }

    @Override
    public String getHelpId() {
        return "d_help_profile";
    }

    @Override
    public void addContent() {
        content.add(new HelpContent(0, R.drawable.h_profile_change_name, R.string.h_profile_change_name));
        content.add(new HelpContent(1, R.drawable.h_profile_level, R.string.h_profile_level));
        content.add(new HelpContent(2, R.drawable.h_profile_title, R.string.h_profile_title));
        content.add(new HelpContent(3, R.drawable.h_profile_options, R.string.h_profile_options));
        content.add(new HelpContent(4, R.drawable.h_help, R.string.h_help, true));
    }

}
