package com.example.gateway.filter;

import com.example.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private RestTemplate template;

//    @Autowired
//    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                try {
                    System.out.println(authHeader);
                    HttpHeaders headers = new HttpHeaders();
                    headers.set("Authorization", "Bearer " + authHeader);

                    // Prepare HttpEntity with headers
                    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

                    // Make the request
                    ResponseEntity<String> responseEntity = template.exchange(
                            "http://localhost:8081/users1/api/korisnik/validatetoken",
                            HttpMethod.GET,
                            requestEntity,
                            String.class
                    );



                    System.out.println("Response: " + responseEntity.getBody());

//                    template.getForObject("http://localhost:8081/users1/api/korisnik/validatetoken", String.class);
//                    template.exchange()
////                    jwtUtil.validateToken(authHeader);

                } catch (Exception e) {
                    System.out.println("invalid access!");
                    throw new RuntimeException("Unauthorized access to application");
                }
            }
            return chain.filter(exchange);


        });
    }

    public static class Config {

    }
}
