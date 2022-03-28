package ma.sg.hackathon.watsonapi.application;

import lombok.Data;

/**
 * Created by podisto on 27/03/2022.
 */
@Data
public class Account {
    private final String userId;
    private final String type;
    private final String category;
    private final double balance;
    private final String number;

    public Account(String userId, String type, String category, double balance, String number) {
        this.userId = userId;
        this.type = type;
        this.category = category;
        this.balance = balance;
        this.number = number;
    }
}
