package ma.sg.hackathon.watsonapi.infrastructure.api;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.SpeechToTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by podisto on 26/03/2022.
 */
@RestController
@RequestMapping("/speech")
@Slf4j
public class SpeechToTextResource {

    @Autowired
    private SpeechToTextService speechToTextService;

    @PostMapping
    public ResponseEntity<String> toText(@RequestBody WelcomeRequest request) {
        log.info("Transcript to text");
        try {
            //speechToTextService.toText(file);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.info("Exception occurred {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
