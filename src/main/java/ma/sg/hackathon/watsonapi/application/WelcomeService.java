package ma.sg.hackathon.watsonapi.application;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.infrastructure.api.GreetingAnswerResponse;
import org.springframework.stereotype.Service;

/**
 * Created by podisto on 26/03/2022.
 */
@Service
@Slf4j
public class WelcomeService {

    public static final String YES_TEXT = "Prononcez votre identifiant chiffre par chiffre.";
    public static final String NO_TEXT = "Parfait, si vous avez besoin de moi, dites Aïcha.";
    private static final String YES = "yes";
    private static final String NO = "no";
    private final SpeechToTextService speechToTextService;
    private final TextToSpeechService textToSpeechService;

    public WelcomeService(SpeechToTextService speechToTextService, TextToSpeechService textToSpeechService) {
        this.speechToTextService = speechToTextService;
        this.textToSpeechService = textToSpeechService;
    }

    public GreetingAnswerResponse toText(byte[] data, String contentType) {
        String transcript = speechToTextService.toText(data, contentType);
        log.info("<< transcript {} >>", transcript);
        String response = WelcomeDictionary.getResponse(transcript);
        log.info("<< reponse {} >>", response);
        if ("OUI".equalsIgnoreCase(response)) {
            return new GreetingAnswerResponse(textToSpeechService.toSpeech(YES_TEXT), YES);
        }
        return new GreetingAnswerResponse(textToSpeechService.toSpeech(NO_TEXT), NO);
    }

    public byte[] initLogin() {
        String text = "Aïcha Prononcez votre identifiant chiffre par chiffre";
        return textToSpeechService.toSpeech(text);
    }

    public byte[] sendGreeting() {
        String text = "Salam aleykoum, ana smiti Aïcha, votre Assistant Société Générale Maroc. Je parle français pour le moment et suis en train d'apprendre l'Arabe. Souhaitez-vous que je vous accompagne pour accéder à vos comptes ?";
        return textToSpeechService.toSpeech(text);
    }
}
