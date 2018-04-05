package com.epam.provider.model;

import java.sql.Date;
import java.util.Objects;


public class Profile implements Entity {
    private Integer idProfiles;
    private String firstName;
    private String secondName;
    private String passport;
    private Integer tariff;
    private Double balance;
    private Date registerDate;


    public Integer getIdProfiles() {
        return idProfiles;
    }

    public void setIdProfiles(Integer idProfiles) {
        this.idProfiles = idProfiles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Integer getTariff() {
        return tariff;
    }

    public void setTariff(Integer tariff) {
        this.tariff = tariff;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(idProfiles, profile.idProfiles) &&
                Objects.equals(firstName, profile.firstName) &&
                Objects.equals(secondName, profile.secondName) &&
                Objects.equals(passport, profile.passport) &&
                Objects.equals(tariff, profile.tariff) &&
                Objects.equals(balance, profile.balance) &&
                Objects.equals(registerDate, profile.registerDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProfiles, firstName, secondName, passport, tariff, balance, registerDate);
    }
}
