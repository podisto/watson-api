package ma.sg.hackathon.watsonapi.infrastructure.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by podisto on 27/03/2022.
 */
@RestController
@RequestMapping
@Slf4j
public class ReadSoundResource {

    @GetMapping("/download")
    public ResponseEntity<byte[]> play() throws IOException {
        File f = new File("src/main/resources/fallback.mp3");
        byte[] file = Files.readAllBytes(f.toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=\"" + f.getName() +".mp3\"");
        return new ResponseEntity(file, headers, HttpStatus.OK);
    }
}
