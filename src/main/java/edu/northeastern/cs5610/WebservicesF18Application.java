package edu.northeastern.cs5610;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class WebservicesF18Application {

	public static void main(String[] args) {
		SpringApplication.run(WebservicesF18Application.class, args);
	}
}
