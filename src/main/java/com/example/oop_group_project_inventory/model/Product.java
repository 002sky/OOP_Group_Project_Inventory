package com.example.oop_group_project_inventory.model;

import com.example.oop_group_project_inventory.controller.MainPage;

import java.sql.*;


/**
 * abstract class Product
 */
public abstract class Product {
    private String productID;
    private String productName;
    private double unitPrice;
    private double sellingPrice;
    private boolean productStatus;
    private String productBrand;

    /**
     * constructor with no parameter
     */
    public Product() {

    }

    /**
     * constructor
     * @param productID accept productID as String parameter
     * @param productName accept productName as String parameter
     * @param unitPrice accept unitPrice as double parameter
     * @param sellingPrice accept sellingPrice as double parameter
     * @param productBrand accept productBrand as String parameter
     * @param productStatus accept productStatus as boolean parameter
     */
    public Product(String productID, String productName, double unitPrice, double sellingPrice, String productBrand, boolean productStatus) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.sellingPrice = sellingPrice;
        this.productBrand = productBrand;
        this.productStatus = productStatus;
    }

    /**
     * setters
     * @param productID accept productID as String parameter
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     * setters
     * @param productName accept productName as String parameter
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * setters
     * @param unitPrice accept unitPrice as double parameter
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * setters
     * @param sellingPrice accept sellingPrice as double parameter
     */
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * setters
     * @param productBrand accept productBrand as String parameter
     */
    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    /**
     * setters
     * @param productStatus accept productStatus as boolean parameter
     */
    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * getters
     * @return return productID as String value
     */
    public String getProductID() {
        return productID;
    }

    /**
     * getters
     * @return return productName as String value
     */
    public String getProductName() {
        return productName;
    }

    /**
     * getters
     * @return return unitPrice as double value
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * getters
     * @return return sellingPrice as double value
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * getters
     * @return return productBrand as String value
     */
    public String getProductBrand() {
        return productBrand;
    }

    /**
     * getters
     * @return return productStatus as boolean value
     */
    public boolean isProductStatus() {
        return productStatus;
    }

    /**
     * Load data from database
     * @param product accept a customer object product and its subclass as parameter
     * @return return a integer value for determine the success or failure
     */
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

    /**
     * @return return productID + productName as String
     */
    @Override
    public String toString() {
        return productID + " - " + productName;
    }

    /**
     * check and return whether got existing product in the Arraylist
     * @param productID accept productID as String parameter
     * @return return the index of the product in the arraylist as an integer
     */
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
