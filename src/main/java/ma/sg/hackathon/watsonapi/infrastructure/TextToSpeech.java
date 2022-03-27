package ma.sg.hackathon.watsonapi.infrastructure;

import lombok.Data;

/**
 * Created by podisto on 27/03/2022.
 */
@Data
public class TextToSpeech {
    private String text;

    public TextToSpeech(String text) {
        this.text = text;
    }
}
