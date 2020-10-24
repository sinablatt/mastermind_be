package com.accenture.mastermind.user;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Generated(GenerationTime.ALWAYS)
  @Column(name = "id")
  private String id;

  @Column(name = "username",  unique=true )
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "account_expiration_date")
  private LocalDate accountExpirationDate;

  @Column(name = "credentials_expiration_date")
  private LocalDate credentialsExpirationDate;

  @Column(name = "locked")
  @Type(type = "org.hibernate.type.NumericBooleanType")
  private Boolean locked;

  @Column(name = "enabled")
  @Type(type = "org.hibernate.type.NumericBooleanType")
  private Boolean enabled;


  public String getUsername() {
    return username;
  }

  public User setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getId() {
    return id;
  }

  public User setId(String id) {
    this.id = id;
    return this;
  }

  public LocalDate getAccountExpirationDate() {
    return accountExpirationDate;
  }

  public User setAccountExpirationDate(LocalDate accountExpirationDate) {
    this.accountExpirationDate = accountExpirationDate;
    return this;
  }

  public LocalDate getCredentialsExpirationDate() {
    return credentialsExpirationDate;
  }

  public User setCredentialsExpirationDate(LocalDate credentialsExpirationDate) {
    this.credentialsExpirationDate = credentialsExpirationDate;
    return this;
  }

  public Boolean getLocked() {
    return locked;
  }

  public User setLocked(Boolean locked) {
    this.locked = locked;
    return this;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public User setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

}
