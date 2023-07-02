package com.example.oop_group_project_inventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onTestingButtonClick(ActionEvent actionEvent) {

        Grocery a = new Grocery();
        a.loadFromDatabase();

    }
}