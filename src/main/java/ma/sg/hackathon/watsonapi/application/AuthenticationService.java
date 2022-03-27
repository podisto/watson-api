package ma.sg.hackathon.watsonapi.application;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.infrastructure.api.VoiceRequest;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * Created by podisto on 27/03/2022.
 */
@Service
@Slf4j
public class AuthenticationService {

    private static final String CONFIRMATION = "Si j'ai bien entendu votre identifiant est le %s yék ?";

    private final SpeechToTextService speechToTextService;

    public AuthenticationService(SpeechToTextService speechToTextService) {
        this.speechToTextService = speechToTextService;
    }

    public void checkIdentityNumber(VoiceRequest voice) {
        log.info("<< check identity number >>>>");
        String base64 = voice.getData();
        byte[] data = Base64.getDecoder().decode(base64);
        String[] split = voice.getTag().split(":");
        String contentType = split.length > 1 ? split[1] : split[0];
        log.info("<< content type {} >>", contentType);
        String transcript = speechToTextService.toText(data, contentType);
        String confirmation = "Si j'ai bien entendu votre identifiant est le " + transcript + ", yék ?";
        log.info("<< transcript: {} >>", transcript);
    }
}
