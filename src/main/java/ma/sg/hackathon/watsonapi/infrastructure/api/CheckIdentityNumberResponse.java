package ma.sg.hackathon.watsonapi.infrastructure.api;

import lombok.Data;

/**
 * Created by podisto on 27/03/2022.
 */
@Data
public class CheckIdentityNumberResponse {
    private final byte[] voice;
    private final String id;

    public CheckIdentityNumberResponse(byte[] voice, String id) {
        this.voice = voice;
        this.id = id;
    }
}
