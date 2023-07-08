package com.example.oop_group_project_inventory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainPage {

    @FXML
    private Pane ContentPane;
    @FXML
    private BorderPane mainPage;


    @FXML
    public void LoadAddProduct(MouseEvent event) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(inventoryApplication.class.getResource("AddProductPane.fxml"));

        if (!ContentPane.getChildren().isEmpty()) {
            ContentPane.getChildren().clear();
        }

        ContentPane = fxmlLoader.load();
        mainPage.setCenter(ContentPane);

    }


    public void loadViewProduct(MouseEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(inventoryApplication.class.getResource("ViewProduct.fxml"));

        if (!ContentPane.getChildren().isEmpty()) {
            ContentPane.getChildren().clear();
        }

        ContentPane = fxmlLoader.load();
        mainPage.setCenter(ContentPane);
    }
}
