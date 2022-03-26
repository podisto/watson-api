package ma.sg.hackathon.watsonapi;

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

    private final AutomaticSpeechRecognition automaticSpeechRecognition;

    public SpeechToTextService(AutomaticSpeechRecognition automaticSpeechRecognition) {
        this.automaticSpeechRecognition = automaticSpeechRecognition;
    }

    public String toText(MultipartFile file) throws IOException {
        String text = automaticSpeechRecognition.toText(file);
        log.info("text {}", text);
        return text;
    }
}
