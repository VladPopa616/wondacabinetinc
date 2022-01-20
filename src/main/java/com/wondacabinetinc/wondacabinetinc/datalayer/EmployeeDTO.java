package com.wondacabinetinc.wondacabinetinc.datalayer;

import java.util.ArrayList;
import java.util.Collection;

public class EmployeeDTO {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private Collection<Role> roles = new ArrayList<>();

    public EmployeeDTO() {
    }

    public EmployeeDTO(Integer id, String username, String email, String password, Collection<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
