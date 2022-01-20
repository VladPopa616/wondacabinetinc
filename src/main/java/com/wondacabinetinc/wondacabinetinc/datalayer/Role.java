package com.wondacabinetinc.wondacabinetinc.datalayer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private EnumRoles roleName;

    public Role() {
    }

    public Role(EnumRoles roleName) {
        this.roleName = roleName;
    }

    public Role(Integer roleId, EnumRoles roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public EnumRoles getRoleName() {
        return roleName;
    }

    public void setRoleName(EnumRoles roleName) {
        this.roleName = roleName;
    }
}
