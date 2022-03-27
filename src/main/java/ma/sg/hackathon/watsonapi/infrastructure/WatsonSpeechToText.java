package ma.sg.hackathon.watsonapi.infrastructure;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.MimeType;
import ma.sg.hackathon.watsonapi.application.SpeechToTextProvider;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import static org.springframework.http.HttpMethod.POST;

/**
 * Created by podisto on 26/03/2022.
 */
@Component
@Slf4j
public class WatsonSpeechToText implements SpeechToTextProvider {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WatsonProperties properties;

    @Override
    public String toText(byte[] data) {
        Tika tika = new Tika();
        String extension = tika.detect(data);
        log.info("<< file type {} >>", extension);
        String contentType = MimeType.getContentType(extension);
        String credentials = getCredentials(properties.getSpeechToText().getApiKey());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + credentials);
        headers.set("Content-Type", contentType);

        String url = UriComponentsBuilder.fromHttpUrl(properties.getSpeechToText().getUrl())
                .queryParam("model", "fr-FR_Multimedia")
                .toUriString();

        HttpEntity<byte[]> request = new HttpEntity<>(data, headers);
        ResponseEntity<SpeechToTextResponse> response = restTemplate.exchange(url, POST, request, SpeechToTextResponse.class);
        log.info("Response {}", response.getBody());
        return response.getBody().getResults().get(0).getAlternatives().get(0).getTranscript();
    }

    private String getContentType(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(filename);
        log.info("<< extension {} >>", extension);
        return Optional.ofNullable(extension).map(ext -> MimeType.getContentType(extension)).orElseGet(file::getContentType);
    }

    private String getCredentials(String apiKey) {
        String plainCreds = "apiKey:" + apiKey;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);
    }

}
