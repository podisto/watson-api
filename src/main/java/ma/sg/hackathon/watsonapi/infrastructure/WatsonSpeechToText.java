package ma.sg.hackathon.watsonapi.infrastructure;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.MimeType;
import ma.sg.hackathon.watsonapi.application.SpeechToTextProvider;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
public class WatsonSpeechToText implements SpeechToTextProvider {

    public static final String API_KEY = "";
    public static final String URL = "";
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String toText(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        String contentType = getContentType(file);
        log.info("<< ContentType {} >>", contentType);
        String credentials = getCredentials(API_KEY);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + credentials);
        headers.set("Content-Type", contentType);

        byte[] data = IOUtils.toByteArray(inputStream);
        HttpEntity<byte[]> request = new HttpEntity<>(data, headers);
        // ResponseEntity<SpeechToTextResponse> response = restTemplate.exchange(URL, HttpMethod.POST, request, SpeechToTextResponse.class);
        // log.info("Response {}", response.getBody());
        return null;
    }

    private String getContentType(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(filename);
        return MimeType.getContentType(extension);
    }

    private String getCredentials(String apiKey) {
        String plainCreds = "apiKey:" + apiKey;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        return base64Creds;
    }

}
