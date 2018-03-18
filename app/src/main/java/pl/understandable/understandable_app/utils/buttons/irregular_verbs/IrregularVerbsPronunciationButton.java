package pl.understandable.understandable_app.utils.buttons.irregular_verbs;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.view.View;

import java.util.Locale;

import pl.understandable.understandable_app.data.entities_data.CurrentWordData;
import pl.understandable.understandable_app.data.enums.words_set.WordsSetOptions;
import pl.understandable.understandable_app.database.entity.IrregularVerbEntity;
import pl.understandable.understandable_app.utils.buttons.BaseButton;

import static pl.understandable.understandable_app.database.entity.enums.IrregularVerbEnum.*;

/**
 * Created by Marcin Zielonka on 2018-03-18.
 */

public class IrregularVerbsPronunciationButton extends BaseButton {

    private CurrentWordData<IrregularVerbEntity> currentWordData;

    private TextToSpeech tts;

    public IrregularVerbsPronunciationButton(Context context, CurrentWordData<IrregularVerbEntity> currentWordData) {
        super(context, WordsSetOptions.PRONUNCIATION, false);
        this.currentWordData = currentWordData;

        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.UK);
                }
            }
        });

        prepare();
    }

    @Override
    protected void setChoiceState() {
        image.setAlpha(ITEM_CHOSEN);
        this.setChecked(true);
        image.setImageResource(WordsSetOptions.PRONUNCIATION.getResId());
    }

    @Override
    protected void setOnClickListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrregularVerbEntity e = currentWordData.getCurrentWord();
                tts.speak(e.getEnglish(INFINITVE), TextToSpeech.QUEUE_ADD, null);
                playSilence();
                tts.speak(e.getEnglish(SIMPLE_PAST), TextToSpeech.QUEUE_ADD, null);
                playSilence();
                tts.speak(e.getEnglish(PAST_PARTICIPLE), TextToSpeech.QUEUE_ADD, null);
            }
        });
    }

    private void playSilence() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.playSilentUtterance(450, TextToSpeech.QUEUE_ADD, null);
        } else {
            tts.playSilence(450, TextToSpeech.QUEUE_ADD, null);
        }
    }

    public void shutdownTts() {
        if(tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

}
