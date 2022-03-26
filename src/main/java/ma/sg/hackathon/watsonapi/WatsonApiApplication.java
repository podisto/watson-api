package ma.sg.hackathon.watsonapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("ma.sg.hackathon.watsonapi")
public class WatsonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatsonApiApplication.class, args);
	}
}
