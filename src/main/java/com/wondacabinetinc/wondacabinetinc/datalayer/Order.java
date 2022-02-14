package com.wondacabinetinc.wondacabinetinc.datalayer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id", unique = true, nullable = false)
    private Integer orderId;

    @Column(name="orderStatus", columnDefinition = "varchar(50) default 'Awaiting Order'", nullable = false)
    private String orderStatus;

    @Column(name="trackingNo",unique = true)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @PositiveOrZero
    private String trackingNo;

    @Column(name="design")
    private String design;

    @Column(name="cabinet_type")
    private String cabinetType;

    @Column(name="color")
    private String color;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="order_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDate;

    @Column(name="delivery_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deliveryDate;

    @Column(name="material")
    private String material;

    @Column(name="handle_type")
    private String handleType;

    @Column(name = "email")
    @Email
    private String email;

//    public void setTrackingNo(Integer trackingNo) {
//        Random rand = new Random();
//        this.trackingNo = rand.nextInt(999999);
//        this.trackingNo = trackingNo;
//    }

    public String getCabinetType() {
        return cabinetType;
    }

    public void setCabinetType(String cabinetType) {
        this.cabinetType = cabinetType;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
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

//    public int getTrackingNo() {
//        return trackingNo;
//    }

//    public void setTrackingNo(int trackingNo) {
//        Random rand = new Random();
//        this.trackingNo = rand.nextInt(999999);
//    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

}
