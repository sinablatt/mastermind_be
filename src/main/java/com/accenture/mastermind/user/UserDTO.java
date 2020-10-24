package com.accenture.mastermind.user;


public class UserDTO {

  private String id;

  private String username;


  public static class withPass extends UserDTO {

    private String password;

    public String getPassword() {
      return password;
    }

    public withPass setPassword(String password) {
      this.password = password;
      return this;
    }
  }


  public String getId() {
    return id;
  }

  public UserDTO setId(String id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public UserDTO setUsername(String username) {
    this.username = username;
    return this;
  }

}
