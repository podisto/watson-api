package ma.sg.hackathon.watsonapi.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.infrastructure.Base64Utils;
import ma.sg.hackathon.watsonapi.infrastructure.api.ApiResponse;
import ma.sg.hackathon.watsonapi.infrastructure.api.CheckIdentityNumberResponse;
import ma.sg.hackathon.watsonapi.infrastructure.api.VoiceRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

import static ma.sg.hackathon.watsonapi.infrastructure.Contants.NO;
import static ma.sg.hackathon.watsonapi.infrastructure.Contants.YES;

/**
 * Created by podisto on 27/03/2022.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

    private static final String CONFIRMATION = "Si j'ai bien entendu votre identifiant est le %s, c'est bien cela ?";
    public static final String WELCOME = "Marhaba, vous êtes sur votre espace connecté Société Générale Maroc. Que souhaitez-vous faire ?";
    private static final String ERROR_LOGIN = "Identifiant ou mot de passe incorrect.";

    private final SpeechToTextService speechToTextService;
    private final TextToSpeechService textToSpeechService;
    private final AuthenticationGateway authenticationGateway;

    public CheckIdentityNumberResponse checkIdentityNumber(VoiceRequest voice) {
        log.info("<< check identity number >>>>");
        byte[] data = Base64Utils.toBase64(voice.getData());
        String contentType = Base64Utils.getContentTypeFromTag(voice.getTag());
        log.info("<< content type {} >>", contentType);
        String transcript = speechToTextService.toText(data, contentType);
        log.info("<< transcript: {} >>", transcript);
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
        log.info("<< transcript: {} >>", transcript);
        String response = AuthenticationDictionary.getDigitNumber(transcript);
        if ("OUI".equalsIgnoreCase(response)) {
            String text = "Meziene, maintenant dites moi votre mot de passe lettre par lettre en précisant majuscules et minuscules et en vous assurant de l'absence d'oreilles indiscrètes";
            return textToSpeechService.toSpeech(text);
        } else {
            String text = "Prononcez à nouveau votre identifiant chiffre par chiffre";
            return textToSpeechService.toSpeech(text);
        }
    }

    public ApiResponse login(String userId, VoiceRequest voice) {
        log.info("<< login >>");
        byte[] data = Base64Utils.toBase64(voice.getData());
        String contentType = Base64Utils.getContentTypeFromTag(voice.getTag());
        log.info("<< content type {} >>", contentType);
        String transcript = speechToTextService.toText(data, contentType);
        log.info("<< transcript: {} >>", transcript);
        // TODO login with userId and Password
        String password = Arrays.stream(transcript.split(" "))
                .map(AuthenticationDictionary::getDigitNumber)
                .collect(Collectors.joining(" "))
                .trim()
                .replaceAll("\\s+", "");
        log.info("<< password {} >>", password);
        boolean isAuthenticated = authenticationGateway.authenticate(userId, password);
        String message = isAuthenticated ? WELCOME : ERROR_LOGIN;
        byte[] bytes = textToSpeechService.toSpeech(message);
        String answer = isAuthenticated ? YES : NO;
        return new ApiResponse(bytes, answer);
    }

}
