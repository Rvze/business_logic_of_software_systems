package com.mpanchuk.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mpanchuk.app")
public class ItemApproveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemApproveApplication.class, args);
	}

}
