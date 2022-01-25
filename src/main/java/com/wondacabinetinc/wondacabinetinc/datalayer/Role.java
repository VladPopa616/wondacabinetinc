package com.wondacabinetinc.wondacabinetinc.datalayer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity
@Table(name = "roles")
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EnumRoles roleName;

    public Role() {
    }

    public Role(EnumRoles roleName) {
        this.roleName = roleName;
    }

    public Role(Integer roleId, EnumRoles roleName) {
        this.id = roleId;
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return id;
    }

    public void setRoleId(Integer roleId) {
        this.id = roleId;
    }

    public EnumRoles getRoleName() {
        return roleName;
    }

    public void setRoleName(EnumRoles roleName) {
        this.roleName = roleName;
    }
}
