package ma.sg.hackathon.watsonapi.application;

import ma.sg.hackathon.watsonapi.infrastructure.SpeechToText;

import java.io.IOException;

/**
 * Created by podisto on 26/03/2022.
 */
public interface SpeechToTextProvider {

    String toText(SpeechToText request);

}
