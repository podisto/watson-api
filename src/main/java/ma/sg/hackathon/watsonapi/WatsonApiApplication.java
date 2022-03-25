package ma.sg.hackathon.watsonapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatsonApiApplication implements CommandLineRunner {

	@Autowired
	private SpeechToTextService speechToTextService;

	public static void main(String[] args) {
		SpringApplication.run(WatsonApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		speechToTextService.toText();
	}
}
