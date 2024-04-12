package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}


	//@Bean
	//public RouteLocator routerBuilder(RouteLocatorBuilder routeLocatorBuilder){
		//return routeLocatorBuilder.routes()
				//.route("soa",r->r.path("/api/korisnik/**")
						//.uri("http://soa:8011"))
				//.route("soablog",r->r.path("/api/blogovi/**")
						//.uri("http://soablog:8012")).build();
	//}

}
