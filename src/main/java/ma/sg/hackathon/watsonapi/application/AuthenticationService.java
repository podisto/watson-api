package ma.sg.hackathon.watsonapi.application;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.infrastructure.api.CredentialsRequest;
import ma.sg.hackathon.watsonapi.infrastructure.api.VoiceRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * Created by podisto on 27/03/2022.
 */
@Service
@Slf4j
public class AuthenticationService {

    private static final String CONFIRMATION = "Si j'ai bien entendu votre identifiant est le %s, c'est bien cela ?";

    private final SpeechToTextService speechToTextService;
    private final TextToSpeechService textToSpeechService;

    public AuthenticationService(SpeechToTextService speechToTextService, TextToSpeechService textToSpeechService) {
        this.speechToTextService = speechToTextService;
        this.textToSpeechService = textToSpeechService;
    }

    public void checkIdentityNumber(VoiceRequest voice) {
        log.info("<< check identity number >>>>");
        String base64 = voice.getData();
        byte[] data = Base64.getDecoder().decode(base64);
        String[] split = voice.getTag().split(":");
        String contentType = split.length > 1 ? split[1] : split[0];
        log.info("<< content type {} >>", contentType);
        String transcript = speechToTextService.toText(data, contentType);
        String confirmation = String.format(CONFIRMATION, transcript);
        log.info("<< confirmation: {} >>", confirmation);
        byte[] content = textToSpeechService.toSpeech(confirmation);
        try {
            Files.write(Paths.get("confirmation.mp3"), content);
        } catch (IOException e) {
            log.info("Error occurs {}", e.getMessage());
        }
    }

    public void login(CredentialsRequest credentials) {
        log.info("<< login >>");
        String base64 = credentials.getData();
        byte[] data = Base64.getDecoder().decode(base64);
        String[] split = credentials.getTag().split(":");
        String contentType = split.length > 1 ? split[1] : split[0];
        log.info("<< content type {} >>", contentType);
        String transcript = speechToTextService.toText(data, contentType);
    }
}
