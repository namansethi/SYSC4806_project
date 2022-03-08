package SpringJPA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebpageApplication {

	private static final Logger log = LoggerFactory.getLogger(WebpageApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WebpageApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(EntityRepository repository) {
//		return (args) -> {
//
//			}
//			log.info("Done base logs");
//
//
//		};

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			log.info("Logging started");
			log.info("Done base logs");

		};
	}


}
