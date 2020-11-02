package com.accenture.mastermind.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @GetMapping("/")
  public @ResponseBody
  ResponseEntity<List<UserDTO>> getAll() {
    return new ResponseEntity<>(userMapper.toUserDTO(userService.findAll()), HttpStatus.OK);
  }

  @PostMapping({"/signup/", "/signup"})
  public @ResponseBody
  ResponseEntity<UserDTO> create(@RequestBody UserDTO.withPass newUser) {
    User user = userMapper.fromWithPassword(newUser);
    return new ResponseEntity<>(userMapper.toDTO(userService.save(user)), HttpStatus.OK);
  }

}
