package com.wondacabinetinc.datalayer;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name="orderStatus")
    private String orderStatus;

    @Column(name="trackingNo")
    private String trackingNo;

    @Column(name="design")
    private String design;

    public Order(int orderId, String orderStatus, String trackingNo, String design) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.trackingNo = trackingNo;
        this.design = design;
    }

    public Order(String orderStatus, String trackingNo, String design) {
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

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }
}
