package ma.sg.hackathon.watsonapi.infrastructure.api;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by podisto on 27/03/2022.
 */
@RestController
@RequestMapping
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountResource {

    @Autowired
    private AccountService accountService;

    @PostMapping("/balance/{customerNumber}")
    public ResponseEntity<String> getBalance(@RequestBody VoiceRequest voice, @PathVariable("customerNumber") String customerNumber) {
        log.info("<< get balance for {} >>", customerNumber);
        accountService.getBalance(customerNumber, voice);
        return null;
    }
}
