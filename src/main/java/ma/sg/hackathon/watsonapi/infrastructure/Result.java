package ma.sg.hackathon.watsonapi.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by podisto on 26/03/2022.
 */
@Data
public class Result {
    @JsonProperty("final")
    private boolean isFinal;
    @JsonProperty("alternatives")
    private List<Alternative> alternatives;

}
