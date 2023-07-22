package com.example.oop_group_project_inventory;

import java.time.LocalDate;
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

    public void setBatchNumber(String PatchNumber) {
        this.PatchNumber = PatchNumber;
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

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
