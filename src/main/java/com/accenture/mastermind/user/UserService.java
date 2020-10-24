package com.accenture.mastermind.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

  User findByUsername(String username);

  User save(User user);



}
