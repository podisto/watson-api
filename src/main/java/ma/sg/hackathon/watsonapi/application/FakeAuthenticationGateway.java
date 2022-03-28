package ma.sg.hackathon.watsonapi.application;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by podisto on 27/03/2022.
 */
@Component
public class FakeAuthenticationGateway implements AuthenticationGateway {

    private final List<User> users = asList(
            new User("0144144", "123456Saf"),
            new User("2356789", "0789&@$*%"),
            new User("1101199", "0789$@&*%"),
            new User("1101198", "09123456"),
            new User("0967543", "123456Aa"),
            new User("0123123", "123456Az")
    );

    @Override
    public boolean authenticate(String userId, String password) {
        return users.stream().anyMatch(u -> u.getUserId().equals(userId) && u.getPassword().equals(password));
    }
}
