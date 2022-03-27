package ma.sg.hackathon.watsonapi.infrastructure.api;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.WelcomeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

/**
 * Created by podisto on 26/03/2022.
 */
@RestController
@RequestMapping
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WelcomeRessource {

    private final WelcomeService welcomeService;

    public WelcomeRessource(WelcomeService welcomeService) {
        this.welcomeService = welcomeService;
    }

    @PostMapping(value = "/welcome", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WelcomeResponse> welcome(@RequestBody WelcomeRequest request) {
        log.info("<< welcome {} tags {} >>", request.getData(), request.getTag());
        String encoded = request.getData();
        byte[] data = Base64.getDecoder().decode(encoded);
        WelcomeResponse welcomeResponse = welcomeService.toText(data);
        return ResponseEntity.ok(welcomeResponse);
    }
}
