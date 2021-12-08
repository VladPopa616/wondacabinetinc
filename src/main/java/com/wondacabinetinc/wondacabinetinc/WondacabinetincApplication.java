package com.wondacabinetinc.wondacabinetinc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.wondacabinetinc")
public class WondacabinetincApplication {

	public static void main(String[] args) {

		SpringApplication.run(WondacabinetincApplication.class, args);
	}

}
