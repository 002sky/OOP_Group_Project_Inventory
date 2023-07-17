package com.example.oop_group_project_inventory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

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

    public ArrayList<Clothing> loadFromDatabase() {
        ArrayList<Clothing> ClothingList = new ArrayList<>();
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";

        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String sql = "SELECT * FROM Clothing LEFT JOIN Product ON Clothing.productID = Product.productID";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                String id = result.getString("productID");
                String name = result.getString("Product.productName");
                double unitPrice = result.getDouble("Product.unitPrice");
                double sellingPrice = result.getDouble("Product.sellingPrice");
                String brand = result.getString("Product.productBrand");
                boolean status = result.getBoolean("Product.productStatus");
                String type = result.getString("clothingType");
                char size = result.getString("clothingSize").charAt(0);
                String color = result.getString("color");
                String material = result.getString("material");

                Clothing a = new Clothing(id,name,unitPrice,sellingPrice,brand,status,type,size,color,material);

                ClothingList.add(a);

                System.out.println(a.getProductID() + " " + a.getProductName() + " " + a.getSellingPrice());
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ClothingList;

    }
}
