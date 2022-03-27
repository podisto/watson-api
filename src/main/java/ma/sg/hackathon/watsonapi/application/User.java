package ma.sg.hackathon.watsonapi.application;

import lombok.Data;

/**
 * Created by podisto on 27/03/2022.
 */
@Data
public class User {
    private String userId;
    private String password;

    public User() {
    }

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
