package com.wondacabinetinc.wondacabinetinc.presentationlayer;
import com.wondacabinetinc.wondacabinetinc.businesslayer.OrderService;
import com.wondacabinetinc.wondacabinetinc.datalayer.Order;
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



    public OrderResource(OrderService orderService) {
        this.orderService = orderService;

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

    @GetMapping("/active")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public List<Order> findNotCancelled(){
        List<Order> orders = new ArrayList<>();
        Iterable<Order> allOrders = orderService.getNotCancelledOrders();

        for(Order order : allOrders){
            orders.add(order);
        }

        return orders;
    }

    @GetMapping("/cancelled")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public List<Order> findCancelled(){
        List<Order> orders = new ArrayList<>();
        Iterable<Order> allOrders = orderService.getCancelledOrders();

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


    @PutMapping("/{orderId}")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Order updateOrder(@PathVariable("orderId") int orderId, @RequestBody Order order){
        return orderService.updateOrder(orderId, order);
    }


}
