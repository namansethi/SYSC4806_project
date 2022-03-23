package SpringJPA;


import SpringJPA.Model.User;
import SpringJPA.Model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class WebpageApplication {

	private static final Logger log = LoggerFactory.getLogger(WebpageApplication.class);

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public static void main(String[] args) {
		SpringApplication.run(WebpageApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			log.info("Logging started");

			User user1 = new User("User1", passwordEncoder.encode("Password123"), "USER", "user1@gmail.com");
			User user2 = new User("User2", passwordEncoder.encode("123Password"), "USER", "user2@gmail.com");
			User user3 = new User("User3", passwordEncoder.encode("Pass123word"), "USER", "user3@gmail.com");

			repository.save(user1);
			repository.save(user2);
			repository.save(user3);

			for (User user: repository.findAll()){
				log.info(user.toString());
			}


			log.info("Done base logs");

		};
	}


}
