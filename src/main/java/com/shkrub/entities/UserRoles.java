package com.shkrub.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_roles", schema = "public", catalog = "todolist")
@IdClass(UserRolesPK.class)
public class UserRoles {
    private String username;
    private String role;

    public UserRoles() {
    }

    public UserRoles(String username) {
        this.username = username;
        this.role = "USER";
    }

    @Id
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    @Column(name = "role")
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
        UserRoles userRoles = (UserRoles) o;
        return Objects.equals(username, userRoles.username) &&
                Objects.equals(role, userRoles.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }
}
