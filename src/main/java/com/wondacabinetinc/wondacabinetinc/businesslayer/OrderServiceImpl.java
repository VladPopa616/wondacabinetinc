package com.wondacabinetinc.wondacabinetinc.businesslayer;


import com.wondacabinetinc.wondacabinetinc.datalayer.Order;
import com.wondacabinetinc.wondacabinetinc.datalayer.OrderRepository;
import com.wondacabinetinc.wondacabinetinc.utils.exceptions.InvalidInputException;
import com.wondacabinetinc.wondacabinetinc.utils.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;



    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    @Override
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    @Override
    public List<Order> getNotCancelledOrders() {
        return orderRepository.findByOrderStatusIsNot("Cancelled");
    }

    @Override
    public List<Order> getCancelledOrders() {
        return orderRepository.findByOrderStatusIs("Cancelled");
    }

    @Override
    public Optional<Order> getOrderDetails(Integer id) {
        try{
            Optional<Order> order = orderRepository.findById(id);
            LOG.debug("Order with Id: " + id + " has been found");
            return order;
        }
        catch(Exception e){
            throw new NotFoundException("Order with Id: " + id + " not found");
        }
    }


    @Override
    public Order addOrder(Order order) {
        try{
            LOG.debug("Order Added: order with ID {} saved", order.getOrderId());
            return orderRepository.save(order);
        }
        catch(DuplicateKeyException e){
            throw new InvalidInputException("Duplicate Key, orderId: " + order.getOrderId());
        }
        catch(NullPointerException e){
            throw new NotFoundException("Error adding Order, missing inputs");
        }
    }

    @Override
    public Order updateOrder(Integer id, Order order) {
        try{
            Optional<Order> orderOpt = orderRepository.findById(id);
            Order foundOrder = orderOpt.get();
            foundOrder.setOrderStatus(order.getOrderStatus());
            foundOrder.setTrackingNo(order.getTrackingNo());
            foundOrder.setDesign(order.getDesign());
            foundOrder.setCabinetType(order.getCabinetType());
            foundOrder.setMaterial(order.getMaterial());
            foundOrder.setColor(order.getColor());
            foundOrder.setHandleType(order.getHandleType());

            LOG.debug("Order with Id {} updated", id);
            return orderRepository.save(foundOrder);

        }
        catch(Exception e)
        {
            LOG.debug(e.getMessage());
            throw new NotFoundException("Update Order with Id " + id + " failed");
        }
    }
}
