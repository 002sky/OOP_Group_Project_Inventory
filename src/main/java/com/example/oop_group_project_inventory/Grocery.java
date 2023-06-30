package com.example.oop_group_project_inventory;

import java.util.Date;

public class Grocery extends Product {
    private Date expiry_date;

    protected Grocery() {

    }

    protected Grocery(String productID, String productName, double unitPrice, double sellingPrice, String productBrand, boolean productStatus, Date expiry_date) {
        super();
        this.expiry_date = expiry_date;

    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }
}
