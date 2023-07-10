package com.example.oop_group_project_inventory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ErrorMessageWindowController {

    @FXML
    private Label errorMessageLabel;

    public void initialize() {
System.out.println("This is initialized");
        errorMessageLabel.setText("Error: " + AStaticClass.getErrorMessage());

    }
}
