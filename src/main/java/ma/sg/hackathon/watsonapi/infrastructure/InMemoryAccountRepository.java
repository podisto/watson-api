package ma.sg.hackathon.watsonapi.infrastructure;

import ma.sg.hackathon.watsonapi.application.Account;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by podisto on 27/03/2022.
 */
@Component
public class InMemoryAccountRepository implements AccountRepository {

    private List<Account> accounts = asList(
            new Account("comptes cheques ordinaires", "C", 20000, "012015789"),
            new Account("comptes cheques ordinaires", "C", 20000, "012015789")
    );

    @Override
    public List<Account> byAccountNumber(String customerNumber) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
