package com.epam.provider.model;

import java.util.Date;

/**
 * Created by HP on 26.03.2018.
 */
public class Profile extends Entity {
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

}
