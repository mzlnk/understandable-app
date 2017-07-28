package net.heliantum.understandable.fragments.custom_words.other;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import net.heliantum.understandable.R;
import net.heliantum.understandable.webservice.WebService;

public class DownloadCustomWordsSetFragment extends Fragment {

    private EditText codeField;

    public DownloadCustomWordsSetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.f_download_words_set, container, false);

        codeField = (EditText) rootView.findViewById(R.id.f_download_words_set_code_field);
        Button submit = (Button) rootView.findViewById(R.id.f_download_words_set_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = codeField.getText().toString();
                code = code.toUpperCase();
                System.out.println("==============================");
                System.out.println("WebService is starting...");
                System.out.println("==============================");
                WebService.DownloadWordsSetTask task = (WebService.DownloadWordsSetTask) new WebService.DownloadWordsSetTask(getContext()).execute(code);
            }
        });

        return rootView;
    }

}
