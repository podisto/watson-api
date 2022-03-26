package ma.sg.hackathon.watsonapi.application;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by podisto on 26/03/2022.
 */
public interface TextToSpeechProvider {

    MultipartFile toSpeech(String text);

}
