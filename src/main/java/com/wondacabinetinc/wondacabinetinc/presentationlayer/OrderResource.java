package com.wondacabinetinc.wondacabinetinc.presentationlayer;
import com.wondacabinetinc.wondacabinetinc.businesslayer.OrderDetailsService;
import com.wondacabinetinc.wondacabinetinc.businesslayer.OrderService;
import com.wondacabinetinc.wondacabinetinc.datalayer.Order;
import com.wondacabinetinc.wondacabinetinc.datalayer.OrderDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/orders")
public class OrderResource {
    private final OrderService orderService;

    private final OrderDetailsService orderDetailsService;

    public OrderResource(OrderService orderService, OrderDetailsService orderDetailsService) {
        this.orderService = orderService;
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping
    @ResponseBody
    @CrossOrigin(origins = "*")
    public List<Order> findAllOrders(){
        List<Order> orders = new ArrayList<>();
        Iterable<Order> allOrders = orderService.getAllOrders();

        for(Order order : allOrders){
            orders.add(order);
        }

        return orders;
    }

    @GetMapping("/{orderId}")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Optional<Order> getOrderDetails(@PathVariable("orderId") int orderId){
        return orderService.getOrderDetails(orderId);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Order addNewOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    @PostMapping(value = "/{orderId}/new", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public OrderDetails addDetailsToOrder(@RequestBody OrderDetails orderDetails, @PathVariable("orderId") Integer orderId){
        return orderDetailsService.addOrderDetailsToOrder(orderDetails, orderId);
    }
}
