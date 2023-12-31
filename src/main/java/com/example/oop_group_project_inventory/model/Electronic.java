package com.example.oop_group_project_inventory.model;

import java.sql.*;
import java.util.ArrayList;


/**
 *  Electronic class with extend Product
 */
public class Electronic extends Product {
    private String color;
    private String model;

    /**
     * constructor with no parameter
     */
    public Electronic() {

    }

    /**
     * constructor with all parameter
     * @param color accept color as String
     * @param model accept model as String
     */
    public Electronic(String productID, String productName, double unitPrice, double sellingPrice, String productBrand, boolean productStatus, String color, String model) {
        super(productID, productName, unitPrice, sellingPrice, productBrand, productStatus);
        this.color = color;
        this.model = model;
    }

    /**
     * getters
     * @return return color as String
     */
    public String getColor() {
        return color;
    }

    /**
     * getters
     * @return return model as String
     */
    public String getModel() {
        return model;
    }

    /**
     * setters
     * @param color accept color as String
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * setters
     * @param model accept model as String
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * load data from database
     * @return return Electronic object list as ArrayList
     */
    //load data from database
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

                Electronic a = new Electronic(id, name, unitPrice, sellingPrice, brand, status, color, models);
                electronicList.add(a);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return electronicList;
    }

    /**
     * update the electronic data in the database by electronic object
     * @param electronic accept Electronic object as parameter
     * @return return a boolean value for determine the success or failure
     */
    public boolean updateProduct(Electronic electronic) {
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";
        boolean hasError = false;
        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            int result = super.updateProduct(electronic);

            if (result >= 1) {
                String newSql = "UPDATE Electronic SET color = ?, models = ? WHERE productID = ?";
                PreparedStatement newStatement = connection.prepareStatement(newSql);
                newStatement.setString(1, electronic.getColor());
                newStatement.setString(2, electronic.getModel());
                newStatement.setString(3, electronic.getProductID());
                newStatement.executeUpdate();

                System.out.println("Electronic Updated");
            } else {
                hasError = true;
            }

            //@todo if there is an error return an error message to the user

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return hasError;
    }
}
