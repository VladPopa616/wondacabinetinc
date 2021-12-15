package com.wondacabinetinc.wondacabinetinc.businesslayer;

import com.wondacabinetinc.wondacabinetinc.datalayer.Order;
import com.wondacabinetinc.wondacabinetinc.datalayer.OrderDetails;
import com.wondacabinetinc.wondacabinetinc.datalayer.OrderDetailsRepository;
import com.wondacabinetinc.wondacabinetinc.datalayer.OrderRepository;
import com.wondacabinetinc.wondacabinetinc.utils.exceptions.InvalidInputException;
import com.wondacabinetinc.wondacabinetinc.utils.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

    private static final Logger LOG = LoggerFactory.getLogger(OrderDetailsServiceImpl.class);

    private final OrderDetailsRepository orderDetailsRepository;

    private final OrderRepository orderRepository;

    public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository, OrderRepository orderRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDetails addOrderDetailsToOrder(OrderDetails orderDetails, Integer orderId) {
        try{
            orderDetails = new OrderDetails();
            Optional<Order> orderOpt = orderRepository.findById(orderId);
            Order order = orderOpt.get();
            order.setOrderDetails(orderDetails);
            return orderDetailsRepository.save(orderDetails);
        }
        catch(DuplicateKeyException e){
            throw new InvalidInputException("Duplicate Key found at Order ID: " + orderId);
        }
        catch(Exception e){
            throw new NotFoundException("order with ID: " + orderId + " is not found");
        }
    }
}
