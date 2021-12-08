package com.wondacabinetinc.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o from Order o ORDER BY o.orderId asc")
    List<Order> getAllOrders();
}
