package ma.sg.hackathon.watsonapi.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by podisto on 26/03/2022.
 */
@Component
@Slf4j
public class SpeechToTextService {

    private final SpeechToTextProvider speechToText;

    public SpeechToTextService(SpeechToTextProvider speechToText) {
        this.speechToText = speechToText;
    }

    public String toText(byte[] data, String contentType) {
        try {
            String text = speechToText.toText(data, contentType);
            log.info("text {}", text);
            return text;
        } catch (IOException e) {
            throw new TranslationProcessingException(e);
        }
    }
}
