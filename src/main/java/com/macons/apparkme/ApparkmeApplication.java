package com.macons.apparkme;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.*")
@SpringBootApplication
public class ApparkmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApparkmeApplication.class, args);
	}

}
