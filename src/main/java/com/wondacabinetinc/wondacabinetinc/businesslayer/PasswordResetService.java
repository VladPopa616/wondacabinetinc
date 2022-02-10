package com.wondacabinetinc.wondacabinetinc.businesslayer;

import com.wondacabinetinc.wondacabinetinc.datalayer.EmployeeRepository;
import com.wondacabinetinc.wondacabinetinc.datalayer.PasswordReset;
import com.wondacabinetinc.wondacabinetinc.datalayer.PasswordResetRepository;
import com.wondacabinetinc.wondacabinetinc.utils.exceptions.TokenRefreshException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Value("@{jwt.passwordtokenexpiration}")
    private Long passwordTokenExpiration;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordResetRepository passwordResetRepository;

    public Optional<PasswordReset> findByToken(String token) {return passwordResetRepository.findByPasswordResetToken(token);}

    public PasswordReset createPasswordResetToken(Long uid){
        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setEmployee(employeeRepository.findByUid(uid).get());
        passwordReset.setExpiration(Instant.now().plusMillis(passwordTokenExpiration));
        passwordReset.setPasswordResetToken(UUID.randomUUID().toString());
        passwordReset = passwordResetRepository.save(passwordReset);
        return passwordReset;
    }

    public PasswordReset verifyExpiration(PasswordReset token){
        if(token.getExpiration().compareTo(Instant.now()) < 0){
            passwordResetRepository.delete(token);
            throw new TokenRefreshException(token.getPasswordResetToken(), "Password reset token was expired. Please make a new request");
        }
        return token;
    }

    @Transactional
    public int deleteByUid(Long uid){
        return passwordResetRepository.deleteByEmployee(employeeRepository.findByUid(uid).get());
    }
}
