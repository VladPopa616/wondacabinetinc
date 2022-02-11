package com.wondacabinetinc.wondacabinetinc.jwt;

import javax.validation.constraints.NotBlank;

public class PasswordResetRequest {

    @NotBlank
    private String passwordToken;

    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPasswordToken() {
        return passwordToken;
    }

    public void setPasswordToken(String passwordToken) {
        this.passwordToken = passwordToken;
    }
}
