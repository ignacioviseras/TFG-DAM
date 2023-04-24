package com.qraccess.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class QraccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(QraccessApplication.class, args);
	}

}
