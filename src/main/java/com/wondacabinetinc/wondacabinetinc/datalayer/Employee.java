package com.wondacabinetinc.wondacabinetinc.datalayer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@Entity
@Table(name = "employees",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Employee extends EmployeeDTO{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UniqueElements(groups = Employee.class)
    @Column(name = "uid")
    private Long uid;

    @NotBlank
    @Size(max = 50)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(max = 50)
    @Column(name = "email")
    @Email
    private String email;

    @NotBlank
    @Size(max = 50)
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "uid"))
    private Collection<Role> roles = new ArrayList<>();

    public Employee() {
    }

    public Employee(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Employee(Integer accountId, Long uid, String username, String email, String password) {
        this.id = accountId;
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Employee(Long uid,String username, String email, String password, Collection<Role> roles) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Employee(Integer accountId,  Long uid, String username, String email, String password, Collection<Role> roles) {
        this.id = accountId;
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer accountId) {
        this.id = accountId;
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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        Random rand = new Random();
        long l = rand.nextInt(99999);
        this.uid = l;
    }
}
