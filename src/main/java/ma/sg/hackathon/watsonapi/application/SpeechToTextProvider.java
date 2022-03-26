package ma.sg.hackathon.watsonapi.application;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by podisto on 26/03/2022.
 */
public interface SpeechToTextProvider {

    String toText(MultipartFile file) throws IOException;

}