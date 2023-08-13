package com.example.oop_group_project_inventory.model;

import com.example.oop_group_project_inventory.controller.MainPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;

/**
 * Product Patch class
 */
public class ProductPatch {
    private String PatchNumber;
    private String supplySource;
    private Product items;
    private Inventory storedInventory;
    private LocalDate receivedDate;
    private int quantity;

    /**
     * constructor with no parameter
     */
    public ProductPatch() {

    }

    /**
     * Parameterized constructor
     * @param PatchNumber accept PatchNumber as String
     * @param supplySource accept supplySource as String
     * @param items accept items as Product object
     * @param storedInventory accept storedInventory as Inventory  Product object
     * @param receivedDate accept receivedDate as LocalDate
     * @param quantity accept quantity as int
     */
    public ProductPatch(String PatchNumber, String supplySource, Product items, Inventory storedInventory, LocalDate receivedDate, int quantity) {
        this.PatchNumber = PatchNumber;
        this.supplySource = supplySource;
        this.items = items;
        this.storedInventory = storedInventory;
        this.receivedDate = receivedDate;
        this.quantity = quantity;

    }

    /**
     * getters
     * @return return PatchNumber as String
     */
    public String getPatchNumber() {
        return PatchNumber;
    }


    /**
     * getters
     * @return return supplySource as String
     */
    public String getSupplySource() {
        return supplySource;
    }

    /**
     * getters
     * @return return items as Product object
     */
    public Product getItems() {
        return items;
    }

    /**
     * getters
     * @return return storedInventory as Inventory object
     */
    public Inventory getStoredInventory() {
        return storedInventory;
    }

    /**
     * getters
     * @return return receivedDate as LocalDate
     */
    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    /**
     * getters
     * @return return quantity as int
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * setters
     * @param PatchNumber accept PatchNumber as String
     */
    public void setPatchNumber(String PatchNumber) {
        this.PatchNumber = PatchNumber;
    }

    /**
     * setters
     * @param supplySource  accept supplySource as String
     */
    public void setSupplySource(String supplySource) {
        this.supplySource = supplySource;
    }

    /**
     * setters
     * @param items accept items as Product object
     */
    public void setItems(Product items) {
        this.items = items;
    }

    /**
     * setters
     * @param storedInventory accept storedInventory as Inventory object
     */
    public void setInventory(Inventory storedInventory) {
        this.storedInventory = storedInventory;
    }


    /**
     * setters
     * @param receivedDate accept receivedDate as LocalDate
     */
    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    /**
     * setters
     * @param quantity accept quantity as int
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Save Product Patch into the database
     * @param productPatch accept ProductPatch object as parameter
     * @return return int value to determine whether the Product Patch is saved
     */
    //add to database
    public int saveToDatabase(ProductPatch productPatch) {

        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";
        int result = 0;

        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            String sql = "INSERT INTO Patch (PatchNumber, supplySource, productID, inventoryID, receivedDate, quantity) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, productPatch.getPatchNumber());
            statement.setString(2, productPatch.getSupplySource());
            statement.setString(3, productPatch.getItems().getProductID());
            statement.setString(4, productPatch.getStoredInventory().getInventoryID());
            statement.setString(5, productPatch.getReceivedDate().toString());
            statement.setInt(6, productPatch.getQuantity());

            result = statement.executeUpdate();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Load product patch from database into an ArrayList
     * @return return ArrayList of ProductPatch
     */
    //load data from database
    public ArrayList<ProductPatch> loadFromDatabase() {
        ArrayList<ProductPatch> list = new ArrayList<>();

        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";
        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            String sql = "SELECT * FROM Patch";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String tmpProductID = resultSet.getString("productID");
                String tmpInventoryID = resultSet.getString("inventoryID");
                Product[] tmpProduct = new Product[1];
                MainPage.productArrayList.forEach(product -> {
                    if (product.getProductID().equals(tmpProductID)) {
                        if (product instanceof Electronic) {
                            tmpProduct[0] = (Electronic) product;
                        } else if (product instanceof Grocery) {
                            tmpProduct[0] = (Grocery) product;
                        } else {
                            tmpProduct[0] = (Clothing) product;
                        }
                    }
                });

                Inventory[] tmpInventory = new Inventory[1];
                MainPage.inventoryArrayList.forEach(inventory -> {
                    if (inventory.getInventoryID().equals(tmpInventoryID)) {
                        tmpInventory[0] = inventory;
                    }
                });

                ProductPatch productPatch = new ProductPatch();
                productPatch.setPatchNumber(resultSet.getString("PatchNumber"));
                productPatch.setSupplySource(resultSet.getString("supplySource"));
                productPatch.setItems(tmpProduct[0]);
                productPatch.setInventory(tmpInventory[0]);
                productPatch.setReceivedDate(resultSet.getDate("receivedDate").toLocalDate());
                productPatch.setQuantity(resultSet.getInt("quantity"));
                list.add(productPatch);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;
    }


}
