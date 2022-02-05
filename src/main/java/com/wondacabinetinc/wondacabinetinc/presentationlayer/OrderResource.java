package com.wondacabinetinc.wondacabinetinc.presentationlayer;
import com.wondacabinetinc.wondacabinetinc.Mail.MailSenderService;
import com.wondacabinetinc.wondacabinetinc.businesslayer.OrderService;
import com.wondacabinetinc.wondacabinetinc.datalayer.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/orders")
public class OrderResource {
    private final OrderService orderService;

    private final MailSenderService mailService;



    public OrderResource(OrderService orderService, MailSenderService mailService) {
        this.orderService = orderService;
        this.mailService = mailService;
    }

    @GetMapping
    @ResponseBody
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE')")
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
    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE', 'ROLE_CUSTOMER')")
    public Optional<Order> getOrderDetails(@PathVariable("orderId") int orderId){
        return orderService.getOrderDetails(orderId);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE', 'ROLE_CUSTOMER')")
    public Order addNewOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }


    @PutMapping("/{orderId}")
    @ResponseBody
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public Order updateOrder(@PathVariable("orderId") int orderId, @RequestBody Order order){

        return orderService.updateOrder(orderId, order);
    }

    @GetMapping("/email/{email}")
    @ResponseBody
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE', 'ROLE_CUSTOMER')")
    public List<Order> getOrdersByEmail(@PathVariable String email){
        return orderService.getOrderByEmail(email);
    }

    @GetMapping("/email/cancelled/{email}")
    @ResponseBody
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE', 'ROLE_CUSTOMER')")
    public List<Order> getCancelledByEmail(@PathVariable String email){
        return orderService.getCancelledByEmail(email);
    }

    @GetMapping("/email/active/{email}")
    @ResponseBody
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE', 'ROLE_CUSTOMER')")
    public List<Order> getNonCancelledByEmail(@PathVariable String email){
        return orderService.getNonCancelledByEmail(email);
    }

    @DeleteMapping("/delete/{orderId}")
    @ResponseBody
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE')")
    public String deleteOrder(@PathVariable Integer orderId){
        return orderService.deleteOrder(orderId);
    }

//    @PutMapping("/cancel/{orderId}")
//    @ResponseBody
//    @CrossOrigin(origins = "*")
//    public Order updateOrderStatusToCancel(@PathVariable("orderId") int orderId){
//        return orderService.updateOrderStatusToCancelled(orderId);
//    }


}
