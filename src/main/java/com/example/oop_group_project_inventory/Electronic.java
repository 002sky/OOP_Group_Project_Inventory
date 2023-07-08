package com.example.oop_group_project_inventory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Electronic extends Product {
    private String color;
    private String model;

    protected Electronic(){

    }

    protected Electronic(String productID,String productName,double unitPrice, double sellingPrice ,String productBrand,boolean productStatus,String color, String model){
        super(productID,productName,unitPrice,sellingPrice,productBrand,productStatus);
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

    public ArrayList<Electronic> loadFromDatabase() {
        ArrayList<Electronic> electronicList = new ArrayList<>();
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";

        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String sql = "SELECT * FROM Electronic LEFT JOIN Product ON Electronic.productID = Product.productID";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                String id = result.getString("productID");
                String name = result.getString("Product.productName");
                String color = result.getString("color");
                String models = result.getString("models");

                double unitPrice = result.getDouble("Product.unitPrice");
                double sellingPrice = result.getDouble("Product.sellingPrice");
                String brand = result.getString("Product.productBrand");
                boolean status = result.getBoolean("Product.productStatus");


                Electronic a = new Electronic(id,name,unitPrice,sellingPrice,brand,status,color,models);
                electronicList.add(a);


                System.out.println(a.getProductID() + " " + a.getProductName() + " " + a.getSellingPrice());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return electronicList;
    }
}
