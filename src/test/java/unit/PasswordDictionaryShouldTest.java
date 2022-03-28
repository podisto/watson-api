package unit;

import ma.sg.hackathon.watsonapi.application.PasswordDictionary;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by podisto on 28/03/2022.
 * Set test methods to private
 */
class PasswordDictionaryShouldTest {

    // This is the public method
    @Test
    void sanitize_password() {
        String text = "zéro sept huit neuf dollar aérobase eux commerciales et toi pourcentage";

        String result = PasswordDictionary.sanitizePassword(text);

        assertThat(result).isEqualTo("0789$@&*%");
    }

    // un deux toi quatre cinq six est-ce majuscule ah minuscule f minescule
    // non deux toi quatre cinq six est-ce majuscule a minuscule f minescule
    @Test
    void transform_word_to_majuscule_or_minuscule() {
        String passwordVoice = "non deux toi quatre cinq six s majuscule a minuscule f minescule";

        String password = PasswordDictionary.transformWordToMajusculeAndMinuscule(passwordVoice);

        assertThat(password).isEqualTo("non deux toi quatre cinq six S a f");
    }

    @Test
    void transform_word_letter_to_digit_number() {
        String passwordVoice = "non deux toi quatre cinq six S a f";

        String password = PasswordDictionary.replaceWordNumberWithDigitNumber(passwordVoice);

        assertThat(password).isEqualTo("123456Saf");
    }

    // non deux toi quatre cinq six est-ce majuscule ah minuscule f minescule
    @Test
    void set_correct_alphabet_letter() {
        String text = "non deux toi quatre cinq six est-ce majuscule ah minuscule f minescule";

        String result = PasswordDictionary.setCorrectAlphabetLetter(text);

        assertThat(result).isEqualTo("non deux toi quatre cinq six S majuscule A minuscule f minescule");
    }

    // zéro sept huit neuf donne moi(dollar) aérobase eux commerciales étoile pour son tâche
    @Test
    void handle_special_characters() {
        String password = "zéro sept huit neuf donne moi aérobase eux commerciales étoile pour son tâche";

        String result = PasswordDictionary.handleSpecialCharacters(password);

        assertThat(result).isEqualTo("zéro sept huit neuf $ @ & * %");
    }
}