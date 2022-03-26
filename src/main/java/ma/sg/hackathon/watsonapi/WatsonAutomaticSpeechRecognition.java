package ma.sg.hackathon.watsonapi;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by podisto on 26/03/2022.
 */
@Component
@Slf4j
public class WatsonAutomaticSpeechRecognition implements AutomaticSpeechRecognition {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String toText(MultipartFile file) throws IOException {
        String url = "https://api.eu-de.speech-to-text.watson.cloud.ibm.com/instances/a767c987-f49c-4717-98f3-df5a86beef6c/v1/recognize";
        String plainCreds = "apiKey:g3zIYUolm2Uz7uM7AubQfLq1CHW1af1E0tScC_YYyLCW";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.set("Content-Type", "audio/flac");

        InputStream inputStream = new ClassPathResource("audio-file2.flac").getInputStream();
        byte[] data = IOUtils.toByteArray(inputStream);
        HttpEntity<byte[]> request = new HttpEntity<>(data, headers);
        ResponseEntity<SpeechToTextResponse> response = restTemplate.exchange(url, HttpMethod.POST, request, SpeechToTextResponse.class);
        log.info("Response {}", response.getBody());
        return null;
    }

    @Override
    public MultipartFile toSpeech(String text) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
