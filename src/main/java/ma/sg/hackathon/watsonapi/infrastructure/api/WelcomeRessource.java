package ma.sg.hackathon.watsonapi.infrastructure.api;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.WelcomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by podisto on 26/03/2022.
 */
@RestController
@RequestMapping
@Slf4j
public class WelcomeRessource {

    private final WelcomeService welcomeService;

    public WelcomeRessource(WelcomeService welcomeService) {
        this.welcomeService = welcomeService;
    }

    @PostMapping("/welcome")
    public ResponseEntity<WelcomeResponse> welcome(@RequestParam("file") MultipartFile file) {
        log.info("<< welcome >>");
        WelcomeResponse welcomeResponse = welcomeService.toText(file);
        return ResponseEntity.ok(welcomeResponse);
    }
}
