package pl.understandable.understandable_app.utils.buttons.phrases;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.View;

import java.util.Locale;

import pl.understandable.understandable_app.data.entities_data.CurrentWordData;
import pl.understandable.understandable_app.data.enums.words_set.WordsSetOptions;
import pl.understandable.understandable_app.database.entity.PhraseEntity;
import pl.understandable.understandable_app.database.entity.WordEntity;
import pl.understandable.understandable_app.utils.buttons.BaseButton;

/**
 * Created by Marcin Zielonka on 2018-03-18.
 */

public class PhrasesPronunciationButton extends BaseButton {

    private CurrentWordData<PhraseEntity> currentWordData;

    private TextToSpeech tts;

    public PhrasesPronunciationButton(Context context, CurrentWordData<PhraseEntity> currentWordData) {
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
                tts.speak(currentWordData.getCurrentWord().getEnglish(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    public void shutdownTts() {
        if(tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

}
