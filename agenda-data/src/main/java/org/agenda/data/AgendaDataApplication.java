package org.agenda.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("org.agenda.data.proxies")
@EnableMongoRepositories("org.agenda.data.dao")
public class AgendaDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaDataApplication.class, args);
	}

}
