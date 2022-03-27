package ma.sg.hackathon.watsonapi.application;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.infrastructure.api.WelcomeResponse;
import org.springframework.stereotype.Service;

/**
 * Created by podisto on 26/03/2022.
 */
@Service
@Slf4j
public class WelcomeService {

    private final SpeechToTextService speechToTextService;

    public WelcomeService(SpeechToTextService speechToTextService) {
        this.speechToTextService = speechToTextService;
    }

    public WelcomeResponse toText(byte[] data, String contentType) {
        String text = speechToTextService.toText(data, contentType.split(":")[1]);
        boolean response = WelcomeDictionary.getResponse(text);
        return new WelcomeResponse(response);
    }
}
