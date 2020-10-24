package com.accenture.mastermind.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/users")
public class UserController {


  private UserService userService;
  private UserMapper userMapper;


  @Autowired
  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }


  @PostMapping({"", "/"})
  public @ResponseBody ResponseEntity<UserDTO> create(@RequestBody UserDTO.withPass newUser) {
    User user = userMapper.fromWithPassword(newUser);
    return new ResponseEntity<>(userMapper.toDTO(userService.save(user)), HttpStatus.OK);
  }

}
