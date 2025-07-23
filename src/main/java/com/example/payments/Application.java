package com.example.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.payments")
@EnableJpaRepositories("com.example.payments.repository")  // where your repositories are
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
