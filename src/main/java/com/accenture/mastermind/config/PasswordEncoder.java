package com.accenture.mastermind.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
  @Bean
  public BCryptPasswordEncoder pwEncoder() {
    return new BCryptPasswordEncoder();
  }

}
