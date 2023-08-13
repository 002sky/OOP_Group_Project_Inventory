package com.example.oop_group_project_inventory.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *  Order class
 */
public class Order {

    private String orderID;
    private LocalDate orderDate;
    private ArrayList<OrderItem> orderItem;


    /**
     * Parameterized constructor
     * @param orderID accept orderID as String
     * @param orderDate accept orderDate as LocalDate
     * @param orderItem accept orderItem as ArrayList
     */
    public Order(String orderID, LocalDate orderDate, ArrayList<OrderItem> orderItem) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderItem = orderItem;
    }

    /**
     *  constructor with no parameter
     */
    public Order() {
    }

    /**
     * getters
     * @return return orderID as String
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * getters
     * @return return orderDate as LocalDate
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * setters
     * @param orderDate accept orderDate as LocalDate
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * getters
     * @return return orderItem as ArrayList
     */
    public ArrayList<OrderItem> getOrderItem() {
        return orderItem;
    }

    /**
     * setters
     * @param orderID accept orderID as String
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * setters
     * @param orderItem accept orderItem as ArrayList
     */
    public void setOrderItem(ArrayList<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    /**
     * Save Order Object to database
     * @param order accept Order object as parameter
     * @return return boolean value to determine whether the order is saved
     */
    //add to database
    public boolean saveToDatabase(Order order) {
        boolean hasError = false;
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";
        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String sql = "INSERT INTO Order (orderID, orderDate) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, order.getOrderID());
            statement.setObject(2, order.getOrderDate());

            int result = statement.executeUpdate();

            if (result == 1) {
                OrderItem ol = new OrderItem();
                ol.saveToDatabase(order.getOrderItem(), order.getOrderID());
            } else {
                hasError = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return hasError;

    }

    /**
     * Load all order objects from database into an ArrayList
     * @return return all orders as ArrayList
     */
    //load data from database
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orderList = new ArrayList<>();
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";
        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String sql = "SELECT * FROM Order";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            OrderItem oi = new OrderItem();
            ArrayList<OrderItem> orderItem = new ArrayList<>();

            while (resultSet.next()) {
                String id = resultSet.getString("orderID");
                LocalDate date = resultSet.getDate("orderDate").toLocalDate();

                orderItem = oi.getOrderItem(id);

                Order order = new Order(id, date, orderItem);
                orderList.add(order);
            }
        } catch (SQLException | ClassNotFoundException ex) {

        }
        return orderList;
    }
}
