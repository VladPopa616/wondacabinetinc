package com.wondacabinetinc.wondacabinetinc.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordReset, Long> {
    Optional<PasswordReset> findByUid(Long uid);
    Optional<PasswordReset> findByPasswordResetToken(String token);

    int deleteByEmployee(Employee employee);
}
