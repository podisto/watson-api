package ma.sg.hackathon.watsonapi.application;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Created by podisto on 27/03/2022.
 */
@Slf4j
public class PasswordDictionary {

    private static final Map<String, String> wordToNumber = new HashMap<>();
    public static final String REGEX_SPACE = " ";
    private static final List<String> lowerCases = asList("minuscule", "menuscule", "minescule");
    // private static final List<String> alphabet = asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    private static Map<String, String> mapAlphabet = new HashMap<>();

    static {
        wordToNumber.put("zéro", "0");
        wordToNumber.put("zalant", "0");
        wordToNumber.put("ziro", "0");
        wordToNumber.put("zero", "0");
        wordToNumber.put("un", "1");
        wordToNumber.put("non", "1");
        wordToNumber.put("deux", "2");
        wordToNumber.put("deu", "2");
        wordToNumber.put("trois", "3");
        wordToNumber.put("troi", "3");
        wordToNumber.put("toi", "3");
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

        mapAlphabet.put("ah", "A");
        mapAlphabet.put("est-ce", "S");
    }

    public static String sanitizePassword(String transcriptedPassword) {
        String correct = setCorrectAlphabetLetter(transcriptedPassword);
        String transformWordToMajusculeAndMinuscule = transformWordToMajusculeAndMinuscule(correct);
        return replaceWordNumberWithDigitNumber(transformWordToMajusculeAndMinuscule);
    }

    // non deux toi quatre cinq six est-ce majuscule ah minuscule f minescule
    public static String getPassword(String transcript) {
        return Arrays.stream(transcript.split(REGEX_SPACE))
                .map(PasswordDictionary::getDigitNumber)
                .collect(Collectors.joining(REGEX_SPACE))
                .trim()
                .replaceAll("\\s+", "");
    }

    private static String transformWordToMajusculeAndMinuscule(String transcript) {
        log.info("<< password voice {} >>", transcript);
        String[] words = transcript.split(REGEX_SPACE);
        for (int i = 0; i < words.length; i++) {
            if ("majuscule".equals(words[i])) {
                words[i - 1] = words[i - 1].toUpperCase();
                words = ArrayUtils.remove(words, i);
            }
            if (lowerCases.contains(words[i])) {
                words[i - 1] = words[i - 1].toLowerCase();
                words = ArrayUtils.remove(words, i);
            }
        }
        return String.join(REGEX_SPACE, words);
    }

    private static String replaceWordNumberWithDigitNumber(String word) {
        log.info("<< word {} >>", word);
        String[] array = word.split(REGEX_SPACE);
        for (int i= 0; i < array.length; i++) {
            try {
                int intValue = Integer.parseInt(wordToNumber.get(array[i]));
                array[i] = String.valueOf(intValue);
            } catch (NumberFormatException ex) {
                log.info("<< cannot parse {} >>", array[i]);
            }
        }
        return String.join("", array);
    }


    private static String getDigitNumber(String text) {
        return wordToNumber.entrySet()
                .stream()
                .filter(data -> data.getKey().contains(text.trim().toLowerCase()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new ChoiceNotFoundException("Unable to process your request " +text));
    }

    private static String setCorrectAlphabetLetter(String text) {
        String[] array = text.split(REGEX_SPACE);
        for (int i= 0; i<array.length; i++) {
            for (Map.Entry<String, String> entry : mapAlphabet.entrySet())
                if (entry.getKey().equalsIgnoreCase(array[i])) {
                    String found = mapAlphabet.get(array[i]);
                    array[i] = found;
                }
        }
        return String.join(REGEX_SPACE, array);

    }
}
