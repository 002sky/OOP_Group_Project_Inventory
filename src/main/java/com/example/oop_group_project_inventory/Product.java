package com.example.oop_group_project_inventory;

import java.sql.*;
import java.util.Date;

public  class Product {
    private String productID;
    private String productName;
    private double unitPrice;
    private double sellingPrice;
    private boolean productStatus;
    private String productBrand;
    protected Product(){

    }
    protected Product(String productID,String productName,double unitPrice, double sellingPrice ,String productBrand,boolean productStatus){
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.sellingPrice = sellingPrice;
        this.productBrand = productBrand;
        this.productStatus = productStatus;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public String getProductBrand() {
        return productBrand;
    }
    public boolean isProductStatus() {
        return productStatus;
    }




}
