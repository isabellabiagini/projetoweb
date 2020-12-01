package br.com.bandtec.ac3isabellacosta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@EnableScheduling
public class Ac3IsabellacostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ac3IsabellacostaApplication.class, args);
	}

}
