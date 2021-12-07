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

    private String orderStatus;

    private String trackingNo;

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
}
