package com.example.oop_group_project_inventory.controller;

import com.example.oop_group_project_inventory.*;
import com.example.oop_group_project_inventory.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

    /**
     * initialize the main page and set the contentMap
     * load data from database into the static ArrayList productArrayList, inventoryArrayList, orderArrayList
     * load the pane from the fxml file and put it into the contentMap
     */
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

    /**
     * activate the pane
     * @param Name accept the name of the pane as parameter
     * @return return the pane object with the name
     */
    protected Pane activate(String Name) {
        return ContentMap.get(Name);
    }

    /**
     * load the add product pane
     */
    public void LoadAddProduct(MouseEvent event) {
        ContentPane.setContent(activate("AddProduct"));
    }

    /**
     * load the view product pane
     */
    public void LoadViewProduct(MouseEvent event) {
        ContentPane.setContent(activate("ViewProduct"));
    }

    /**
     * load the update product pane
     */
    public void LoadUpdateProduct(MouseEvent event) {
        ContentPane.setContent(activate("UpdateProduct"));
    }

    /**
     * load the add inventory pane
     */
    public void LoadAddInventory(MouseEvent event) {
        ContentPane.setContent(activate("AddInventory"));
    }

    /**
     * load the update inventory pane
     */
    public void LoadUpdateInventory(MouseEvent event) {
        ContentPane.setContent(activate("EditInventory"));
    }

    /**
     * load the add patch pane
     */
    public void LoadAddPatch(MouseEvent event) {
        ContentPane.setContent(activate("AddPatch"));
    }

    /**
     * load the view patch pane
     */
    public void LoadViewPatch(MouseEvent event) {
        ContentPane.setContent(activate("ViewPatch"));
    }

    /**
     * load the add order pane
     */
    public void loadAddOrder(MouseEvent event) {
        ContentPane.setContent(activate("AddOrder" ));
    }

    /**
     * load the view order pane
     */
    public void loadViewOrder(MouseEvent event) {
        ContentPane.setContent(activate("ViewOrder"));
    }


    /**
     * initialize the data of the static ArrayList by loading the data from the database with corresponding class
     */
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
