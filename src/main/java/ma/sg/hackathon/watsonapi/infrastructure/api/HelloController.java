package ma.sg.hackathon.watsonapi.infrastructure.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by podisto on 26/03/2022.
 */
@RestController
@RequestMapping
public class HelloController {

    @GetMapping("/status")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("UP");
    }
}
