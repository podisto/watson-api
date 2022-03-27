package ma.sg.hackathon.watsonapi.infrastructure.api;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by podisto on 27/03/2022.
 */
@RestController
@RequestMapping
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationResource {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/check-identity")
    public ResponseEntity<byte[]> checkIdentityNumber(@RequestBody VoiceRequest voice) {
        log.info("<< identity number data: {}, tags: {} >>", voice.getData(), voice.getTag());
        byte[] file = authenticationService.checkIdentityNumber(voice);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=identity.mp3");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(file);
    }

    @PostMapping("/confirm-identity")
    public ResponseEntity<byte[]> confirmIdentity(@RequestBody VoiceRequest voice) {
        log.info("<< confirm identity >>");
        byte[] file = authenticationService.confirmIdentity(voice);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=confirm_identity.mp3");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(file);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CredentialsRequest credentials) {
        log.info("<< login with identity {} >>", credentials.getIdentity());
        authenticationService.login(credentials);
        return ResponseEntity.ok("OK");
    }
}
