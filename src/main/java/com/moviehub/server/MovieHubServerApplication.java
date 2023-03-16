package com.moviehub.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wsh ruan
 */
//@EnableJpaRepositories(basePackages = {"com.moviehub.server.repository"})
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@ComponentScan(value = {"com.moviehub.server.repository"})
@SpringBootApplication
public class MovieHubServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieHubServerApplication.class, args);
    }

}
