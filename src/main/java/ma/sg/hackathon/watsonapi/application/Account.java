package ma.sg.hackathon.watsonapi.application;

import lombok.Data;

/**
 * Created by podisto on 27/03/2022.
 */
@Data
public class Account {
    private final String type;
    private final String category;
    private final int balance;
    private final String number;

    public Account(String type, String category, int balance, String number) {
        this.type = type;
        this.category = category;
        this.balance = balance;
        this.number = number;
    }
}
