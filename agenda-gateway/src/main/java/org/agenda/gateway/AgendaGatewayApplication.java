package org.agenda.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableFeignClients("org.agenda.gateway.proxies")
public class AgendaGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaGatewayApplication.class, args);
	}

}
