package com.example.oop_group_project_inventory;

public class Clothing extends Product {

    private String clothingType;
    private char clothingSize;
    private String color;
    private String material;

    protected Clothing() {

    }

    protected Clothing(String productID, String productName, double unitPrice, double sellingPrice, String productBrand, boolean productStatus, String clothingType, char clothingSize, String color, String material) {
        super(productID,productName,unitPrice,sellingPrice,productBrand,productStatus);
        this.clothingType = clothingType;
        this.clothingSize = clothingSize;
        this.color = color;
        this.material = material;
    }

    public String getClothingType() {
        return clothingType;
    }

    public char getClothingSize() {
        return clothingSize;
    }

    public String getColor() {
        return color;
    }

    public String getMaterial() {
        return material;
    }
    public void setClothingType(String clothingType) {
        this.clothingType = clothingType;
    }

    public void setClothingSize(char clothingSize) {
        this.clothingSize = clothingSize;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
