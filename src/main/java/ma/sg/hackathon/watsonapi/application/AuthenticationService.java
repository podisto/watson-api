package ma.sg.hackathon.watsonapi.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.infrastructure.Base64Utils;
import ma.sg.hackathon.watsonapi.infrastructure.api.ApiResponse;
import ma.sg.hackathon.watsonapi.infrastructure.api.CheckIdentityNumberResponse;
import ma.sg.hackathon.watsonapi.infrastructure.api.VoiceRequest;
import org.springframework.stereotype.Service;

import static ma.sg.hackathon.watsonapi.infrastructure.Contants.NO;
import static ma.sg.hackathon.watsonapi.infrastructure.Contants.YES;

/**
 * Created by podisto on 27/03/2022.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

    public static final String WELCOME = "Marhaba, vous êtes sur votre espace connecté Société Générale Maroc. Que souhaitez-vous faire ?";
    private static final String ERROR_LOGIN = "Identifiant ou mot de passe incorrect.";

    private final SpeechToTextService speechToTextService;
    private final TextToSpeechService textToSpeechService;
    private final AuthenticationGateway authenticationGateway;

    public CheckIdentityNumberResponse checkIdentityNumber(VoiceRequest voice) {
        log.info("<< check identity number >>>>");
        byte[] data = getBytes(voice.getData());
        String contentType = Base64Utils.getContentTypeFromTag(voice.getTag());
        log.info("<< content type {} >>", contentType);
        String transcript = speechToTextService.toText(data, contentType);
        log.info("<< transcript: {} >>", transcript);
        String id = UserIdDictionary.getIdentityNumber(transcript);
        log.info("<< id {} >>", id);
        String text = "Meziene, maintenant dites moi votre mot de passe lettre par lettre en précisant majuscules et minuscules et en vous assurant de l'absence d'oreilles indiscrètes";
        byte[] bytes = textToSpeechService.toSpeech(text);
        return new CheckIdentityNumberResponse(bytes, id);
    }

    public ApiResponse login(String userId, VoiceRequest voice) {
        log.info("<< login user with Id: {}>>", userId);
        String contentType = Base64Utils.getContentTypeFromTag(voice.getTag());
        log.info("<< content type: {} >>", contentType);
        String transcript = speechToTextService.toText(getBytes(voice.getData()), contentType);
        log.info("<< transcript: {} >>", transcript);
        String password = PasswordDictionary.sanitizePassword(transcript);
        log.info("<< password: {} >>", password);
        boolean isAuthenticated = authenticationGateway.authenticate(userId, password);
        String message = isAuthenticated ? WELCOME : ERROR_LOGIN;
        byte[] bytes = textToSpeechService.toSpeech(message);
        String answer = isAuthenticated ? YES : NO;
        return new ApiResponse(bytes, answer);
    }

    private byte[] getBytes(String encoded) {
        return Base64Utils.toBase64(encoded);
    }

}
