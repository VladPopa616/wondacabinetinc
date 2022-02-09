package com.wondacabinetinc.wondacabinetinc.Mail;

import com.wondacabinetinc.wondacabinetinc.datalayer.Employee;
import com.wondacabinetinc.wondacabinetinc.datalayer.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailSenderServiceImp implements  MailSenderService{

    @Autowired
    private JavaMailSender mailsender;

    @Override
    public void sendEmail(String toEmail, String body, String subject ){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("noreply.wondacabinetinc@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailsender.send(message);
    }

    @Override
    public void sendEmailWithAttachment(String toEmail, String body, String subject, String attachment) throws MessagingException {
        MimeMessage mimeMessage = mailsender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("noreply.wondacabinetinc@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setCc("wondacabinetinctestemail@gmail.com");
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystem
                = new FileSystemResource(new File(attachment));

        mimeMessageHelper.addAttachment(fileSystem.getFilename(),
                fileSystem);

        mailsender.send(mimeMessage);
    }

    @Override
    public String sendUpdateEmailWithAttachment(String toEmail, Order order) throws MessagingException {
        try{
            MimeMessage mimeMessage = mailsender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);

            String body = "Here is a confirmation of your updated order: " +
                    "\nOrder Name: " + order.getCabinetType() +
                    "\nTracking Number: " + order.getTrackingNo() +
                    "\nOrder Status: " + order.getOrderStatus() +
                    "\nMaterial: " + order.getMaterial() +
                    "\nHandle Type: " + order.getHandleType() +
                    "\nColor: " + order.getColor() +
                    "\nIf there are any mistakes, please contact an employee." +
                    "\nThank you for choosing Wonda Cabinet Inc.";

            String subject = "UPDATE ORDER - CABINET: " + order.getCabinetType();
            String attachment = order.getDesign();

            mimeMessageHelper.setFrom("noreply.wondacabinetinc@gmail.com");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setCc("wondacabinetinctestemail@gmail.com");
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(subject);

//            FileSystemResource fileSystem
//                    = new FileSystemResource(new File(attachment));
//
//            mimeMessageHelper.addAttachment(fileSystem.getFilename(),
//                    fileSystem);

            mailsender.send(mimeMessage);
            return "Email sent";

        }
        catch(MessagingException e) {
            throw new MessagingException("Failed to send email");
        }
    }

    @Override
    public String sendCreateEmailWithAttachment(String toEmail, Order order) throws MessagingException {
        try{
            MimeMessage mimeMessage = mailsender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);

            String body = "Here is a confirmation of your updated order: " +
                    "\nOrder Name: " + order.getCabinetType() +
                    "\nTracking Number: " + order.getTrackingNo() +
                    "\nOrder Status: " + order.getOrderStatus() +
                    "\nMaterial: " + order.getMaterial() +
                    "\nHandle Type: " + order.getHandleType() +
                    "\nColor: " + order.getColor() +
                    "\nIf there are any mistakes, please contact an employee." +
                    "\nThank you for choosing Wonda Cabinet Inc.";

            String subject = "NEW ORDER - CABINET: " + order.getCabinetType();
//            String attachment = order.getDesign();

            mimeMessageHelper.setFrom("noreply.wondacabinetinc@gmail.com");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(subject);

//            FileSystemResource fileSystem
//                    = new FileSystemResource(new File(attachment));
//
//            mimeMessageHelper.addAttachment(fileSystem.getFilename(),
//                    fileSystem);

            mailsender.send(mimeMessage);
            return "Email sent";

        }
        catch(MessagingException e) {
            throw new MessagingException("Failed to send email");
        }
    }

    @Override
    public String sendAccountCreationEmail(String toEmail, Employee employee) throws MessagingException{
        try{
            MimeMessage mimeMessage = mailsender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            String body = "Welcome to Wonda Cabinet Inc. \n" +
                    "Your account has officially been created. \n" +
                    "Here is the information we have about your account: \n" +
                    "First Name: " + employee.getFirstName() + "\n" +
                    "Last Name: " + employee.getLastName() + "\n" +
                    "Username: " + employee.getUsername() + "\n" +
                    "Email address: " + employee.getEmail() + "\n" +
                    "Phone Number: " + employee.getPhone() + "\n" +
                    "If you think there's any mistake, please contact an employee. \n" +
                    "Thank you for choosing Wonda Cabinet Inc.";

            String subject = "ACCOUNT CREATION: " + employee.getUsername();

            mimeMessageHelper.setFrom("noreply.wondacabinetinc@gmail.com");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(subject);

            mailsender.send(mimeMessage);
            return "Email sent";
        }
        catch(Exception e){
            throw new MessagingException("Failed to send email");
        }
    }
}
