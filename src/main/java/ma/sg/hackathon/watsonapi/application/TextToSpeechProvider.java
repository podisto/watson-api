package ma.sg.hackathon.watsonapi.application;

/**
 * Created by podisto on 26/03/2022.
 */
public interface TextToSpeechProvider {

    byte[] toSpeech(String text);

}
