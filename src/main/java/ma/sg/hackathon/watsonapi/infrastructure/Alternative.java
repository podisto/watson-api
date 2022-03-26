package ma.sg.hackathon.watsonapi.infrastructure;

import lombok.Data;

/**
 * Created by podisto on 26/03/2022.
 */
@Data
public class Alternative {
    private String transcript;
    private double confidence;
}
