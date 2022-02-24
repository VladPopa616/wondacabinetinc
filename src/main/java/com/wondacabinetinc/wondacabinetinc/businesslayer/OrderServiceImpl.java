package com.wondacabinetinc.wondacabinetinc.businesslayer;


import com.wondacabinetinc.wondacabinetinc.Mail.MailSenderService;
import com.wondacabinetinc.wondacabinetinc.datalayer.Order;
import com.wondacabinetinc.wondacabinetinc.datalayer.OrderRepository;
import com.wondacabinetinc.wondacabinetinc.datalayer.OrderTrackingNoDTO;
import com.wondacabinetinc.wondacabinetinc.utils.exceptions.InvalidEmailException;
import com.wondacabinetinc.wondacabinetinc.utils.exceptions.InvalidInputException;
import com.wondacabinetinc.wondacabinetinc.utils.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import static com.wondacabinetinc.wondacabinetinc.datalayer.EmailValidator.validateEmail;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    private final MailSenderService mailService;

    private final OrderMapper mapper;

    public OrderServiceImpl(OrderRepository orderRepository, MailSenderService mailService, OrderMapper mapper) {
        this.orderRepository = orderRepository;

        this.mailService = mailService;
        this.mapper = mapper;
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
            if (order.get() == null){
                throw new NotFoundException("Order with Id: " + id + " not found");
            }
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
            Random rand = new Random();
            order.setOrderId(rand.nextInt());

            order.setOrderStatus("Awaiting Order");
            String uuid = UUID.randomUUID().toString();
            order.setTrackingNo(uuid);
            Date date = new Date();
            order.setOrderDate(new Timestamp(date.getTime()));
//            order.setOrderDate();
//            order.setDesign("https://s2.q4cdn.com/498544986/files/doc_downloads/test.pdf");
            LOG.debug("Order Added: order with ID {} saved", order.getOrderId());
            boolean validEmail = validateEmail(order.getEmail());
            if (validEmail){
                mailService.sendCreateEmailWithAttachment(order.getEmail(), order);
            }


            return orderRepository.save(order);
        }
        catch(DuplicateKeyException e){
            throw new InvalidInputException("Duplicate Key, orderId: " + order.getOrderId());
        }
        catch(NullPointerException e){
            throw new NotFoundException("Error adding Order, missing inputs");
        } catch (MessagingException e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to send mail");
        }
    }

    @Override
    public Order updateOrder(Integer id, Order order) {
        try{
            Optional<Order> orderOpt = orderRepository.findById(id);
            Order foundOrder = orderOpt.get();
            if (order.getOrderStatus() != null && !order.getOrderStatus().isEmpty()){
                foundOrder.setOrderStatus(order.getOrderStatus());
            }
//            foundOrder.setTrackingNo(order.getTrackingNo());
            if (order.getDesign() != null && !order.getDesign().isEmpty()) {
                foundOrder.setDesign(order.getDesign());
            }
            if (order.getCabinetType() != null && !order.getCabinetType().isEmpty()) {
                foundOrder.setCabinetType(order.getCabinetType());
            }
            if (order.getMaterial() != null && !order.getMaterial().isEmpty()) {
                foundOrder.setMaterial(order.getMaterial());
            }
            if (order.getColor() != null && !order.getColor().isEmpty()) {
                foundOrder.setColor(order.getColor());
            }
            if (order.getHandleType() != null && !order.getHandleType().isEmpty()) {
                foundOrder.setHandleType(order.getHandleType());
            }
            if (order.getAddress() != null && !order.getAddress().isEmpty()){
                foundOrder.setAddress(order.getAddress());
            }
            if (order.getCity() != null && !order.getCity().isEmpty()){
                foundOrder.setCity(order.getCity());
            }
            if (order.getDeliveryDate() != null){
                foundOrder.setDeliveryDate(order.getDeliveryDate());
            }
            LOG.debug("Order with Id {} updated", id);
            mailService.sendUpdateEmailWithAttachment(foundOrder.getEmail(), foundOrder);
            return orderRepository.save(foundOrder);

        }
        catch(Exception e)
        {
            LOG.debug(e.getMessage());
            throw new NotFoundException("Update Order with Id " + id + " failed");
        }
    }

    @Override
    public Order updateOrderDelivery(Integer id, String date ) {
        try{
            Optional<Order> orderOpt = orderRepository.findById(id);
            Order foundOrder = orderOpt.get();

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateParsed = df.parse(date);
//            String newDateString = df.format(startDate);
            if (date != null){
                foundOrder.setDeliveryDate(dateParsed);
            }

            LOG.debug("Order with Id {} updated", id);
            mailService.sendUpdateEmailWithAttachment(foundOrder.getEmail(), foundOrder);
            return orderRepository.save(foundOrder);

        }
        catch(Exception e)
        {
            LOG.debug(e.getMessage());
            throw new NotFoundException("Update Order with Id " + id + " failed");
        }
    }

    @Override
    public List<Order> getOrderByEmail(String email) {
        return orderRepository.findByEmail(email);
    }

    @Override
    public List<Order> getCancelledByEmail(String email) {
        return orderRepository.findByEmailAndOrderStatusIs(email, "Cancelled");
    }

    @Override
    public List<Order> getNonCancelledByEmail(String email) {
        return orderRepository.findByEmailAndOrderStatusIsNot(email, "Cancelled");
    }

    @Override
    public String deleteOrder(Integer id){
        try{
            orderRepository.deleteById(id);
            LOG.debug("Order with Id {} deleted", id);
            return "Order with ID: " + id + " successfully deleted.";
        }
        catch(Exception e){
            LOG.debug("No orders with orderId " + id );
            throw new NotFoundException("Delete order with ID: " + id + " not found");
        }

    }

    @Override
    public String deleteByEmail(String email) {
        try{
            long numberOfDeleteRecords = orderRepository.deleteByEmail(email);
            LOG.debug("Deleted {} records with email {}", numberOfDeleteRecords, email );
            return "Deleted " + numberOfDeleteRecords + " records with email: " + email;
        }
        catch(Exception e){
            LOG.debug("No orders with email " + email + " to be deleted");
            throw new NotFoundException("No orders with email: " + email);
        }
    }

    @Override
    public OrderTrackingNoDTO getOrderByTrackingNo(String trackingNo) {
        try{
            Optional<Order> foundOrder = orderRepository.findByTrackingNoIs(trackingNo);
            if (foundOrder == null){
                throw new NotFoundException("Order with trackingNo: " + trackingNo + " not found");
            }
            LOG.debug("Order found with orderId: {}", trackingNo);
            OrderTrackingNoDTO orderTrackingNoDTO = mapper.entityToModel(foundOrder.get());
            return orderTrackingNoDTO;
        }
        catch(Exception e) {
            throw new NotFoundException("Order with trackingNo: " + trackingNo + " not found");
        }
    }

}
