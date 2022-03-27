package ma.sg.hackathon.watsonapi.infrastructure.api;

import lombok.Data;

/**
 * Created by podisto on 27/03/2022.
 */
@Data
public class GreetingAnswerResponse {
    private byte[] voice;
    private String answer;

    public GreetingAnswerResponse(byte[] voice, String answer) {
        this.voice = voice;
        this.answer = answer;
    }
}
