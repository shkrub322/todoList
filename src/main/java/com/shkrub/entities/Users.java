package com.shkrub.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Users {
    @Id
    private String username;
    private String password;

    public Users() {
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return username.equals(users.username) &&
                password.equals(users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
