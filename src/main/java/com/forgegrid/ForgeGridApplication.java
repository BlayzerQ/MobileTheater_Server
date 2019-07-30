package com.forgegrid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForgeGridApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForgeGridApplication.class, args);
		System.out.println("INFO: WebServer has been started!");
	}

}
