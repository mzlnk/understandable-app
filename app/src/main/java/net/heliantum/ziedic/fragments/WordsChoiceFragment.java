package net.heliantum.ziedic.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.database.data.DatabaseData;
import net.heliantum.ziedic.database.entity.LanguageCategory;
import net.heliantum.ziedic.database.entity.LanguageEntity;
import net.heliantum.ziedic.database.entity.LanguageType;
import net.heliantum.ziedic.utils.CurrentlyChosenWordsData;
import net.heliantum.ziedic.utils.Debug;
import net.heliantum.ziedic.utils.LanguageLearningDirection;
import net.heliantum.ziedic.utils.LearningOption;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */

public class WordsChoiceFragment extends Fragment {

    private List<LanguageCategory> chosenCategories = new ArrayList<>();
    private List<LanguageType> chosenTypes = new ArrayList<>();
    private LearningOption learningOption = LearningOption.REPETITION;
    private LanguageLearningDirection learningDirection = LanguageLearningDirection.POLISH_TO_ENGLISH;

    private List<LanguageEntity> chosenWords = new ArrayList<>();

    private CheckBox[] categories = new CheckBox[17];
    private CheckBox[] types = new CheckBox[7];
    private RadioButton[] modes = new RadioButton[2];
    private RadioButton[] ways = new RadioButton[3];
    private Button start;

    public WordsChoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_words_choice, container, false);

        for(LanguageType t : LanguageType.values()) {
            chosenTypes.add(t);
        }

        registerFields(rootView);
        registerListeners();


        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
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

        modes[0] = (RadioButton) v.findViewById(R.id.mode_0);
        modes[1] = (RadioButton) v.findViewById(R.id.mode_1);

        ways[0] = (RadioButton) v.findViewById(R.id.way_0);
        ways[1] = (RadioButton) v.findViewById(R.id.way_1);
        ways[2] = (RadioButton) v.findViewById(R.id.way_2);

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

            modes[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    LearningOption option = LearningOption.getEnumFromPolish(String.valueOf(modes[n].getText()));
                    learningOption = option;
                }
            });
        }

        for(int i = 0; i < 3; i++) {
            final int n = i;

            ways[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    LanguageLearningDirection direction = LanguageLearningDirection.getEnumFromPolish(String.valueOf(ways[n].getText()));
                    learningDirection = direction;
                }
            });
        }

        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(chosenCategories.size() > 0 ) {
                    if(chosenTypes.size() > 0) {

                        final ProgressDialog pd = new ProgressDialog(getContext());
                        pd.setCancelable(false);
                        pd.setMessage("Ładowanie zestawu słów");
                        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        pd.setProgress(0);
                        pd.setMax(100);
                        pd.show();

                        chosenWords = null;
                        chosenWords = new ArrayList<>();

                        chosenWords = DatabaseData.languageEntites.getSpecifiedEntities(chosenCategories, chosenTypes);
                        pd.dismiss();

                        Debug.sendMessage(getContext(), "chosenWords: " + chosenWords.size() + ", chosenCategories: " + chosenCategories.size() + ", chosenTypes: , "+ chosenTypes.size(), Toast.LENGTH_LONG);
                        if(chosenWords.size() > 0) {

                            CurrentlyChosenWordsData.chosenWords = chosenWords;
                            CurrentlyChosenWordsData.direction = learningDirection;

                            switch(learningOption) {
                                case REPETITION:
                                    FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                                    transaction1.replace(R.id.layout_for_fragments, new WordsRepetitionFragment());
                                    transaction1.commit();
                                    break;
                                case QUIZ:
                                    FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                                    transaction2.replace(R.id.layout_for_fragments, QuizFragment.newInstance(0, 0));
                                    transaction2.commit();//todo: put FragmentTransaction object out of switch instruction
                                    //todo: add if statements (words >= 4)
                                    //Toast.makeText(getContext(), "Tryb quiz obecnie niedostępny", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        }
                        else {
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.layout_for_fragments, new NoWordsErrorFragment());
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }

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
