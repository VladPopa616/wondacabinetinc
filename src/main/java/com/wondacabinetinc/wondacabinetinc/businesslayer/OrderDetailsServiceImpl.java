package com.wondacabinetinc.wondacabinetinc.businesslayer;

import com.wondacabinetinc.wondacabinetinc.datalayer.Order;
import com.wondacabinetinc.wondacabinetinc.datalayer.OrderDetails;
import com.wondacabinetinc.wondacabinetinc.datalayer.OrderDetailsRepository;
import com.wondacabinetinc.wondacabinetinc.utils.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

    private static final Logger LOG = LoggerFactory.getLogger(OrderDetailsServiceImpl.class);

    private OrderDetailsRepository orderDetailsRepository;

    private OrderService orderService;

    @Override
    public OrderDetails addOrderDetailsToOrder(OrderDetails orderDetails, int orderId) {
        try{
            orderDetails = new OrderDetails();
            Optional<Order> orderOpt = orderService.getOrderDetails(orderId);
            Order order = orderOpt.get();
            order.setOrderDetails(orderDetails);
            return orderDetailsRepository.save(orderDetails);
        }
        catch(Exception e){
            throw new NotFoundException("order with ID: " + orderId + " is not found");
        }
    }
}
