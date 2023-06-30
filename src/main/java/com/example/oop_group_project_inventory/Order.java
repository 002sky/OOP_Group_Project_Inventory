package com.example.oop_group_project_inventory;

import java.util.Date;
import java.util.Map;

public class Order {

    private String orderID;
    private Date orderReceivedDate;
    private Boolean orderStatus;
    private Date orderCompleteDate;
    private Map<Product, Integer> orderItem;


    protected Order(){

    }

    protected Order(String orderID, Date orderReceivedDate, Boolean orderStatus, Date orderCompleteDate, Map<Product,Integer> orderItem){
        this.orderID = orderID;
        this.orderReceivedDate = orderReceivedDate;
        this.orderStatus = orderStatus;
        this.orderCompleteDate = orderReceivedDate;
        this.orderItem = orderItem;
    }

    public String getOrderID() {
        return orderID;
    }

    public Date getOrderReceivedDate() {
        return orderReceivedDate;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public Date getOrderCompleteDate() {
        return orderCompleteDate;
    }

    public Map<Product, Integer> getOrderItem() {
        return orderItem;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setOrderReceivedDate(Date orderReceivedDate) {
        this.orderReceivedDate = orderReceivedDate;
    }

    public void setOrderCompleteDate(Date orderCompleteDate) {
        this.orderCompleteDate = orderCompleteDate;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderItem(Map<Product, Integer> orderItem) {
        this.orderItem = orderItem;
    }
}
