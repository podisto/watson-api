package ma.sg.hackathon.watsonapi.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.infrastructure.Base64Utils;
import ma.sg.hackathon.watsonapi.infrastructure.Constants;
import ma.sg.hackathon.watsonapi.infrastructure.api.ApiResponse;
import ma.sg.hackathon.watsonapi.infrastructure.api.VoiceRequest;
import org.springframework.stereotype.Service;

import static ma.sg.hackathon.watsonapi.infrastructure.Constants.FALLBACK_RESPONSE;

/**
 * Created by podisto on 28/03/2022.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DashboardService {

    private static final String NO = Constants.NO;
    public static final String CONSULTATION_SOLDE = "CONSULTATION_SOLDE";
    private static final String UNKNOWN = "UNKNOWN";

    private final TextToSpeechService textToSpeechService;
    private final SpeechToTextService speechToTextService;

    public ApiResponse processAnswer(VoiceRequest voice) {
        log.info("<< process answer >>");
        byte[] bytes = Base64Utils.toBase64(voice.getData());
        String contentType = Base64Utils.getContentTypeFromTag(voice.getTag());
        String transcript = speechToTextService.toText(bytes, contentType);
        log.info("<< transcript: {} >>", transcript);
        String response = TransactionDictionary.getResponse(transcript);
        log.info("<< response: {} >>", response);
        String answer;
        byte[] data = null;
        if (isTransaction(response)) {
            answer = "NOT_AVAILABLE";
            String text = "Pour des raisons de sécurité, il n'est pas encore possible de faire ces opérations sur le Web, cependant je vous invite à télécharger l'Appli Société Générale Maroc où vous pourrez faire ces opérations.";
            data = textToSpeechService.toSpeech(text);
        } else if (isBalance(response)) {
            answer = CONSULTATION_SOLDE;
            // vérifier si le client a un seul compte et retourner le solde sinon demander de choisir le compte
        } else {
            answer = UNKNOWN;
            data = textToSpeechService.toSpeech(FALLBACK_RESPONSE);
        }
        return new ApiResponse(data, answer);
    }

    private boolean isBalance(String response) {
        return CONSULTATION_SOLDE.equalsIgnoreCase(response);
    }

    private boolean isTransaction(String response) {
        return "TRANSACTION".equalsIgnoreCase(response);
    }
}
