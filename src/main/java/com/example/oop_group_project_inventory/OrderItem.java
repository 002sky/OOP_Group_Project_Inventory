package com.example.oop_group_project_inventory;

import java.sql.*;
import java.util.ArrayList;

public class OrderItem {

    private Product orderProduct;
    private int productQuantity;
    private double TotalPrice;

    protected OrderItem(){

    }
    public OrderItem(Product orderProduct, int productQuantity, double totalPrice) {
        this.orderProduct = orderProduct;
        this.productQuantity = productQuantity;
        TotalPrice = totalPrice;
    }


    public Product getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(Product orderProduct) {
        this.orderProduct = orderProduct;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public boolean saveToDatabase(ArrayList<OrderItem> orderItem, String orderID){
        boolean hasError = false;
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";

        for (OrderItem ol: orderItem) {
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
                }else {
                    hasError = true;
                }
            }catch (ClassNotFoundException | SQLException e){
                e.printStackTrace();
            }

        }

        return hasError;
    }

    public ArrayList<OrderItem> getOrderItem(String OrderID){
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

            while(rs.next()){
                productID = p.checkProductExists(rs.getString("ProductID"));

                orderItem.add(new OrderItem(MainPage.productArrayList.get(productID), rs.getInt("Quantity"), rs.getDouble("TotalPrice")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return orderItem;
    }
}
