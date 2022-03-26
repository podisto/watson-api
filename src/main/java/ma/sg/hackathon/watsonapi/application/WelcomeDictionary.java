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

    private static final List<String> YES_KEYWORDS = asList("oui", "j'ai besoin d'Aïcha", "je veux bien", "oui avec plaisir", "s'il vous plaît", "c'est parti", "j'en ai besoin", "j'ai besoin", "je veux", "Aicha", "ok", "ok je veux bien");
    private static final List<String> NO_KEYWORDS = asList("non", "je veux pas", "veux pas", "non merci", "merci", "pas besoin", "j'en ai pas besoin", "merci");

    static {
        responses.put("OUI", YES_KEYWORDS);
        responses.put("NON", NO_KEYWORDS);
    }

    public static boolean getResponse(String text) {
        String response = responses.entrySet()
                .stream()
                .filter(data -> data.getValue().contains(text.trim().toLowerCase()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new ChoiceNotFoundException("Unable to process your request " + text));
        return "OUI".equals(response);

    }
}