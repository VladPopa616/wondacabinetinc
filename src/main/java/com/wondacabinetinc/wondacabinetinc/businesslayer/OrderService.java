package com.wondacabinetinc.wondacabinetinc.businesslayer;

import com.wondacabinetinc.wondacabinetinc.datalayer.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public List<Order> getAllOrders();

    public Optional<Order> getOrderDetails(Integer id);

}
