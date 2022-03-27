package ma.sg.hackathon.watsonapi.application;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.infrastructure.api.VoiceRequest;
import org.springframework.stereotype.Service;

/**
 * Created by podisto on 27/03/2022.
 */
@Service
@Slf4j
public class AccountService {

    private final AccountClient accountClient;

    public AccountService(AccountClient accountClient) {
        this.accountClient = accountClient;
    }

    public void getBalance(String customerNumber, VoiceRequest voice) {
        log.info("<< get balance for {} >>", customerNumber);
        // accountClient.getAccounts(customerNumber);
    }
}
