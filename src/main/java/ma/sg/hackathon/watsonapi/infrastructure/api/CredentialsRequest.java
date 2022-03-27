package ma.sg.hackathon.watsonapi.infrastructure.api;

import lombok.Data;

/**
 * Created by podisto on 27/03/2022.
 */
@Data
public class CredentialsRequest {
    private String identity;
    private String data;
    private String tag;
}
