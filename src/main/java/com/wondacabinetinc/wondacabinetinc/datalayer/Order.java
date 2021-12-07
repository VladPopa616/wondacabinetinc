package com.wondacabinetinc.wondacabinetinc.datalayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trackingNo;

    @Column(name="cabinet_type")
    @NonNull
    private String cabinetType;

    @Column(name="color")
    @NonNull
    private String color;

    @Column(name="material")
    @NonNull
    private String material;

    @Column(name="handle_type")
    @NonNull
    private String handleType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private List<Item> items;

    public Order(long trackingNo, String cabinetType, String color, String material, String handleType, List<Item> items) {
        this.trackingNo = trackingNo;
        this.cabinetType = cabinetType;
        this.color = color;
        this.material = material;
        this.handleType = handleType;
        this.items = items;
    }

    public Order() {

    }

    public Order(@NonNull String cabinetType, @NonNull String color, @NonNull String material, @NonNull String handleType, List<Item> items) {
        this.cabinetType = cabinetType;
        this.color = color;
        this.material = material;
        this.handleType = handleType;
        this.items = items;
    }

    public long getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(long trackingNo) {
        this.trackingNo = trackingNo;
    }

    @NonNull
    public String getCabinetType() {
        return cabinetType;
    }

    public void setCabinetType(@NonNull String cabinetType) {
        this.cabinetType = cabinetType;
    }

    @NonNull
    public String getColor() {
        return color;
    }

    public void setColor(@NonNull String color) {
        this.color = color;
    }

    @NonNull
    public String getMaterial() {
        return material;
    }

    public void setMaterial(@NonNull String material) {
        this.material = material;
    }

    @NonNull
    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(@NonNull String handleType) {
        this.handleType = handleType;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
