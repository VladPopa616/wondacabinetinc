package com.wondacabinetinc.wondacabinetinc.datalayer;

import com.wondacabinetinc.wondacabinetinc.utils.exceptions.InvalidEmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class EmailValidator {

    private static final Logger LOG = LoggerFactory.getLogger(EmailValidator.class);

    public static boolean validateEmail(String emailAddress) {
        boolean match;
//        String pattern1 = "^(.+)@(\\S+)$";
        String pattern1 = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        match = Pattern.compile(pattern1)
                    .matcher(emailAddress)
                    .matches();

        if (match){
            LOG.debug("The email is valid");
        }
        else{
            throw new InvalidEmailException("The email: " + emailAddress + " is invalid.");
        }
        return match;
    }
}
