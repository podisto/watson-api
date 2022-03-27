package ma.sg.hackathon.watsonapi.infrastructure.api;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

/**
 * Created by podisto on 27/03/2022.
 */
@RestController
@RequestMapping
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/check-identity")
    public ResponseEntity<String> checkIdentityNumber(@RequestBody VoiceRequest voice) {
        log.info("<< check identity number data: {}, tags: {} >>", voice.getData(), voice.getTag());
        authenticationService.checkIdentityNumber(voice);
        return ResponseEntity.ok("OK");
    }

}
