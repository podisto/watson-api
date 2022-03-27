package ma.sg.hackathon.watsonapi.infrastructure;

import lombok.Data;

/**
 * Created by podisto on 27/03/2022.
 */
@Data
public class SpeechToText {
    private byte[] data;
    private String contentType;

    public SpeechToText(byte[] data, String contentType) {
        this.data = data;
        this.contentType = contentType;
    }
}
