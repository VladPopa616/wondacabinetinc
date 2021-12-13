package com.wondacabinetinc.wondacabinetinc.datalayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @OneToOne
    @JoinColumn(name="order_id")
    private OrderDetails orderDetails;

    @Column(name="orderStatus")
    private String orderStatus;

    @Column(name="trackingNo")
    private Long trackingNo;

    @Column(name="design")
    private String design;

    public Order(int orderId, String orderStatus, long trackingNo, String design) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.trackingNo = trackingNo;
        this.design = design;
    }

    public Order(String orderStatus, long trackingNo, String design) {
        this.orderStatus = orderStatus;
        this.trackingNo = trackingNo;
        this.design = design;
    }

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(long trackingNo) {
        this.trackingNo = trackingNo;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
}
