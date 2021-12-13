package com.wondacabinetinc.wondacabinetinc.datalayer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity
@Table(name="OrderDetails")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrderDetails {

    @Id
    @GeneratedValue
    private Integer orderId;

    @Column(name="cabinet_type")
    private String cabinetType;

    @Column(name="color")
    private String color;

    @Column(name="material")
    private String material;

    @Column(name="handle_type")
    private String handleType;

    public OrderDetails() {
    }

    public OrderDetails(String cabinetType, String color, String material, String handleType) {
        this.cabinetType = cabinetType;
        this.color = color;
        this.material = material;
        this.handleType = handleType;
    }

    public OrderDetails(Integer orderId, String cabinetType, String color, String material, String handleType) {
        this.orderId = orderId;
        this.cabinetType = cabinetType;
        this.color = color;
        this.material = material;
        this.handleType = handleType;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
}
