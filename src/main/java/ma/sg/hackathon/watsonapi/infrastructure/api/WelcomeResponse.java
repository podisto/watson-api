package ma.sg.hackathon.watsonapi.infrastructure.api;

import lombok.Data;

/**
 * Created by podisto on 26/03/2022.
 */
@Data
public class WelcomeResponse {
    private boolean response;

    public WelcomeResponse(boolean response) {
        this.response = response;
    }
}
