package com.wondacabinetinc.wondacabinetinc.jwt;

public class PasswordTokenGenerationResponse {
    private String passwordToken;

    public PasswordTokenGenerationResponse(String passwordToken) {
        this.passwordToken = passwordToken;
    }

    public String getPasswordToken() {
        return passwordToken;
    }

    public void setPasswordToken(String passwordToken) {
        this.passwordToken = passwordToken;
    }
}
