package com.example.oop_group_project_inventory;

import java.util.Date;

public class ProductBatch {
    private int batchNumber;
    private String supplySource;
    private Product items;
    private Inventory storedInventory;
    private Date receivedDate;
    private int quantity;

    protected ProductBatch() {

    }

    protected ProductBatch(int batchNumber, String supplySource, Product items, Inventory storedInventory, Date receivedDate, int quantity) {
        this.batchNumber = batchNumber;
        this.supplySource = supplySource;
        this.items = items;
        this.storedInventory = storedInventory;
        this.receivedDate = receivedDate;
        this.quantity = quantity;

    }

    public int getBatchNumber() {
        return batchNumber;
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

    public Date getReceivedDate() {
        return receivedDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    public void setSupplySource(String supplySource) {
        this.supplySource = supplySource;
    }

    public void setItems(Product items) {
        this.items = items;
    }

    public void setStoredInventory(Inventory storedInventory) {
        this.storedInventory = storedInventory;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
