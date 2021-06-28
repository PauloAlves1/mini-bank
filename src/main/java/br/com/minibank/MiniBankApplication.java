package br.com.minibank;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@Log4j2
@SpringBootApplication
public class MiniBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniBankApplication.class, args);
		log.info("Mini-Bank API iniciada com sucesso as {}", LocalDateTime.now());
	}

}
