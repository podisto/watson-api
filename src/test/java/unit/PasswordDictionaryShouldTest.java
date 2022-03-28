package unit;

import ma.sg.hackathon.watsonapi.application.PasswordDictionary;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by podisto on 28/03/2022.
 * Set test methods to private
 */
class PasswordDictionaryShouldTest {

    // un deux toi quatre cinq six est-ce majuscule ah minuscule f minescule
    // non deux toi quatre cinq six est-ce majuscule a minuscule f minescule
    /*@Test
    void transform_word_to_majuscule_or_minuscule() {
        String passwordVoice = "non deux toi quatre cinq six s majuscule a minuscule f minescule";

        String password = PasswordDictionary.transformWordToMajusculeAndMinuscule(passwordVoice);

        assertThat(password).isEqualTo("non deux toi quatre cinq six S a f");
    }*/

    /*@Test
    void transform_word_letter_to_digit_number() {
        String passwordVoice = "non deux toi quatre cinq six S a f";

        String password = PasswordDictionary.replaceWordNumberWithDigitNumber(passwordVoice);

        assertThat(password).isEqualTo("123456Saf");
    }*/

    // non deux toi quatre cinq six est-ce majuscule ah minuscule f minescule
    /*@Test
    void set_correct_alphabet_letter() {
        String text = "non deux toi quatre cinq six est-ce majuscule ah minuscule f minescule";

        String result = PasswordDictionary.setCorrectAlphabetLetter(text);

        assertThat(result).isEqualTo("non deux toi quatre cinq six S majuscule A minuscule f minescule");
    }*/
}