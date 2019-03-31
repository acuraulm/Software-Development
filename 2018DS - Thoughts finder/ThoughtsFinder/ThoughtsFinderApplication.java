package acuraulm.ThoughtsFinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ThoughtsFinderApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ThoughtsFinderApplication.class, args);
		
		System.out.println("\nMain successfully ran.");

	}
}
