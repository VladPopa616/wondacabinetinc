package com.wondacabinetinc.datalayer;


import com.wondacabinetinc.wondacabinetinc.datalayer.EmailValidator;
import com.wondacabinetinc.wondacabinetinc.utils.exceptions.InvalidEmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailValidationTest {

    String validEmail = "name@domain.com";
    String invalidEmail = "namedomain.com";

    @Test
    @DisplayName("Email validation constructor tests")
    void ValidateEmailConstructorTest(){
        EmailValidator validator = new EmailValidator();
        assertEquals(validator.getClass(), EmailValidator.class);

    }

    @Test
    @DisplayName("Email validation returns true")
    void ValidateEmail(){
        EmailValidator validator = new EmailValidator();
        boolean validatedEmail = validator.validateEmail(validEmail);
        assertTrue(validatedEmail);

    }

    @Test
    @DisplayName("Email validation throws invalid email exception")
    void ValidateEmailShouldThrowInvalidEmailException(){
        EmailValidator validator = new EmailValidator();
        assertThrows(InvalidEmailException.class,  () -> {
            validator.validateEmail(invalidEmail);
        });

    }
}
