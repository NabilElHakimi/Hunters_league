package org.rabie.hunters_league;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.rabie.hunters_league.repository")
@EnableScheduling
public class HuntersLeagueApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuntersLeagueApplication.class, args);
    }

}
