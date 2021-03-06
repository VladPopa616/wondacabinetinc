package com.wondacabinetinc.wondacabinetinc.presentationlayer;
import com.wondacabinetinc.wondacabinetinc.Mail.CancellationEmailRequest;
import com.wondacabinetinc.wondacabinetinc.Mail.MailSenderService;
import com.wondacabinetinc.wondacabinetinc.Mail.UpdateEmailRequest;
import com.wondacabinetinc.wondacabinetinc.businesslayer.OrderService;
import com.wondacabinetinc.wondacabinetinc.businesslayer.OrderServiceImpl;
import com.wondacabinetinc.wondacabinetinc.datalayer.Order;
import com.wondacabinetinc.wondacabinetinc.datalayer.OrderTrackingNoDTO;
import com.wondacabinetinc.wondacabinetinc.jwt.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import javax.validation.Valid;
import java.util.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/orders")
public class OrderResource {
    private final OrderService orderService;

    private final MailSenderService mailService;

    private static final Logger LOG = LoggerFactory.getLogger(OrderResource.class);

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

    @PutMapping("/delivery/{orderId}")
    @ResponseBody
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public Order updateOrderDelivery(@PathVariable("orderId") int orderId, @RequestBody String date){
        return orderService.updateOrderDelivery(orderId, date);
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

    @DeleteMapping("/delete/email/{email}")
    @ResponseBody
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE')")
    public String deleteOrderByEmail(@PathVariable String email){
        return orderService.deleteByEmail(email);
    }

    @GetMapping("/track/{trackingNo}")
    @ResponseBody
    @CrossOrigin(origins = "*")
//    @PreAuthorize("hasAnyAuthority()")
    public OrderTrackingNoDTO getByTrackingNumber(@PathVariable String trackingNo){
        return orderService.getOrderByTrackingNo(trackingNo);
    }

    @PostMapping("/updaterequest")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> sendUpdateRequest(@Valid @RequestBody UpdateEmailRequest request){
       try{
           mailService.sendUpdateRequestEmail("noreply.wondacabinetinc@gmail.com", request);
           return ResponseEntity.ok(new MessageResponse("Email sent sucessfully"));
       }
       catch(Exception e){
           return ResponseEntity.status(400).body(new MessageResponse("Failed to send email due to: " + e.getMessage()));
       }
    }

    @PostMapping("/cancelrequest")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> sendCancelRequest(@Valid @RequestBody CancellationEmailRequest request){
        try{
            mailService.sendCancelRequestEmail("noreply.wondacabinetinc@gmail.com", request);
            return ResponseEntity.ok(new MessageResponse("Email sent sucessfully"));
        }
        catch(Exception e){
            return ResponseEntity.status(400).body(new MessageResponse("Failed to send email due to: " + e.getMessage()));
        }
    }

//    @PutMapping("/cancel/{orderId}")
//    @ResponseBody
//    @CrossOrigin(origins = "*")
//    public Order updateOrderStatusToCancel(@PathVariable("orderId") int orderId){
//        return orderService.updateOrderStatusToCancelled(orderId);
//    }


}
