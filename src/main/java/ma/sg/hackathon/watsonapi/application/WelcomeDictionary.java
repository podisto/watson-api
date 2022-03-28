package ma.sg.hackathon.watsonapi.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Created by podisto on 26/03/2022.
 */
public class WelcomeDictionary {

    private WelcomeDictionary() {
        // prevent creating objects
    }

    private static final Map<String, List<String>> responses = new HashMap<>();

    private static final List<String> YES_KEYWORDS = asList("oui", "j'ai besoin d'Aïcha", "je veux bien", "oui avec plaisir", "s'il vous plaît", "c'est parti",
            "j'en ai besoin", "j'ai besoin", "je veux", "Aicha", "ok", "ok je veux bien", "s'il vous plait", "s v p", "c'est parti ", "allons y",
            "où est chat", "s'il te plait", "s t p", "oui un chat", "oui achat", "où est un chat");
    private static final List<String> NO_KEYWORDS = asList("non", "je veux pas", "veux pas", "non merci", "merci", "pas besoin", "j'en ai pas besoin", "merci", "non j'aime des brouilles tout ça",
            "pas la peine");

    static {
        responses.put("OUI", YES_KEYWORDS);
        responses.put("NON", NO_KEYWORDS);
    }

    public static String getResponse(String text) {
        return responses.entrySet()
                .stream()
                .filter(data -> data.getValue().contains(text.trim().toLowerCase()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new ChoiceNotFoundException("Unable to process your request " + text));

    }
}
