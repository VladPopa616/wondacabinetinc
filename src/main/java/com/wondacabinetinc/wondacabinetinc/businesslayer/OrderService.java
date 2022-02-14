package com.wondacabinetinc.wondacabinetinc.businesslayer;

import com.wondacabinetinc.wondacabinetinc.datalayer.Order;
import com.wondacabinetinc.wondacabinetinc.datalayer.OrderTrackingNoDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {

    public List<Order> getAllOrders();

    public Optional<Order> getOrderDetails(Integer id);

    public Order addOrder(Order order);

    public List<Order> getNotCancelledOrders();

    public List<Order> getCancelledOrders();

    public Order updateOrder(Integer id, Order order);

    public Order updateOrderDelivery(Integer id, String date);

    public List<Order> getOrderByEmail(String email);

    public List<Order> getCancelledByEmail(String email);

    public List<Order> getNonCancelledByEmail(String email);

    public String deleteOrder(Integer id);

    public String deleteByEmail(String email);

    public OrderTrackingNoDTO getOrderByTrackingNo(String trackingNo);
}
