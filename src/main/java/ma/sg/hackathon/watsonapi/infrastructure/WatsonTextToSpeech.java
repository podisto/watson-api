package ma.sg.hackathon.watsonapi.infrastructure;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.TextToSpeechProvider;
import org.springframework.stereotype.Component;

/**
 * Created by podisto on 26/03/2022.
 */
@Component
@Slf4j
public class WatsonTextToSpeech implements TextToSpeechProvider {

    @Override
    public byte[] toSpeech(String text) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
