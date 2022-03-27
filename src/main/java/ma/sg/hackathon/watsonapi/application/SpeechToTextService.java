package ma.sg.hackathon.watsonapi.application;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.infrastructure.SpeechToText;
import org.springframework.stereotype.Component;

/**
 * Created by podisto on 26/03/2022.
 */
@Component
@Slf4j
public class SpeechToTextService {

    private final SpeechToTextProvider speechToTextProvider;

    public SpeechToTextService(SpeechToTextProvider speechToText) {
        this.speechToTextProvider = speechToText;
    }

    public String toText(byte[] data, String contentType) {
        SpeechToText speechToText = new SpeechToText(data, contentType.split(":")[1]);
        String text = speechToTextProvider.toText(speechToText);
        log.info("text {}", text);
        return text;
    }
}
