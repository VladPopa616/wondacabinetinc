package com.wondacabinetinc.wondacabinetinc.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o.orderId, o.trackingNo, o.orderStatus " +
            "FROM Order o " +
            "INNER JOIN o.orderDetails od where od.orderId = :order_id")
    Optional<Order> getDetails(@Param("order_id") Integer order_id);
}
