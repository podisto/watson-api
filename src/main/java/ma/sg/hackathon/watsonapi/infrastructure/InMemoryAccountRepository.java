package ma.sg.hackathon.watsonapi.infrastructure;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Created by podisto on 27/03/2022.
 */
@Component
@Slf4j
public class InMemoryAccountRepository implements AccountRepository {

    private final List<Account> accounts = asList(
            new Account("0144144", "comptes cheques ordinaires", "C", 13293.83, "012015789"),
            new Account("0123123", "comptes cheques ordinaires", "C", 20000, "012015789"),
            new Account("0123123", "comptes cheques ordinaires", "C", 20000, "012015789")
    );

    @Override
    public List<Account> byUserId(String userId) {
        log.info("<< find accounts for {} >>", userId);
        return accounts.stream()
                .filter(a -> a.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

}
