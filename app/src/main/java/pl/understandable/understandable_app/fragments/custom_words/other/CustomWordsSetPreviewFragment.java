package pl.understandable.understandable_app.fragments.custom_words.other;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.database.entity.CustomWordsSetEntity;
import pl.understandable.understandable_app.database.repository.CustomWordEntityRepository;
import pl.understandable.understandable_app.database.repository.CustomWordsSetsRepository;
import pl.understandable.understandable_app.fragments.custom_words.choice.CustomWordsChoiceWayFragment;
import pl.understandable.understandable_app.utils.FragmentUtil;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin Zielonka
 */

public class CustomWordsSetPreviewFragment extends Fragment {

    public static final String POSITION_PARAM = "custom.words.set.preview.idParam";

    private RelativeLayout mainLayout;
    private TextView wordsSetNameInfo, wordsSetName;
    private TextView wordsSetIdInfo, wordsSetId;
    private TextView wordsSetDescriptionInfo, wordsSetDescription;
    private LinearLayout nameField, idField, descriptionField;
    private Button delete, start;

    private String id;

    public CustomWordsSetPreviewFragment() {
        // Required empty public constructor
    }

    public static CustomWordsSetPreviewFragment newInstance(String id) {
        CustomWordsSetPreviewFragment fragment = new CustomWordsSetPreviewFragment();
        Bundle args = new Bundle();
        args.putString(POSITION_PARAM, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            id = getArguments().getString(POSITION_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the wordLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_custom_words_set_preview, container, false);
        loadViewsFromXml(rootView);
        setAnimation();
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_custom_words_set_preview);
        wordsSetNameInfo = (TextView) rootView.findViewById(R.id.f_custom_words_set_preview_words_set_name_info);
        wordsSetName = (TextView) rootView.findViewById(R.id.f_custom_words_set_preview_words_set_name);
        wordsSetIdInfo = (TextView) rootView.findViewById(R.id.f_custom_words_set_preview_words_set_id_info);
        wordsSetId = (TextView) rootView.findViewById(R.id.f_custom_words_set_preview_words_set_id);
        wordsSetDescriptionInfo = (TextView) rootView.findViewById(R.id.f_custom_words_set_preview_words_set_description_info);
        wordsSetDescription = (TextView) rootView.findViewById(R.id.f_custom_words_set_preview_words_set_description);
        delete = (Button) rootView.findViewById(R.id.f_custom_words_set_preview_button_delete);
        start = (Button) rootView.findViewById(R.id.f_custom_words_set_preview_button_start_learning);
        nameField = (LinearLayout) rootView.findViewById(R.id.f_custom_words_set_preview_name_field);
        idField = (LinearLayout) rootView.findViewById(R.id.f_custom_words_set_preview_id_field);
        descriptionField = (LinearLayout) rootView.findViewById(R.id.f_custom_words_set_preview_description_field);
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        mainLayout.setAnimation(anim);
    }

    private void prepareLayout() {
        setFonts();
        prepareButtons();
        prepareFields();
        setWordsSetData();
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        wordsSetNameInfo.setTypeface(typeface);
        wordsSetName.setTypeface(typeface);
        wordsSetIdInfo.setTypeface(typeface);
        wordsSetId.setTypeface(typeface);
        wordsSetDescriptionInfo.setTypeface(typeface);
        wordsSetDescription.setTypeface(typeface);
        delete.setTypeface(typeface);
        start.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            delete.setBackgroundResource(R.drawable.field_rounded_pink);
            start.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            delete.setBackgroundResource(R.drawable.field_rounded_gray);
            start.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
    }

    private void setWordsSetData() {
        CustomWordsSetEntity wordsSet = CustomWordsSetsRepository.getEntity(id);
        wordsSetName.setText(wordsSet.getName());
        wordsSetId.setText(wordsSet.getId());
        wordsSetDescription.setText(wordsSet.getDescription());
    }

    private void prepareFields() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            nameField.setBackgroundResource(R.drawable.field_rounded_light_light_light_gray);
            idField.setBackgroundResource(R.drawable.field_rounded_light_light_light_gray);
            descriptionField.setBackgroundResource(R.drawable.field_rounded_light_light_light_gray);
        } else {
            nameField.setBackgroundResource(R.drawable.field_rounded_dark_gray);
            idField.setBackgroundResource(R.drawable.field_rounded_dark_gray);
            descriptionField.setBackgroundResource(R.drawable.field_rounded_dark_gray);
        }
    }

    private void addListeners() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("")
                        .setMessage("Czy chcesz usunąć ten zestaw?")
                        .setPositiveButton("Tak", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CustomWordsSetsRepository.removeEntity(id);
                                FragmentManager fragmentManager = getFragmentManager();
                                CustomWordsSetsListFragment fragment = new CustomWordsSetsListFragment();
                                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, FragmentUtil.F_CUSTOM_WORDS_SETS_LIST).commit();
                            }

                        })
                        .setNegativeButton("Nie", null)
                        .show();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomWordEntityRepository.create(getContext(), id);
                CustomWordsChoiceWayFragment fragment = new CustomWordsChoiceWayFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, FragmentUtil.F_CUSTOM_WORDS_CHOICE_WAY).commit();
            }
        });
        nameField.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final EditText edit = new EditText(getContext());
                edit.setText(wordsSetName.getText());
                edit.setGravity(Gravity.CENTER);
                new AlertDialog.Builder(getContext())
                        .setTitle("")
                        .setMessage("Wprowadź nową nazwę zestawu")
                        .setView(edit)
                        .setPositiveButton("zapisz", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String text = edit.getText().toString();
                                if(text.contains("\n")) {
                                    Toast.makeText(getContext(), "Nazwa nie może zawierać znaków nowej linii (ENTER)", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                if(text.length() > 40) {
                                    Toast.makeText(getContext(), "Nazwa nie może być dłuższa niż 40 znaków", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                wordsSetName.setText(edit.getText().toString());
                                CustomWordsSetsRepository.setName(id, edit.getText().toString());
                                Toast.makeText(getContext(), "Nazwa zostala zmieniona", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("anuluj", null)
                        .show();
                return false;
            }
        });
        descriptionField.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final EditText edit = new EditText(getContext());
                edit.setText(wordsSetDescription.getText());
                edit.setGravity(Gravity.CENTER);
                new AlertDialog.Builder(getContext())
                        .setTitle("")
                        .setMessage("Wprowadź nowy opis zestawu")
                        .setView(edit)
                        .setPositiveButton("zapisz", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String text = edit.getText().toString();
                                if(text.length() > 200) {
                                    Toast.makeText(getContext(), "Nazwa nie może być dłuższa niż 200 znaków", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                wordsSetDescription.setText(edit.getText().toString());
                                CustomWordsSetsRepository.setDescription(id, edit.getText().toString());
                                Toast.makeText(getContext(), "Opis zostal zmieniony", Toast.LENGTH_LONG).show();;
                            }
                        })
                        .setNegativeButton("anuluj", null)
                        .show();
                return false;
            }
        });
    }

}
