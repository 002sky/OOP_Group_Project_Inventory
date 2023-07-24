package com.example.oop_group_project_inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;

public class ProductPatch {
    private String PatchNumber;
    private String supplySource;
    private Product items;
    private Inventory storedInventory;
    private LocalDate receivedDate;
    private int quantity;

    protected ProductPatch() {

    }

    protected ProductPatch(String PatchNumber, String supplySource, Product items, Inventory storedInventory, LocalDate receivedDate, int quantity) {
        this.PatchNumber = PatchNumber;
        this.supplySource = supplySource;
        this.items = items;
        this.storedInventory = storedInventory;
        this.receivedDate = receivedDate;
        this.quantity = quantity;

    }

    public String getPatchNumber() {
        return PatchNumber;
    }


    public String getSupplySource() {
        return supplySource;
    }

    public Product getItems() {
        return items;
    }

    public Inventory getStoredInventory() {
        return storedInventory;
    }
    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPatchNumber(String PatchNumber) {
        this.PatchNumber = PatchNumber;
    }

    public void setSupplySource(String supplySource) {
        this.supplySource = supplySource;
    }

    public void setItems(Product items) {
        this.items = items;
    }

    public void setInventory(Inventory storedInventory) {
        this.storedInventory = storedInventory;
    }

    public void setStoredInventory(Inventory storedInventory) {
        this.storedInventory = storedInventory;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public int saveToDatabase(ProductPatch productPatch){

        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";
        int result = 0;

        try{
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
        }catch ( Exception e){
            e.printStackTrace();
        }

        return result;
    }


    public ArrayList<ProductPatch> loadFromDatabase(){
        ArrayList<ProductPatch> list = new ArrayList<>();

        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";
        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            String sql = "SELECT * FROM Patch";


            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String tmpProductID = resultSet.getString("productID");
                String tmpInventoryID = resultSet.getString("inventoryID");
                Product[] tmpProduct = new Product[1];
                MainPage.productArrayList.forEach(product -> {
                    if(product.getProductID().equals(tmpProductID)){
                        if(product instanceof Electronic){
                            tmpProduct[0] = (Electronic) product;
                        }else if (product instanceof Grocery){
                            tmpProduct[0] = (Grocery) product;
                        }else {
                            tmpProduct[0] = (Clothing) product;
                        }
                    }
                });

                Inventory[] tmpInventory = new Inventory[1];
                MainPage.inventoryArrayList.forEach(inventory -> {
                    if(inventory.getInventoryID().equals(tmpInventoryID)){
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

        }catch ( Exception e){
            e.printStackTrace();
        }


        return list;
    }


}
