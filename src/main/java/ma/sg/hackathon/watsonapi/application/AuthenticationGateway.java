package ma.sg.hackathon.watsonapi.application;

/**
 * Created by podisto on 27/03/2022.
 */
public interface AuthenticationGateway {

    boolean authenticate(String userId, String password);

}
