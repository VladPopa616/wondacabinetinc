package com.wondacabinetinc.wondacabinetinc.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByOrderStatusIsNot(String status);

    List<Order> findByOrderStatusIs(String cancelled);

    List<Order> findByEmail(String email);

    List<Order> findByEmailAndOrderStatusIsNot(String email, String status);

    List<Order> findByEmailAndOrderStatusIs(String email, String status);

    Optional<Order> findByTrackingNoIs(String trackingNo);

    @Transactional
    long deleteByEmail(String email);


}
