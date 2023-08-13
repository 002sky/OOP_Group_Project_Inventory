package com.example.oop_group_project_inventory.model;

import com.example.oop_group_project_inventory.controller.MainPage;

import java.sql.*;
import java.util.ArrayList;

/**
 * OrderItem class
 */
public class OrderItem {

    private Product orderProduct;
    private int productQuantity;
    private double TotalPrice;

    /**
     * constructor with no parameter
     */
    public OrderItem() {

    }

    /**
     * Parameterized constructor
     * @param orderProduct accept orderProduct as Product obejct
     * @param productQuantity accept productQuantity as int
     * @param totalPrice accept totalPrice as double
     */
    public OrderItem(Product orderProduct, int productQuantity, double totalPrice) {
        this.orderProduct = orderProduct;
        this.productQuantity = productQuantity;
        TotalPrice = totalPrice;
    }


    /**
     * getters
     * @return return orderProduct as Product object
     */
    public Product getOrderProduct() {
        return orderProduct;
    }

    /**
     * setters
     * @param orderProduct accept orderProduct as Product object
     */
    public void setOrderProduct(Product orderProduct) {
        this.orderProduct = orderProduct;
    }

    /**
     * getters
     * @return return productQuantity as int
     */
    public int getProductQuantity() {
        return productQuantity;
    }

    /**
     * setters
     * @param productQuantity accept productQuantity as int
     */
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    /**
     * getters
     * @return return totalPrice as double
     */
    public double getTotalPrice() {
        return TotalPrice;
    }

    /**
     * setters
     * @param totalPrice accept totalPrice as double
     */
    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    /**
     * Save an Arraylist of OrderItem to database
     * @param orderItem accept an ArrayList of OrderItem objects
     * @param orderID accept orderID as String
     * @return return boolean value to determine whether the order is saved
     */
    //add to database
    public boolean saveToDatabase(ArrayList<OrderItem> orderItem, String orderID) {
        boolean hasError = false;
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";

        for (OrderItem ol : orderItem) {
            try {
                Connection connection = DriverManager.getConnection(url);
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

                String sql = "INSERT INTO OrderItem (OrderID,ProductID, Quantity, TotalPrice) VALUES (?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, orderID);
                statement.setString(2, ol.getOrderProduct().getProductID());
                statement.setInt(3, ol.getProductQuantity());
                statement.setDouble(4, ol.getTotalPrice());
                int result = statement.executeUpdate();
                if (result == 1) {
                    hasError = false;
                } else {
                    hasError = true;
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        }

        return hasError;
    }

    /**
     * load all the OrderItem objects from database based on orderID into an ArrayList
     * @param OrderID accept orderID as String
     * @return return an ArrayList of OrderItem
     */
    //load data from database
    public ArrayList<OrderItem> getOrderItem(String OrderID) {
        ArrayList<OrderItem> orderItem = new ArrayList<>();
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";
        try {


            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String sql = "SELECT * FROM OrderItem WHERE OrderID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, OrderID);

            ResultSet rs = statement.executeQuery();

            int productID;
            Product p = MainPage.productArrayList.get(0);

            while (rs.next()) {
                productID = p.checkProductExists(rs.getString("ProductID"));

                orderItem.add(new OrderItem(MainPage.productArrayList.get(productID), rs.getInt("Quantity"), rs.getDouble("TotalPrice")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return orderItem;
    }
}
