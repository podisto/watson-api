package ma.sg.hackathon.watsonapi.infrastructure;

import ma.sg.hackathon.watsonapi.application.Account;

import java.util.List;

/**
 * Created by podisto on 27/03/2022.
 */
public interface AccountRepository {

    List<Account> byUserId(String userId);
}
