package ma.sg.hackathon.watsonapi.infrastructure.api;

import lombok.Data;

/**
 * Created by podisto on 27/03/2022.
 */
@Data
public class ApiResponse {
    private byte[] voice;
    private String answer;

    public ApiResponse(byte[] voice, String answer) {
        this.voice = voice;
        this.answer = answer;
    }
}
