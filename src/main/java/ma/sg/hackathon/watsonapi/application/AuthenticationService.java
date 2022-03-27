package ma.sg.hackathon.watsonapi.application;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.infrastructure.Base64Utils;
import ma.sg.hackathon.watsonapi.infrastructure.api.CheckIdentityNumberResponse;
import ma.sg.hackathon.watsonapi.infrastructure.api.CredentialsRequest;
import ma.sg.hackathon.watsonapi.infrastructure.api.VoiceRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    public CheckIdentityNumberResponse checkIdentityNumber(VoiceRequest voice) {
        log.info("<< check identity number >>>>");
        byte[] data = Base64Utils.toBase64(voice.getData());
        String contentType = Base64Utils.getContentTypeFromTag(voice.getTag());
        log.info("<< content type {} >>", contentType);
        String transcript = speechToTextService.toText(data, contentType);
        log.info("<< transcript {} >>", transcript);
        String id = Arrays.stream(transcript.split(" "))
                .map(AuthenticationDictionary::getDigitNumber)
                .collect(Collectors.joining(" "))
                .trim()
                .replaceAll("\\s+", "");
        log.info("<< id {} >>", id);
        String text = "Meziene, maintenant dites moi votre mot de passe lettre par lettre en précisant majuscules et minuscules et en vous assurant de l'absence d'oreilles indiscrètes";
        byte[] bytes = textToSpeechService.toSpeech(text);
        return new CheckIdentityNumberResponse(bytes, id);
    }

    public byte[] confirmIdentity(VoiceRequest voice) {
        byte[] data = Base64Utils.toBase64(voice.getData());
        String contentType = Base64Utils.getContentTypeFromTag(voice.getTag());
        log.info("<< content type {} >>", contentType);
        String transcript = speechToTextService.toText(data, contentType);
        log.info("<< transcript {} >>", transcript);
        String response = AuthenticationDictionary.getDigitNumber(transcript);
        if ("OUI".equalsIgnoreCase(response)) {
            String text = "Meziene, maintenant dites moi votre mot de passe lettre par lettre en précisant majuscules et minuscules et en vous assurant de l'absence d'oreilles indiscrètes";
            return textToSpeechService.toSpeech(text);
        } else {
            String text = "Prononcez à nouveau votre identifiant chiffre par chiffre";
            return textToSpeechService.toSpeech(text);
        }
    }

    public void login(CredentialsRequest credentials) {
        log.info("<< login >>");
        byte[] data = Base64Utils.toBase64(credentials.getData());
        String contentType = Base64Utils.getContentTypeFromTag(credentials.getTag());
        log.info("<< content type {} >>", contentType);
        String transcript = speechToTextService.toText(data, contentType);
        log.info("<< transcript {} >>", transcript);
    }

}
