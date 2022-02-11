package com.wondacabinetinc.wondacabinetinc.datalayer;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "resetpassword")
public class PasswordReset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long uid;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "uid")
    private Employee employee;

    @Column(nullable = false, unique = true)
    private String passwordResetToken;

    @Column(nullable = false)
    private Instant expiration;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public Instant getExpiration() {
        return expiration;
    }

    public void setExpiration(Instant expiration) {
        this.expiration = expiration;
    }
}
