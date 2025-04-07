package com.example.gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {


    public static final List<String> openApiEndpoints = List.of(
            "/users1/api/korisnik/registrovanikorisnik",
            "/users1/api/korisnik/login1"
//            "/blogs/kreiranjebloga.html",
//            "/blogs/sviblogovi.html",
//            "/blogs/svikomentari.html",
//            "/blogs/sviblogovi.js",
//            "/blogs/svikomentari.js",
//            "/blogs/kreiranjebloga.html",
//            "/blogs/kreiranjebloga.js",
//            "/blogs/jquery.min.js",
//            "/blogs/jquery.validate.min.js",
//            "/blogs/dodajkomentar.html",
//            "/blogs/dodajkomentar.js",
//            "/blogs/blog.html",
//            "/blogs/blog.js"
//            "/tours/kreiranjetura.html",
//            "/tours/kreiranjetura.js",
//            "/tours/kupiture.html",
//            "/tours/sveture.js",
//            "/tours/jquery.min.js",
//            "/tours/jquery.validate.min.js",
//            "/*.png",
//            "/*/*.png"

    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
