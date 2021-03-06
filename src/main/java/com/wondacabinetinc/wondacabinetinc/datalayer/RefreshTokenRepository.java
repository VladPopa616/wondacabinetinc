package com.wondacabinetinc.wondacabinetinc.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUid(Long uid);
    Optional<RefreshToken> findByToken(String token);

    int deleteByEmployee(Employee employee);
}
