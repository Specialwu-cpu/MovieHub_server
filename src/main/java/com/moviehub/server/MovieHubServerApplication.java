package com.moviehub.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class MovieHubServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieHubServerApplication.class, args);
    }

}
