package ma.sg.hackathon.watsonapi.infrastructure.api;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ma.sg.hackathon.watsonapi.infrastructure.Constants.ANSWER;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by podisto on 27/03/2022.
 */
@RestController
@RequestMapping
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationResource {

    public static final String ID = "Id";
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/check-identity")
    public ResponseEntity<byte[]> checkIdentityNumber(@RequestBody VoiceRequest voice) {
        log.info("<< identity number tags: {} >>", voice.getTag());
        CheckIdentityNumberResponse response = authenticationService.checkIdentityNumber(voice);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=identity.mp3");
        headers.set(ID, response.getId());
        return ResponseEntity.status(OK).headers(headers).body(response.getVoice());
    }

    @PostMapping("/login")
    public ResponseEntity<byte[]> login(@RequestHeader(ID) String userId, @RequestBody VoiceRequest voice) {
        log.info("<< login with userId {} >>", userId);
        ApiResponse response = authenticationService.login(userId, voice);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=identity.mp3");
        headers.set(ANSWER, response.getAnswer());
        headers.set(ID, userId);
        return ResponseEntity.status(OK).headers(headers).body(response.getVoice());
    }
}
