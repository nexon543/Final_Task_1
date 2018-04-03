package com.epam.provider.model;

import java.util.Objects;

/**
 * Created by HP on 27.03.2018.
 */
public class User implements Entity {

    private Integer idUsers;
    private String login;
    private String password;
    private Integer idProfiles;
    private String role;

    public Integer getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Integer idUsers) {
        this.idUsers = idUsers;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdProfiles() {
        return idProfiles;
    }

    public void setIdProfiles(Integer idProfiles) {
        this.idProfiles = idProfiles;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(idUsers, user.idUsers) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(idProfiles, user.idProfiles) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsers, login, password, idProfiles, role);
    }
}
