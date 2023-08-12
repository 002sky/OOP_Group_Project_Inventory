package com.example.oop_group_project_inventory;

import java.sql.*;


public abstract class Product {
    private String productID;
    private String productName;
    private double unitPrice;
    private double sellingPrice;
    private boolean productStatus;
    private String productBrand;

    protected Product() {

    }

    protected Product(String productID, String productName, double unitPrice, double sellingPrice, String productBrand, boolean productStatus) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.sellingPrice = sellingPrice;
        this.productBrand = productBrand;
        this.productStatus = productStatus;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    //update database
    public int updateProduct(Product product) {
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";
        try {
            Connection connection = DriverManager.getConnection(url);

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            String sql = "UPDATE Product SET  productName = ?, unitPrice = ?, sellingPrice = ?, productBrand = ?, productStatus = ? WHERE productID = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, product.getProductName());
            statement.setDouble(2, product.getUnitPrice());
            statement.setDouble(3, product.getSellingPrice());
            statement.setString(4, product.getProductBrand());
            statement.setBoolean(5, product.isProductStatus());
            statement.setString(6, product.getProductID());

            int result = statement.executeUpdate();

            if (result >= 1) {
                return result;
            } else {
                return 0;
            }
            //@TODO if there is an error return 0


        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public String toString() {
        return productID + " - " + productName;
    }

    //check and return whether got existing product
    public int checkProductExists(String productID) {
        int[] productExists = new int[1];
        productExists[0] = -1;
        MainPage.productArrayList.forEach(product -> {
            if (product.getProductID().equals(productID)) {
                productExists[0] = MainPage.productArrayList.indexOf(product);
            }
        });
        return productExists[0];
    }
}
