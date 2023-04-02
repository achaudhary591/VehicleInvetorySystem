package com.lld.automobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class VehicleInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleInventoryApplication.class, args);
	}

}
