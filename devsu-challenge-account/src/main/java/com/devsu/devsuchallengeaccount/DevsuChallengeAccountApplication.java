package com.devsu.devsuchallengeaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
public class DevsuChallengeAccountApplication {

  public static void main(String[] args) {
    SpringApplication.run(DevsuChallengeAccountApplication.class, args);
  }

}
