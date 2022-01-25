package com.wondacabinetinc.wondacabinetinc.Mail;

public interface MailSenderService {

//    public void sendSimpleEmail(Mail mail);

    public void sendSimpleEmail(String toEmail, String body, String subject );
}
