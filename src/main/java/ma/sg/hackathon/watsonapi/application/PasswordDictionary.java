package ma.sg.hackathon.watsonapi.application;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by podisto on 27/03/2022.
 */
public class PasswordDictionary {

    private static final Map<String, String> wordToNumber = new HashMap<>();

    static {
        wordToNumber.put("zéro", "0");
        wordToNumber.put("zalant", "0");
        wordToNumber.put("ziro", "0");
        wordToNumber.put("zero", "0");
        wordToNumber.put("un", "1");
        wordToNumber.put("deux", "2");
        wordToNumber.put("deu", "2");
        wordToNumber.put("trois", "3");
        wordToNumber.put("troi", "3");
        wordToNumber.put("quatre", "4");
        wordToNumber.put("quate", "4");
        wordToNumber.put("quaat", "4");
        wordToNumber.put("cinq", "5");
        wordToNumber.put("six", "6");
        wordToNumber.put("sisse", "6");
        wordToNumber.put("sis", "6");
        wordToNumber.put("sept", "7");
        wordToNumber.put("sét", "7");
        wordToNumber.put("set", "7");
        wordToNumber.put("sette", "7");
        wordToNumber.put("huit", "8");
        wordToNumber.put("oui", "8");
        wordToNumber.put("ouite", "8");
        wordToNumber.put("neuf", "9");
    }

    public static String getPassword(String transcript) {
        return Arrays.stream(transcript.split(" "))
                .map(PasswordDictionary::getDigitNumber)
                .collect(Collectors.joining(" "))
                .trim()
                .replaceAll("\\s+", "");
    }

    private static String getDigitNumber(String text) {
        return wordToNumber.entrySet()
                .stream()
                .filter(data -> data.getKey().contains(text.trim().toLowerCase()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new ChoiceNotFoundException("Unable to process your request " +text));
    }
}
