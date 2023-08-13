package com.example.oop_group_project_inventory.model;

import com.example.oop_group_project_inventory.controller.MainPage;

import java.sql.*;
import java.util.ArrayList;

/**
 *  Inventory object
 */
public class Inventory {

    private String inventoryID;
    private String inventoryName;
    private String inventoryLocation;
    private boolean freezerAvailable;

    /**
     * constructor with no parameter
     */
    public Inventory() {

    }

    /**
     * @param inventoryID accept inventoryID as String
     * @param inventoryName accept inventoryName as String
     * @param inventoryLocation accept inventoryLocation as String
     * @param freezerAvailable accept freezerAvailable as boolean
     */
    public Inventory(String inventoryID, String inventoryName, String inventoryLocation, boolean freezerAvailable) {
        this.inventoryID = inventoryID;
        this.inventoryName = inventoryName;
        this.inventoryLocation = inventoryLocation;
        this.freezerAvailable = freezerAvailable;

    }

    /**
     * getters
     * @return return inventoryID as String
     */
    public String getInventoryID() {
        return inventoryID;
    }

    /**
     * getters
     * @return return inventoryName as String
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * getters
     * @return return inventoryLocation as String
     */
    public String getInventoryLocation() {
        return inventoryLocation;
    }

    /**
     * getters
     * @return return freezerAvailable as boolean
     */
    public boolean isFreezerAvailable() {
        return freezerAvailable;
    }

    /**
     * setters
     * @param inventoryID accept inventoryID as String
     */
    public void setInventoryID(String inventoryID) {
        this.inventoryID = inventoryID;
    }

    /**
     * setters
     * @param inventoryName accept inventoryName as String
     */
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    /**
     * setters
     * @param inventoryLocation accept inventoryLocation as String
     */
    public void setInventoryLocation(String inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }

    /**
     * setters
     * @param freezerAvailable accept freezerAvailable as boolean
     */
    public void setFreezerAvailable(boolean freezerAvailable) {
        this.freezerAvailable = freezerAvailable;
    }

    /**
     * add Inventory Object into the database
     * @param inventory accept Inventory Object as parameter with name inventory
     */
    //add to database
    public void addInventory(Inventory inventory) {
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";
        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String sql = "INSERT INTO Inventory (inventoryID, inventoryName, inventoryLocation, freezerAvailable) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, inventory.getInventoryID());
            statement.setString(2, inventory.getInventoryName());
            statement.setString(3, inventory.getInventoryLocation());
            statement.setBoolean(4, inventory.isFreezerAvailable());
            int result = statement.executeUpdate();

            if (result == 1) {
                System.out.println("Inventory added successfully");
            }


        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * load all inventory data from database and load it into ArrayList
     * @return return ArrayList of Inventory Object
     */
    //load data from database
    public ArrayList<Inventory> getAllInventory() {

        ArrayList<Inventory> inventoryArrayList = new ArrayList<>();
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";
        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String sql = "SELECT * FROM Inventory";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Inventory inventory = new Inventory();
                inventory.setInventoryID(resultSet.getString("inventoryID"));
                inventory.setInventoryName(resultSet.getString("inventoryName"));
                inventory.setInventoryLocation(resultSet.getString("inventoryLocation"));
                inventory.setFreezerAvailable(resultSet.getBoolean("freezerAvailable"));
                inventoryArrayList.add(inventory);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return inventoryArrayList;
    }

    /**
     * @param InventoryID accept InventoryID as String
     * @return return boolean value for determine the existence of inventory
     */
    //check and return whether got existing inventory
    public boolean checkInventoryExists(String InventoryID) {
        boolean[] inventoryExists = new boolean[1];
        inventoryExists[0] = false;
        MainPage.inventoryArrayList.forEach(inventory -> {
            if (inventory.getInventoryID().equals(InventoryID)) {
                inventoryExists[0] = true;
            }
        });
        return inventoryExists[0];
    }

    /**
     * update Inventory Object into the database
     * @param inventory accept Inventory Object as parameter
     * @return return a boolean value for determine the success or failure
     */
    //update database
    public boolean updateInventory(Inventory inventory) {
        boolean hasError = false;
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";
        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            String sql = "UPDATE Inventory SET inventoryID = ?, inventoryName = ?, inventoryLocation = ?, freezerAvailable = ? WHERE inventoryID = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, inventory.getInventoryID());
            statement.setString(2, inventory.getInventoryName());
            statement.setString(3, inventory.getInventoryLocation());
            statement.setBoolean(4, inventory.isFreezerAvailable());
            statement.setString(5, inventory.getInventoryID());
            int result = statement.executeUpdate();

            if (result == 1) {
                System.out.println("Inventory updated successfully");

            } else {
                System.out.println("Inventory not updated");
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
     * @return return inventoryID + inventoryName as String
     */
    @Override
    public String toString() {
        return inventoryID + " - " + inventoryName;
    }


}
