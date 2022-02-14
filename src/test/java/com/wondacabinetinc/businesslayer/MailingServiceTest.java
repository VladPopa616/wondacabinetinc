package com.wondacabinetinc.businesslayer;

import com.wondacabinetinc.wondacabinetinc.Mail.MailSenderService;
import com.wondacabinetinc.wondacabinetinc.datalayer.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.mail.MessagingException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@EnableAutoConfiguration
@ContextConfiguration
public class MailingServiceTest {

    @MockBean
    MailSenderService mailSenderService;

    Date date = new Date();
    Order order = new Order(1, "Done", UUID.randomUUID().toString(), "Design", "Kitchen Cabinet","White", "123 Address", "City", new Timestamp(date.getTime()), new Timestamp(date.getTime()), "Material", "Handle", "vpopa18@gmail.com");

    @BeforeEach
    void setUp() throws MessagingException {
        when(mailSenderService.sendUpdateEmailWithAttachment("test@test.com", order)).thenReturn("Email Sent");
        when(mailSenderService.sendCreateEmailWithAttachment("test@test.com", order)).thenReturn("Email Sent");
    }

    @Test
    @DisplayName("Send Update Email")
    void send_update_email() throws MessagingException {
        String result = mailSenderService.sendUpdateEmailWithAttachment("test@test.com", order);
        assertEquals(result, "Email Sent");
    }

    @Test
    @DisplayName("Send Create Email")
    void send_create_email() throws MessagingException {
        String result = mailSenderService.sendCreateEmailWithAttachment("test@test.com", order);
        assertEquals(result, "Email Sent");
    }
}
