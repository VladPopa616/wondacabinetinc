package com.wondacabinetinc.wondacabinetinc.Mail;

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
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystem
                = new FileSystemResource(new File(attachment));

        mimeMessageHelper.addAttachment(fileSystem.getFilename(),
                fileSystem);

        mailsender.send(mimeMessage);
    }

    public String sendUpdateEmailWithAttachment(String toEmail, Order order) throws MessagingException {
        try{
            MimeMessage mimeMessage = mailsender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);

            String body = "";
            String subject = "UPDATE - CABINET: " + order.getCabinetType();
            String attachment = order.getDesign();

            mimeMessageHelper.setFrom("noreply.wondacabinetinc@gmail.com");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(subject);

            FileSystemResource fileSystem
                    = new FileSystemResource(new File(attachment));

            mimeMessageHelper.addAttachment(fileSystem.getFilename(),
                    fileSystem);

            mailsender.send(mimeMessage);
            return "Email sent";

        }
        catch(MessagingException e) {
            throw new MessagingException();
        }
    }
}
