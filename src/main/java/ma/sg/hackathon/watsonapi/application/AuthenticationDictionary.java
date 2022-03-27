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

    static {
        responses.put("OUI", YES_KEYWORDS);
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
