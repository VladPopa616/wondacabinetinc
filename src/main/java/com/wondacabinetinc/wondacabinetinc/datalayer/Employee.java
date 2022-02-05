package com.wondacabinetinc.wondacabinetinc.datalayer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Employee extends EmployeeDTO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;

    @NotBlank
    @Size(min = 2, max = 50)
    private String phone;

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 200)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Employee() {
    }

    public Employee(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public Employee(Long uid,String username, String email, String password, Set<Role> roles) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Employee(String firstName, String lastName, String phoneNumber, String username, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phoneNumber;
        this.username = username;
        this.email = email;
        this.password = password;


    }

    public Employee(Long uid, String firstName, String lastName, String phoneNumber, String username, String email, String password, Set<Role> roles, Long uid1, String firstName1, String lastName1, String phone, String username1, String email1, String password1, Set<Role> roles1) {
        this.uid = uid1;
        this.firstName = firstName1;
        this.lastName = lastName1;
        this.phone = phone;
        this.username = username1;
        this.email = email1;
        this.password = password1;
        this.roles = roles1;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Long getUId() {
        return uid;
    }

    @Override
    public void setUId(Long uid) {
        this.uid = uid;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
