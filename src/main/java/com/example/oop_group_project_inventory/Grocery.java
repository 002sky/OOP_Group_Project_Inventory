package com.example.oop_group_project_inventory;

import java.util.ArrayList;
import java.util.Date;
import java.sql.*;

public class Grocery extends Product {
    private Date expiry_date;

    protected Grocery() {

    }

    protected Grocery(String productID, String productName, double unitPrice, double sellingPrice, String productBrand, boolean productStatus, Date expiry_date) {
        super(productID,productName,unitPrice,sellingPrice,productBrand, productStatus);
        this.expiry_date = expiry_date;

    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public void loadFromDatabase() {
//        ArrayList<Grocery> GroceryList = null;
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";

        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String sql = "SELECT * FROM Grocery LEFT JOIN Product ON Grocery.productID = Product.productID";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                String id = result.getString("productID");
                Date expiry = result.getDate("expiry_date");
                String name = result.getString("Product.productName");
                double unitPrice = result.getDouble("Product.unitPrice");
                double sellingPrice = result.getDouble("Product.sellingPrice");
                String brand = result.getString("Product.productBrand");
                boolean status = result.getBoolean("Product.productStatus");


                Grocery a = new Grocery(id,name,unitPrice,sellingPrice,brand,status,expiry);

                System.out.println(a.getProductID() + " " + a.getProductName() + " " + a.getSellingPrice());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}

