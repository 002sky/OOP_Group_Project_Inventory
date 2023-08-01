package com.example.oop_group_project_inventory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPage implements Initializable {

    public static ArrayList<Product> productArrayList = new ArrayList<>();
    public static ArrayList<Inventory> inventoryArrayList = new ArrayList<>();

    public static ArrayList<ProductPatch> productPatchArrayList = new ArrayList<>();

    public static  ArrayList<Order> orderArrayList = new ArrayList<>();

    @FXML
    private ScrollPane ContentPane;
    @FXML
    private BorderPane mainPage;

    public HashMap<String, Pane> ContentMap = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeData();

        try {
            ContentMap.put("AddProduct", FXMLLoader.load(Objects.requireNonNull(inventoryApplication.class.getResource("AddProductPane.fxml"))));
            ContentMap.put("ViewProduct", FXMLLoader.load(Objects.requireNonNull(inventoryApplication.class.getResource("ViewProduct.fxml"))));
            ContentMap.put("UpdateProduct", FXMLLoader.load(Objects.requireNonNull(inventoryApplication.class.getResource("UpdateProductPane.fxml"))));
            ContentMap.put("AddInventory", FXMLLoader.load(Objects.requireNonNull(inventoryApplication.class.getResource("AddInventoryPane.fxml"))));
            ContentMap.put("EditInventory", FXMLLoader.load(Objects.requireNonNull(inventoryApplication.class.getResource("UpdateInventoryPane.fxml"))));
            ContentMap.put("AddPatch", FXMLLoader.load(Objects.requireNonNull(inventoryApplication.class.getResource("AddPatchPane.fxml"))));
            ContentMap.put("ViewPatch", FXMLLoader.load(Objects.requireNonNull(inventoryApplication.class.getResource("ViewPatchPane.fxml"))));
            ContentMap.put("AddOrder", FXMLLoader.load(Objects.requireNonNull(inventoryApplication.class.getResource("AddOrderPane.fxml"))));
            ContentMap.put("ViewOrder", FXMLLoader.load(Objects.requireNonNull(inventoryApplication.class.getResource("ViewOrderPane.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    protected Pane activate(String Name) {
        return ContentMap.get(Name);
    }

    public void LoadAddProduct(MouseEvent event) {
        ContentPane.setContent(activate("AddProduct"));
    }

    public void LoadViewProduct(MouseEvent event) {
        ContentPane.setContent(activate("ViewProduct"));
    }

    public void LoadUpdateProduct(MouseEvent event) {
        ContentPane.setContent(activate("UpdateProduct"));
    }

    public void LoadAddInventory(MouseEvent event) {
        ContentPane.setContent(activate("AddInventory"));
    }

    public void LoadUpdateInventory(MouseEvent event) {
        ContentPane.setContent(activate("EditInventory"));
    }

    public void LoadAddPatch(MouseEvent event) {
        ContentPane.setContent(activate("AddPatch"));
    }

    public void LoadViewPatch(MouseEvent event) {
        ContentPane.setContent(activate("ViewPatch"));
    }

    public void loadAddOrder(MouseEvent event) {
        ContentPane.setContent(activate("AddOrder" ));
    }

    public void loadViewOrder(MouseEvent event) {
        ContentPane.setContent(activate("ViewOrder"));
    }


    public void initializeData(){
        ArrayList<Clothing> tmpClothingArrayList = new ArrayList<>();

        Clothing clothing = new Clothing();
        tmpClothingArrayList.addAll(clothing.loadFromDatabase());


        ArrayList<Electronic> ElectronicList = new ArrayList<>();

        Electronic electronic = new Electronic();
        ElectronicList.addAll(electronic.loadFromDatabase());

        Grocery grocery = new Grocery();
        ArrayList<Grocery> groceries = new ArrayList<>();
        groceries.addAll(grocery.loadFromDatabase());

        Inventory inventory = new Inventory();
        inventoryArrayList.addAll(inventory.getAllInventory());
        productArrayList.addAll(tmpClothingArrayList);
        productArrayList.addAll(ElectronicList);
        productArrayList.addAll(groceries);

        Order order = new Order();
        orderArrayList.addAll(order.getAllOrders());
    }

}
