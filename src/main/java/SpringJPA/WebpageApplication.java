package SpringJPA;


import SpringJPA.Model.Customer;
import SpringJPA.Model.User;
import SpringJPA.Model.UserRepository;
import SpringJPA.Model.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableScheduling
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

			User user1 = new User("User1", passwordEncoder.encode("Password123"), "user1@gmail.com", UserType.ROLE_TRIAL, Integer.toUnsignedLong(998), Integer.toUnsignedLong(1000));
			User user2 = new User("User2", passwordEncoder.encode("123Password"), "user2@hotmail.com", UserType.ROLE_TRIAL);
			User user3 = new User("User3", passwordEncoder.encode("Pass123word"), "user3@yahoo.com", UserType.ROLE_TRIAL, Integer.toUnsignedLong(0), Integer.toUnsignedLong(1000));
			User user4 = new User("admin", passwordEncoder.encode("1Pass2word3"), "admin@admin.com", UserType.ROLE_ADMIN);

			repository.save(user1);
			repository.save(user2);
			repository.save(user3);
			repository.save(user4);

			for (User user: repository.findAll()){
				log.info(user.toString());
			}


			log.info("Done base logs");

		};
	}


}
