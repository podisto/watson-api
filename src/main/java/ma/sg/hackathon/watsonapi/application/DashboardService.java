package ma.sg.hackathon.watsonapi.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.infrastructure.AccountRepository;
import ma.sg.hackathon.watsonapi.infrastructure.Base64Utils;
import ma.sg.hackathon.watsonapi.infrastructure.api.ApiResponse;
import ma.sg.hackathon.watsonapi.infrastructure.api.VoiceRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static ma.sg.hackathon.watsonapi.infrastructure.Constants.FALLBACK_RESPONSE;

/**
 * Created by podisto on 28/03/2022.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DashboardService {

    private static final String CONSULTATION_SOLDE = "CONSULTATION_SOLDE";
    private final TextToSpeechService textToSpeechService;
    private final SpeechToTextService speechToTextService;
    private final AccountRepository accountRepository;

    public ApiResponse processAnswer(VoiceRequest voice, String userId) {
        log.info("<< process answer for userId: {} >>", userId);
        byte[] bytes = Base64Utils.toBase64(voice.getData());
        String contentType = Base64Utils.getContentTypeFromTag(voice.getTag());
        String transcript = speechToTextService.toText(bytes, contentType);
        log.info("<< transcript: {} >>", transcript);
        String response = TransactionDictionary.getResponse(transcript);
        log.info("<< response: {} >>", response);
        String answer;
        byte[] data;
        if (isTransaction(response)) {
            answer = "not_available";
            String text = "Pour des raisons de sécurité, il n'est pas encore possible de faire ces opérations sur le Web, cependant je vous invite à télécharger l'Appli Société Générale Maroc où vous pourrez faire ces opérations.";
            data = textToSpeechService.toSpeech(text);
        } else if (isBalance(response)) {
            answer = "consultation_solde";
            List<Account> accounts = accountRepository.byUserId(userId);
            if (accounts.size() == 1) {
                String text = String.format("Votre solde est de %s, souhaitez-vous savoir autre chose ?", accounts.get(0).getBalance());
                data = textToSpeechService.toSpeech(text);
            } else {
                String text = String.format("Avec plaisir, vous avez %s comptes quel compte vous intéresse ?", accounts.size());
                data = textToSpeechService.toSpeech(text);
            }
        } else {
            answer = "unknown";
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
