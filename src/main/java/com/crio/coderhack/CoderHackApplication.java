package com.crio.coderhack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@RestController
@OpenAPIDefinition(
    info = @Info(
        title = "CoderHack Leaderboard API",
        version = "1.0",
        description = "API for managing the leaderboard in the CoderHack application"
    ),
    servers = @Server(
        url = "http://localhost:8081",
        description = "CoderHack Leaderboard API url"
    )
)
public class CoderHackApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoderHackApplication.class, args);
        System.out.println("\nWelcome to the CoderHack Leaderboard API!");
        System.out.println("\nThe application has started successfully.");
        System.out.println("\nYou can now interact with the API endpoints to manage leaderboard data.");
    }

    @GetMapping("/coderhack")
    public String message() {
        return "Welcome to CoderHack Leaderboard API!";
    }
}
