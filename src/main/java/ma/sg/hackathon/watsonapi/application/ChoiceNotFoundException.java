package ma.sg.hackathon.watsonapi.application;

/**
 * Created by podisto on 26/03/2022.
 */
public class ChoiceNotFoundException extends RuntimeException {
    public ChoiceNotFoundException(String message) {
        super(message);
    }
}
