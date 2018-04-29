package com.epam.provider.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;


/**
 * This entity contains information about client of internet provider
 *
 * @author Gleb Aksenov
 */
public class Profile implements Entity, Serializable {

  private Integer profileId;
  private String firstName;
  private String secondName;
  private String passport;

  /**
   * Client tariff id
   */
  private Integer idTariffs;
  private Double balance;

  /**
   * When the client was registered
   */
  private Date registerDate;
  private String login;
  private String password;
  private String role;

  public Profile() {
  }

  public String getLogin() {
    return login;
  }

  public Profile setLogin(String login) {
    this.login = login;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public Profile setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getRole() {
    return role;
  }

  public Profile setRole(String role) {
    this.role = role;
    return this;
  }

  public Integer getProfileId() {
    return profileId;
  }

  public Profile setProfileId(Integer profileId) {
    this.profileId = profileId;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public Profile setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getSecondName() {
    return secondName;
  }

  public Profile setSecondName(String secondName) {
    this.secondName = secondName;
    return this;
  }

  public String getPassport() {
    return passport;
  }

  public Profile setPassport(String passport) {
    this.passport = passport;
    return this;
  }

  public Integer getIdTariffs() {
    return idTariffs;
  }

  public Profile setIdTariffs(Integer idTariffs) {
    this.idTariffs = idTariffs;
    return this;
  }

  public Double getBalance() {
    return balance;
  }

  public Profile setBalance(Double balance) {
    this.balance = balance;
    return this;
  }

  public Date getRegisterDate() {
    return registerDate;
  }

  public Profile setRegisterDate(Date registerDate) {
    this.registerDate = registerDate;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profile profile = (Profile) o;
    return Objects.equals(profileId, profile.profileId) &&
        Objects.equals(firstName, profile.firstName) &&
        Objects.equals(secondName, profile.secondName) &&
        Objects.equals(passport, profile.passport) &&
        Objects.equals(idTariffs, profile.idTariffs) &&
        Objects.equals(balance, profile.balance) &&
        Objects.equals(registerDate, profile.registerDate);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(profileId, firstName, secondName, passport, idTariffs, balance, registerDate);
  }

}
