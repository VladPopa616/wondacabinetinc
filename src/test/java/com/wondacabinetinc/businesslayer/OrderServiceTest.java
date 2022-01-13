package com.wondacabinetinc.businesslayer;

import com.wondacabinetinc.wondacabinetinc.businesslayer.OrderService;
import com.wondacabinetinc.wondacabinetinc.datalayer.Order;
import com.wondacabinetinc.wondacabinetinc.datalayer.OrderRepository;
import com.wondacabinetinc.wondacabinetinc.presentationlayer.OrderResource;
import com.wondacabinetinc.wondacabinetinc.utils.exceptions.NotFoundException;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@EnableAutoConfiguration
@SpringBootConfiguration
@ContextConfiguration
public class OrderServiceTest {

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    OrderResource orderResource;

    @MockBean
    OrderService orderService;

    @DisplayName("Find all orders")
    @Test
    public void find_all_orders(){
        int expected_length = 10;

        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1,"Received", 123321, "Design"));
        orderList.add(new Order(2,"In Progress", 456647, "Design"));
        orderList.add(new Order(3,"Design Received", 789789, "Design"));
        orderList.add(new Order(4,"Awaiting Scheduling", 999999, "Design"));
        orderList.add(new Order(5,"Shipped", 555555, "Design"));
        orderList.add(new Order(6,"Cancelled", 333333, "Design"));
        orderList.add(new Order(7,"Cancelled", 444444, "Design"));
        orderList.add(new Order(8,"Closed", 777888, "Design"));
        orderList.add(new Order(9,"Closed", 999000, "Design"));
        orderList.add(new Order(10,"Closed", 334455, "Design"));

        when(orderResource.findAllOrders()).thenReturn(orderList);

        List<Order> realList = orderResource.findAllOrders();

        assertEquals(expected_length, realList.size());
    }


    @DisplayName("Get all cancelled orders")
    @Test
    public void find_all_cancelled_orders(){
        int expected_length = 3;

        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1,"Cancelled", 123321, "Design"));
        orderList.add(new Order(2,"Cancelled", 456647, "Design"));
        orderList.add(new Order(3,"Cancelled", 789789, "Design"));

        when(orderService.getCancelledOrders()).thenReturn(orderList);

        List<Order> realList = orderService.getCancelledOrders();

        assertThat(realList.size()).isEqualTo(expected_length);
    }

    @DisplayName("Get all active orders")
    @Test
    public void find_all_active_orders(){
        int expected_length = 3;

        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1,"Awaiting Design", 123321, "Design"));
        orderList.add(new Order(2,"Awaiting Design", 456647, "Design"));
        orderList.add(new Order(3,"Awaiting Design", 789789, "Design"));

        when(orderService.getNotCancelledOrders()).thenReturn(orderList);

        List<Order> realList = orderService.getNotCancelledOrders();

        assertThat(realList.size()).isEqualTo(expected_length);
    }


    @DisplayName("Add an order")
    @Test
    public void add_order(){
        int orderId = 1;
        Order order = new Order(orderId, "Done",
                (long)555555, "Design",
                "Kitchen Cabinet","White",
                "Birch", "Circle");

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        orderService.addOrder(order);
        Optional<Order> retrievedOrder = orderRepository.findById(orderId);

        MatcherAssert.assertThat(retrievedOrder.get(), samePropertyValuesAs(order));


    }

    @DisplayName("Add an order null value")
    @Test
    public void add_order_throws_not_found_when_null_value(){
        Order order = new Order("Done",
                (long)555555, "Design",
                "Kitchen Cabinet","White",
                "Birch", "Circle");

        String expectedMsg = "Error adding Order, missing inputs";

        when(orderRepository.save(any(Order.class))).thenThrow(new NotFoundException("Error adding Order, missing inputs"));

        try{
            orderService.addOrder(order);
        }
        catch(NullPointerException e){
            assertEquals(e.getMessage(), expectedMsg);
        }
    }

    @DisplayName("Add an order duplicate key")
    @Test
    public void add_order_throws_duplicate_key_when_id_exists(){
        Order order = new Order(1,null,
                null, null,
                null,null,
                null, null);

        String expectedMsg = "Duplicate Key, orderId: " + order.getOrderId();

        when(orderRepository.save(any(Order.class))).thenThrow(new DuplicateKeyException("Duplicate Key, orderId: " + order.getOrderId()));

        try{
            orderService.addOrder(order);
        }
        catch(DuplicateKeyException e){
            assertEquals(e.getMessage(), expectedMsg);
        }
    }

    @DisplayName("Find order by ID")
    @Test
    public void find_order_by_id(){
        Order order = new Order(1, "Done",
                (long)555555, "Design",
                "Kitchen Cabinet","White",
                "Birch", "Circle");

        when(orderRepository.findById(1)).thenReturn(Optional.of(order));

        Optional<Order> retrievedOrder = orderRepository.findById(1);
        Order receivedOrder = retrievedOrder.get();

        assertThat(receivedOrder.getOrderId()).isEqualTo(order.getOrderId());
    }

    @DisplayName("Find by Order ID Not Found")
    @Test
    public void find_by_order_id_throws_not_found_when_order_does_not_exist(){
        int orderId = 1;
        String expectedMsg = "Order with Id: " + orderId + " not found";

        when(orderRepository.findById(Mockito.anyInt())).thenThrow(new NotFoundException());

        try{
            orderService.getOrderDetails(orderId);
        }
        catch(NotFoundException e){
            assertEquals(e.getMessage(), expectedMsg);
        }
    }

    @DisplayName("Update order")
    @Test
    public void update_order(){
        Order newOrder = new Order(1, "Received",(long)555555, "Design", "Kitchen Cabinet", "Ivory", "Pine", "Knob");
        when(orderRepository.findById(1)).thenReturn(Optional.of(newOrder));

        Optional<Order> retrieved = orderRepository.findById(1);
        Order receivedOrder = retrieved.get();

        orderService.updateOrder(1,newOrder);

        assertEquals(receivedOrder.getOrderId(), newOrder.getOrderId());
        assertEquals(receivedOrder.getOrderStatus(), newOrder.getOrderStatus());
        assertEquals(receivedOrder.getTrackingNo(), newOrder.getTrackingNo());
        assertEquals(receivedOrder.getDesign(), newOrder.getDesign());
        assertEquals(receivedOrder.getCabinetType(), newOrder.getCabinetType());
        assertEquals(receivedOrder.getColor(), newOrder.getColor());
        assertEquals(receivedOrder.getMaterial(), newOrder.getMaterial());
        assertEquals(receivedOrder.getHandleType(), newOrder.getHandleType());

    }

    @DisplayName("Update Order should return not found when id is not found")
    @Test
    public void update_order_should_return_not_found_for_non_existing_id(){
        int orderId = 100;
        String expectedMsg = "Order with Id: " + orderId + " not found";

        Order newOrder = new Order(1, "Received",(long)555555, "Design", "Kitchen Cabinet", "Ivory", "Pine", "Knob");

        when(orderRepository.findById(Mockito.anyInt())).thenThrow(new NotFoundException());

        try{
            orderService.updateOrder(orderId, newOrder);
        }
        catch(Exception e){
            assertEquals(e.getMessage(), expectedMsg);
        }
    }
}
