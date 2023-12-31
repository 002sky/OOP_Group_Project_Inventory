package com.example.oop_group_project_inventory.model;

import java.util.ArrayList;
import java.sql.*;

/**
 *  Grocery class with extend Product
 */
public class Grocery extends Product {
    private String category;

    /**
     * constructor with no parameter
     */
    public Grocery() {

    }

    /**
     * constructor with all parameter
     * @param category accept category as String
     */
    public Grocery(String productID, String productName, double unitPrice, double sellingPrice, String productBrand, boolean productStatus, String category) {
        super(productID, productName, unitPrice, sellingPrice, productBrand, productStatus);
        this.category = category;

    }

    /**
     * getters
     * @return return category as String
     */
    public String getCategory() {
        return category;
    }

    /**
     * setters
     * @param category accept category as String
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * load Grocery list from database
     * @return return grocery list as ArrayList
     */
    //load data from database
    public ArrayList<Grocery> loadFromDatabase() {
        ArrayList<Grocery> GroceryList = new ArrayList<>();
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";

        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String sql = "SELECT * FROM Grocery LEFT JOIN Product ON Grocery.productID = Product.productID";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                String id = result.getString("productID");
                String category = result.getString("category");
                String name = result.getString("Product.productName");
                double unitPrice = result.getDouble("Product.unitPrice");
                double sellingPrice = result.getDouble("Product.sellingPrice");
                String brand = result.getString("Product.productBrand");
                boolean status = result.getBoolean("Product.productStatus");


                Grocery a = new Grocery(id, name, unitPrice, sellingPrice, brand, status, category);
                GroceryList.add(a);

            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return GroceryList;
    }

    /**
     * update the database with grocery object
     * @param grocery accept a grocery object as parameter
     * @return return a boolean value for determine the success or failure
     */
    //update the database
    public boolean updateProduct(Grocery grocery) {
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";
        boolean hasError = false;
        try {
            Connection connection = DriverManager.getConnection(url);
            int result = super.updateProduct(grocery);

            if (result >= 1) {

                String newSql = "UPDATE Grocery SET category = ? WHERE productID = ?";

                PreparedStatement newStatement = connection.prepareStatement(newSql);

                newStatement.setString(1, grocery.getCategory());
                newStatement.setString(2, grocery.getProductID());

                newStatement.executeUpdate();

                System.out.println("Grocery Updated");
            } else {
                hasError = true;
            }

            //@todo if there is an error return an error message to the user


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return hasError;
    }


}

