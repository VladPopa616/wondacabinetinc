package com.wondacabinetinc.wondacabinetinc.Mail;

import javax.mail.MessagingException;

public interface MailSenderService {

//    public void sendSimpleEmail(Mail mail);

    public void sendEmail(String toEmail, String body, String subject );

    public void sendEmailWithAttachment(String toEmail, String body, String subject, String attachment ) throws MessagingException;
}
