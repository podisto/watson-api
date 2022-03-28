package ma.sg.hackathon.watsonapi.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.infrastructure.Base64Utils;
import ma.sg.hackathon.watsonapi.infrastructure.Constants;
import ma.sg.hackathon.watsonapi.infrastructure.api.ApiResponse;
import ma.sg.hackathon.watsonapi.infrastructure.api.VoiceRequest;
import org.springframework.stereotype.Service;

/**
 * Created by podisto on 28/03/2022.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DashboardService {

    public static final String NOT_AVAILABLE = "Pour des raisons de sécurité, il n'est pas encore possible de faire ces opérations sur le Web, cependant je vous invite à télécharger l'Appli Société Générale Maroc où vous pourrez faire ces opérations.";
    private static final String YES = Constants.YES;
    private static final String NO = Constants.NO;

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
        if ("TRANSACTION".equalsIgnoreCase(response)) {
            answer = NOT_AVAILABLE;
        } else if ("CONSULTATION_SOLDE".equalsIgnoreCase(response)) {
            answer = YES;
        } else {
            answer = NO;
        }
        // byte[] toSpeech = textToSpeechService.toSpeech(TRANSACTION_NOT_AVAILABLE);
        return new ApiResponse(null, answer);
    }
}
