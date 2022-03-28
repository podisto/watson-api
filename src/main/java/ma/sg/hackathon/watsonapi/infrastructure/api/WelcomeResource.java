package ma.sg.hackathon.watsonapi.infrastructure.api;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.WelcomeService;
import ma.sg.hackathon.watsonapi.infrastructure.Base64Utils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ma.sg.hackathon.watsonapi.infrastructure.Constants.ANSWER;

/**
 * Created by podisto on 26/03/2022.
 */
@RestController
@RequestMapping
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WelcomeResource {

    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    private final WelcomeService welcomeService;

    public WelcomeResource(WelcomeService welcomeService) {
        this.welcomeService = welcomeService;
    }

    @GetMapping("/greetings")
    public ResponseEntity<byte[]> sendGreeting() {
        byte[] file = welcomeService.sendGreeting();
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_DISPOSITION, "attachment; filename=greetings.mp3");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(file);
    }

    @PostMapping(value = "/greetings-answer")
    public ResponseEntity<byte[]> answerQuestion(@RequestBody VoiceRequest request) { // oui non sur le header
        log.info("<< answer to question {} tags {} >>", request.getData(), request.getTag());
        byte[] data = Base64Utils.toBase64(request.getData());
        String contentType = Base64Utils.getContentTypeFromTag(request.getTag());
        ApiResponse response = welcomeService.toText(data, contentType);
        HttpHeaders headers = new HttpHeaders();
        headers.set(ANSWER, response.getAnswer());
        headers.set(CONTENT_DISPOSITION, "attachment; filename=login.mp3");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response.getVoice());
    }

    @GetMapping("/init-login")
    public ResponseEntity<byte[]> init() {
        byte[] file = welcomeService.initLogin();
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_DISPOSITION, "attachment; filename=initlogin.mp3");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(file);
    }
}
