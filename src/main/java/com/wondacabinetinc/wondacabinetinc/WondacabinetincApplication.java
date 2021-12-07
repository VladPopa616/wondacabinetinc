package com.wondacabinetinc.wondacabinetinc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@SpringBootApplication
public class WondacabinetincApplication {


	@RequestMapping("/")
	public String home(){
		return "Hello Docker World";
	}

	public static void main(String[] args) {
		SpringApplication.run(WondacabinetincApplication.class, args);
	}

}
