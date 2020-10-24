package com.accenture.mastermind;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {


  public static void main(String[] args) {

    System.out.println(new BCryptPasswordEncoder().encode("12345"));

  }

}