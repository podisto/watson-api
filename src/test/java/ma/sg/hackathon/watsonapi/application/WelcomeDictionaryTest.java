package ma.sg.hackathon.watsonapi.application;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by podisto on 26/03/2022.
 */
class WelcomeDictionaryTest {

    @Test
    void should_return_OUI_if_response_is_oui() {
        boolean response = WelcomeDictionary.getResponse("OUI");
        assertThat(response).isTrue();
    }

    @Test
    void should_return_OUI_if_response_is_je_veux_bien() {
        boolean response = WelcomeDictionary.getResponse("ok je veux bien");
        assertThat(response).isTrue();
    }

    @Test
    void should_return_NO_if_response_is_is_dictionary() {
        boolean response = WelcomeDictionary.getResponse("non merci");
        assertThat(response).isFalse();
    }

}