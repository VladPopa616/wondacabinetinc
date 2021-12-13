package com.wondacabinetinc.wondacabinetinc.businesslayer;


import com.wondacabinetinc.wondacabinetinc.datalayer.Order;
import com.wondacabinetinc.wondacabinetinc.datalayer.OrderRepository;
import com.wondacabinetinc.wondacabinetinc.utils.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public Optional<Order> getOrderDetails(Integer id) {
        try{
            Optional<Order> order = orderRepository.getDetails(id);
            LOG.debug("Order with Id: " + id + " has been found");
            return order;
        }
        catch(Exception e){
            throw new NotFoundException("Order with Id: " + id + " not found");
        }
    }
}
