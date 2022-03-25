package ma.sg.hackathon.watsonapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by podisto on 26/03/2022.
 */
@Data
public class SpeechToTextResponse {
    @JsonProperty("result_index")
    private int resultIndex;
    @JsonProperty("results")
    private List<Result> results;

}
