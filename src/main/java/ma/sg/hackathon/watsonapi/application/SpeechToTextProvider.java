package ma.sg.hackathon.watsonapi.application;

import java.io.IOException;

/**
 * Created by podisto on 26/03/2022.
 */
public interface SpeechToTextProvider {

    String toText(byte[] data, String contentType) throws IOException;

}
