package com.example.volot.Models;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String id;

    private String volotid;

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    private User user;

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    private Product product;
    private String address;

    private String dateTime;

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    private Status status;

    private String Contract;
    private Integer Amount;

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public String getVolotid() {
        return volotid;
    }

    public void setVolotid(String volotid) {
        this.volotid = volotid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getContract() {
        return Contract;
    }

    public void setContract(String contract) {
        Contract = contract;
    }
}
