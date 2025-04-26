package com.paw.fund;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@EnableFeignClients
@SpringBootApplication
public class PawFundBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PawFundBackendApplication.class, args);
	}

}
