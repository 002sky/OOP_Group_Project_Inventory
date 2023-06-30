package com.example.oop_group_project_inventory;

public class Electronic extends Product {
    private String color;
    private String model;

    protected Electronic(){

    }

    protected Electronic(String productID,String productName,double unitPrice, double sellingPrice ,String productBrand,boolean productStatus,String color, String model){
        super();
        this.color = color;
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
