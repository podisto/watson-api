package ma.sg.hackathon.watsonapi.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Created by podisto on 27/03/2022.
 */
public class AuthenticationDictionary {

    private static final Map<String, List<String>> responses = new HashMap<>();

    private static final List<String> YES_KEYWORDS = asList("oui", "effectivement", "wé", "ouais", "c'est ça", "c'est bien ça", "oui aïcha", "effectivement aïcha", "c'est ça aïcha");
    private static final Map<String, Integer> wordToNumber = new HashMap<>();

    static {
        responses.put("OUI", YES_KEYWORDS);
        wordToNumber.put("0", 1);
        wordToNumber.put("1", 1);
        wordToNumber.put("2", 1);
        wordToNumber.put("3", 1);
        wordToNumber.put("4", 1);
        wordToNumber.put("5", 1);
        wordToNumber.put("6", 1);
        wordToNumber.put("7", 1);
        wordToNumber.put("8", 1);
        wordToNumber.put("un", 1);
    }

    public static String getResponse(String text) {
        return responses.entrySet()
                .stream()
                .filter(data -> data.getValue().contains(text.trim().toLowerCase()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("NON");
    }
}
