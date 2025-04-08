package fr.inventivit.DreamCaseApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DreamCaseAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(DreamCaseAppApplication.class, args);
	}
}
