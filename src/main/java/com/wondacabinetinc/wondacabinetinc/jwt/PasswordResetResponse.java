package com.wondacabinetinc.wondacabinetinc.jwt;

public class PasswordResetResponse {

    private String responseMessage;

    private String newPassword;

    public PasswordResetResponse(String responseMessage, String newPassword) {
        this.responseMessage = responseMessage;
        this.newPassword = newPassword;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
