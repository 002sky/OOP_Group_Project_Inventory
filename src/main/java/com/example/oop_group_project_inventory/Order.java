package com.example.oop_group_project_inventory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Order {

    private String orderID;
    private LocalDate orderDate;
    private ArrayList<OrderItem> orderItem;


    protected Order(String orderID, LocalDate orderDate, ArrayList<OrderItem> orderItem) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderItem = orderItem;
    }

    protected Order() {
    }

    public String getOrderID() {
        return orderID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setOrderItem(ArrayList<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

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
