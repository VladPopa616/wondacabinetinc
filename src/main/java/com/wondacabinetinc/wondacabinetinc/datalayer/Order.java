package com.wondacabinetinc.wondacabinetinc.datalayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "orders")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Integer orderId;

    @Column(name="orderStatus")
    private String orderStatus;

    @Column(name="trackingNo")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Digits(integer = 6, fraction = 0)
    private Long trackingNo;

    @Column(name="design")
    private String design;

    @Column(name="cabinet_type")
    private String cabinetType;

    @Column(name="color")
    private String color;

    @Column(name="material")
    private String material;

    @Column(name="handle_type")
    private String handleType;

    public void setTrackingNo(Long trackingNo) {
        this.trackingNo = trackingNo;
    }

    public String getCabinetType() {
        return cabinetType;
    }

    public void setCabinetType(String cabinetType) {
        this.cabinetType = cabinetType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }

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

    public Order(Integer orderId, String orderStatus, @Digits(integer = 6, fraction = 0) Long trackingNo, String design, String cabinetType, String color, String material, String handleType) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.trackingNo = trackingNo;
        this.design = design;
        this.cabinetType = cabinetType;
        this.color = color;
        this.material = material;
        this.handleType = handleType;
    }

    public Order(String orderStatus, @Digits(integer = 6, fraction = 0) Long trackingNo, String design, String cabinetType, String color, String material, String handleType) {
        this.orderStatus = orderStatus;
        this.trackingNo = trackingNo;
        this.design = design;
        this.cabinetType = cabinetType;
        this.color = color;
        this.material = material;
        this.handleType = handleType;
    }

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
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


}
