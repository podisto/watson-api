package ma.sg.hackathon.watsonapi.infrastructure.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.sg.hackathon.watsonapi.application.DashboardService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ma.sg.hackathon.watsonapi.infrastructure.Constants.ANSWER;

/**
 * Created by podisto on 28/03/2022.
 */
@RestController
@RequestMapping("/dashboard")
@Slf4j
@RequiredArgsConstructor
public class DashboardResource {

    private final DashboardService dashboardService;

    @PostMapping("/answer")
    public ResponseEntity<byte[]> answer(@RequestBody VoiceRequest voice) {
        log.info("<< Answer {} >>");
        ApiResponse response = dashboardService.processAnswer(voice);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=answer.mp3");
        headers.set(ANSWER, response.getAnswer());
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response.getVoice());
    }
}
