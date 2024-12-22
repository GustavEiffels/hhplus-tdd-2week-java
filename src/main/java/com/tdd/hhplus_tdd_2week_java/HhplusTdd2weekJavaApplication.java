package com.tdd.hhplus_tdd_2week_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HhplusTdd2weekJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HhplusTdd2weekJavaApplication.class, args);
	}

}
