package ma.sg.hackathon.watsonapi.infrastructure;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.ChoiceNotFoundException;
import ma.sg.hackathon.watsonapi.application.TextToSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

/**
 * Created by podisto on 26/03/2022.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String FALLBACK_RESPONSE = "Je n'ai pas encore la réponse à votre question, vous pouvez soit contacter notre Service Relations clients au 42 42 ou votre conseiller";

    @Autowired
    private TextToSpeechService textToSpeechService;

    @ExceptionHandler(ChoiceNotFoundException.class)
    protected ResponseEntity<Object> handleChoiceNotFoundException(ChoiceNotFoundException ex) throws FileNotFoundException {
        log.info("<< Return fallback audio >>");
        byte[] voice = textToSpeechService.toSpeech(FALLBACK_RESPONSE);
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_DISPOSITION, "attachment; filename=fallback.mp3");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(voice);
    }
}
