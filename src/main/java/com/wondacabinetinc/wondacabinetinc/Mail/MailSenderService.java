package com.wondacabinetinc.wondacabinetinc.Mail;

import com.wondacabinetinc.wondacabinetinc.datalayer.Employee;
import com.wondacabinetinc.wondacabinetinc.datalayer.Order;

import javax.mail.MessagingException;

public interface MailSenderService {

//    public void sendSimpleEmail(Mail mail);

    public void sendEmail(String toEmail, String body, String subject );

    public void sendEmailWithAttachment(String toEmail, String body, String subject, String attachment ) throws MessagingException;

    public String sendUpdateEmailWithAttachment(String toEmail, Order order) throws MessagingException;

    public String sendCreateEmailWithAttachment(String toEmail, Order order) throws MessagingException;

    public String sendAccountCreationEmail(String toEmail, Employee employee) throws MessagingException;
}
