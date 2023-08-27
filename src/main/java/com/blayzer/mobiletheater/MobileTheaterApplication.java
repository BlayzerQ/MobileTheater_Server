package com.blayzer.mobiletheater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MobileTheaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileTheaterApplication.class, args);
		System.out.println("INFO: MTServer has been started!");
	}

}
