package ma.sg.hackathon.watsonapi.application.balance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.balance.Account;
import ma.sg.hackathon.watsonapi.infrastructure.AccountRepository;
import ma.sg.hackathon.watsonapi.infrastructure.api.VoiceRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by podisto on 27/03/2022.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void getBalance(String customerNumber, VoiceRequest voice) {
        log.info("<< get balance for {} >>", customerNumber);
        List<Account> accounts = accountRepository.byAccountNumber(customerNumber);
    }
}
