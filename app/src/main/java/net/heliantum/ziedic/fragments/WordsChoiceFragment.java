package net.heliantum.ziedic.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.database.entity.LanguageCategory;
import net.heliantum.ziedic.database.entity.LanguageType;
import net.heliantum.ziedic.utils.LearningOption;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */

public class WordsChoiceFragment extends Fragment {

    private List<LanguageCategory> chosenCategories = new ArrayList<>();
    private List<LanguageType> chosenTypes = new ArrayList<>();
    private LearningOption learningOption = LearningOption.MEMORY;

    private CheckBox[] categories = new CheckBox[17];
    private CheckBox[] types = new CheckBox[7];
    private RadioButton[] ways = new RadioButton[2];
    private Button start;

    public WordsChoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Toast.makeText(getContext(), "onCreate method", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getContext(), "onCreateView method", Toast.LENGTH_SHORT).show();
        View rootView = inflater.inflate(R.layout.fragment_words_choice, container, false);

        for(LanguageType t : LanguageType.values()) {
            chosenTypes.add(t);
        }

        registerFields(rootView);
        registerListeners();


        return rootView;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getContext(), "onDestroy method", Toast.LENGTH_SHORT).show();
        //todo: code here
        super.onDestroy();
    }

    private void registerFields(View v) {

        categories[0] = (CheckBox) v.findViewById(R.id.category_0);
        categories[1] = (CheckBox) v.findViewById(R.id.category_1);
        categories[2] = (CheckBox) v.findViewById(R.id.category_2);
        categories[3] = (CheckBox) v.findViewById(R.id.category_3);
        categories[4] = (CheckBox) v.findViewById(R.id.category_4);
        categories[5] = (CheckBox) v.findViewById(R.id.category_5);
        categories[6] = (CheckBox) v.findViewById(R.id.category_6);
        categories[7] = (CheckBox) v.findViewById(R.id.category_7);
        categories[8] = (CheckBox) v.findViewById(R.id.category_8);
        categories[9] = (CheckBox) v.findViewById(R.id.category_9);
        categories[10] = (CheckBox) v.findViewById(R.id.category_10);
        categories[11] = (CheckBox) v.findViewById(R.id.category_11);
        categories[12] = (CheckBox) v.findViewById(R.id.category_12);
        categories[13] = (CheckBox) v.findViewById(R.id.category_13);
        categories[14] = (CheckBox) v.findViewById(R.id.category_14);
        categories[15] = (CheckBox) v.findViewById(R.id.category_15);
        categories[16] = (CheckBox) v.findViewById(R.id.category_16);

        types[0] = (CheckBox) v.findViewById(R.id.type_0);
        types[1] = (CheckBox) v.findViewById(R.id.type_1);
        types[2] = (CheckBox) v.findViewById(R.id.type_2);
        types[3] = (CheckBox) v.findViewById(R.id.type_3);
        types[4] = (CheckBox) v.findViewById(R.id.type_4);
        types[5] = (CheckBox) v.findViewById(R.id.type_5);
        types[6] = (CheckBox) v.findViewById(R.id.type_6);

        ways[0] = (RadioButton) v.findViewById(R.id.way_0);
        ways[1] = (RadioButton) v.findViewById(R.id.way_1);

        start = (Button) v.findViewById(R.id.start);
    }

    private void registerListeners() {

        for(int i = 0; i < 17; i++) {
            final int n = i;

            categories[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    LanguageCategory category = LanguageCategory.getEnumFromPolish(String.valueOf(categories[n].getText()));

                    if(categories[n].isChecked()) {
                        chosenCategories.add(category);
                    }
                    else {
                        chosenCategories.remove(category);
                    }
                }
            });
        }

        for(int i = 0; i < 7; i++) {
            final int n = i;

            types[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    LanguageType type = LanguageType.getEnumFromPolish(String.valueOf(types[n].getText()));

                    if(types[n].isChecked()) {
                        chosenTypes.add(type);
                    }
                    else {
                        chosenTypes.remove(type);
                    }
                }
            });

        }

        for(int i = 0; i < 2; i++) {
            final int n = i;

            ways[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    LearningOption option = LearningOption.getEnumFromPolish(String.valueOf(ways[n].getText()));
                    learningOption = option;
                }
            });
        }

        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(chosenCategories.size() > 0 ) {
                    if(chosenTypes.size() > 0) {
                        StringBuilder builder = new StringBuilder();
                        for(LanguageCategory c : chosenCategories) {
                            builder.append(c.getName() + ", ");
                        }
                        for(LanguageType t : chosenTypes) {
                            builder.append(t.getName() + ", ");
                        }
                        builder.append(learningOption.getName());
                        Toast.makeText(getContext(), builder.toString(), Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getContext(), "Brak wybranych rodzajów", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getContext(), "Brak wybranych kategorii", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

}
