package ma.sg.hackathon.watsonapi.infrastructure;

import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.ChoiceNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by podisto on 26/03/2022.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ChoiceNotFoundException.class)
    protected ResponseEntity<Object> handleChoiceNotFoundException(ChoiceNotFoundException ex) throws FileNotFoundException {
        log.info("Return fallback audio");
        String file = "src/main/resources/fallback.mp3";
        long length = new File(file).length();
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentLength(length);
        httpHeaders.setCacheControl(CacheControl.noCache().getHeaderValue());
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(inputStreamResource);
    }
}
