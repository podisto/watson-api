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
            new User("0987654", "123"),
            new User("1101198", "09123456"),
            new User("0967543", "123456Aa"),
            new User("1206202", "passer@123")
    );

    @Override
    public boolean authenticate(String userId, String password) {
        return users.stream().anyMatch(u -> u.getUserId().equals(userId) && u.getPassword().equals(password));
    }
}
