package ma.sg.hackathon.watsonapi.infrastructure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by podisto on 26/03/2022.
 */
@ConfigurationProperties(prefix = "watson")
@Data
public class WatsonProperties {
    private SpeechToTextConfig speechToText;
    private TextToSpeechConfig textToSpeech;

    @Data
    public static class SpeechToTextConfig {
        private String url;
        private String apiKey;
    }

    @Data
    public static class TextToSpeechConfig {
        private String url;
        private String apiKey;
    }
}
