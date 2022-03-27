package ma.sg.hackathon.watsonapi.application;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.infrastructure.TextToSpeech;
import ma.sg.hackathon.watsonapi.infrastructure.WatsonProperties;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by podisto on 26/03/2022.
 */
@Component
@Slf4j
public class TextToSpeechService implements TextToSpeechProvider {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WatsonProperties properties;

    @Override
    public byte[] toSpeech(String text) {
        log.info("<< Data to transcribe to audio {} >>", text);
        String credentials = getCredentials(properties.getTextToSpeech().getApiKey());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + credentials);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "audio/mp3");
        HttpEntity<TextToSpeech> request = new HttpEntity<>(new TextToSpeech(text), headers);
        ResponseEntity<byte[]> response = restTemplate.exchange(properties.getTextToSpeech().getUrl(), HttpMethod.POST, request, byte[].class);
        return response.getBody();
    }

    private String getCredentials(String apiKey) {
        log.info("api key {}", apiKey);
        String plainCreds = "apiKey:" + apiKey;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);
    }
}
