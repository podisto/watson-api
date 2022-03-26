package ma.sg.hackathon.watsonapi.infrastructure.api;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.TextToSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by podisto on 26/03/2022.
 */
@RestController
@RequestMapping("/text")
@Slf4j
public class TextToSpeechResource {

    @Autowired
    private TextToSpeechService toSpeechService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> toSpeech(@RequestBody String text) {
        log.info("<< Convert {} to speech >>", text);
        toSpeechService.toSpeech(text);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
