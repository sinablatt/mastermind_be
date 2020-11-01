package com.accenture.mastermind.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private BCryptPasswordEncoder bCryptPasswordEncoder;
  private UserRepository userRepository;
  private Logger logger;

  @Autowired
  public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, Logger logger) {
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.userRepository = userRepository;
    this.logger = logger;
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      return new UserDetailsImpl(findByUsername(username));
    } catch (NoSuchElementException e) {
      logger.debug("Exception while loading User by username occurred: ", e);
      throw new UsernameNotFoundException(e.getMessage());
    }
  }

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public List<User> findAll(){
    return userRepository.findAll();
  }

  @Override
  public User save(User user) {

    logger.debug("trying to set password");

    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    logger.debug("password is set");

    user.setLocked(false);
    logger.debug("New User is not locked");

    user.setEnabled(true);
    logger.debug("New User is enabled");

    return userRepository.save(user);
  }


}
