package com.weekclone.marketkurlyclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MarketkurlyCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketkurlyCloneApplication.class, args);
	}

}
