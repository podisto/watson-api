package ma.sg.hackathon.watsonapi.application;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.infrastructure.WatsonProperties;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        HttpEntity<String> request = new HttpEntity<>(text, headers);
        ResponseEntity<byte[]> response = restTemplate.exchange(properties.getTextToSpeech().getUrl(), HttpMethod.POST, request, byte[].class);
        try {
            Files.write(Paths.get("data.mp3"), response.getBody());
        } catch (IOException e) {
            log.info("Error occurs {}", e.getMessage());
        }
        return response.getBody();
    }

    private String getCredentials(String apiKey) {
        String plainCreds = "apiKey:" + apiKey;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);
    }
}
