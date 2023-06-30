package com.example.oop_group_project_inventory;

public class Inventory {

    private String inventoryID;
    private String inventoryName;
    private String inventoryLocation;
    private boolean freezerAvailable;
    private int capacity;

    protected Inventory() {

    }

    protected Inventory(String inventoryID, String inventoryName, String inventoryLocation, boolean freezerAvailable, int capacity) {
        this.inventoryID = inventoryID;
        this.inventoryName = inventoryName;
        this.inventoryLocation = inventoryLocation;
        this.freezerAvailable = freezerAvailable;
        this.capacity = capacity;
    }


    public String getInventoryID() {
        return inventoryID;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public String getInventoryLocation() {
        return inventoryLocation;
    }

    public boolean isFreezerAvailable() {
        return freezerAvailable;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setInventoryID(String inventoryID) {
        this.inventoryID = inventoryID;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public void setInventoryLocation(String inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setFreezerAvailable(boolean freezerAvailable) {
        this.freezerAvailable = freezerAvailable;
    }


}
