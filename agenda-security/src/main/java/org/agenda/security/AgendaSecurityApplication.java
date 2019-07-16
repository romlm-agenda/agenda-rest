package org.agenda.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AgendaSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaSecurityApplication.class, args);
	}

}
