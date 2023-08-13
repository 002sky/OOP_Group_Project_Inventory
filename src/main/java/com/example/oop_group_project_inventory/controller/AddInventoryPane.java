package com.example.oop_group_project_inventory.controller;

import com.example.oop_group_project_inventory.model.Inventory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddInventoryPane implements Initializable {

    @FXML
    private TextField TfInventoryID, TfInventoryName;
    @FXML
    private TextArea TaInventoryAddress;
    @FXML
    private CheckBox CbFreezerAvailable;
    @FXML
    private Button BtnSubmit;
    @FXML
    private Label lblinventoryIDErrMsg, lblinventoryNameErrMsg, lblinventoryAddressErrMsg;
    public Stage primaryStage;

    /**
     * initialize TextField TfInventoryID with listener which will check whether the inventory id already exist
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TfInventoryID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (checkExistInventory(newValue) != -1) {
                lblinventoryIDErrMsg.setText("The inventory id is already exist");
            } else {
                lblinventoryIDErrMsg.setText("");
            }
        });
        resetLBLErrMsg();

    }

    /**
     * resetLBLErrMsg function to set every error messages empty
     */
    //set every error messages empty
    public void resetLBLErrMsg() {
        lblinventoryIDErrMsg.setText("");
        lblinventoryNameErrMsg.setText("");
        lblinventoryAddressErrMsg.setText("");

        lblinventoryIDErrMsg.setTextFill(Color.color(1, 0, 0));
        lblinventoryNameErrMsg.setTextFill(Color.color(1, 0, 0));
        lblinventoryAddressErrMsg.setTextFill(Color.color(1, 0, 0));
    }

    /**
     * check and return whether got existing inventory
     * @param inventoryID accept inventoryID as String
     * @return return inventoryID as int
     */
    //check and return whether got existing inventory
    public int checkExistInventory(String inventoryID) {
        int[] a = new int[1];
        a[0] = -1;
        MainPage.inventoryArrayList.forEach((n) -> {
            if (n.getInventoryID().equalsIgnoreCase(inventoryID)) {
                a[0] = MainPage.inventoryArrayList.indexOf(n);
            }
        });

        return a[0];
    }

    /**
     * clearData function to clear all text field
     */
    //clear all text field
    public void clearData() {
        resetLBLErrMsg();
        TfInventoryID.setText("");
        TfInventoryName.setText("");
        TaInventoryAddress.setText("");
    }

    /**
     * AddInventory function to add inventory to the database using the inventory class
     * with listener which will check whether the inventory id already exist
     * if inventory id already exist then show pop up error message
     * @throws IOException
     */
    //add inventory function
    public void AddInventory(MouseEvent event) throws IOException {
        Inventory inventory = new Inventory();
        //get checking result
        boolean check = validation(event);
        if (check) {

            if (!inventory.checkInventoryExists(TfInventoryID.getText())) {

                // TODO validation of the Textfield
                inventory = new Inventory(TfInventoryID.getText(), TfInventoryName.getText(), TaInventoryAddress.getText(), CbFreezerAvailable.isSelected());
                MainPage.inventoryArrayList.add(inventory);
                inventory.addInventory(inventory);
                clearData();

            } else {
                //Show pop up error message
                primaryStage = new Stage();

                primaryStage.setTitle("Error");
                final Popup popup = new Popup();

                popup.setX(300);
                popup.setY(200);

                Button show = new Button("OK");
                show.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
//                            popup.show(primaryStage);
                        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                    }
                });

                Label errorMessage = new Label("Inventory already exists");


                VBox layout = new VBox(10);
                layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
                layout.getChildren().addAll(show, errorMessage);
                layout.setAlignment(Pos.CENTER);
                primaryStage.setScene(new Scene(layout, 400, 400));
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.setResizable(false);
                primaryStage.show();

            }

        }

        // TODO Error Message here
    }

    /**
     * validation function to validate every text field
     * @return return boolean value to determine whether the text field is valid
     * @throws IOException
     */
    //validation for every text field
    public boolean validation(MouseEvent event) throws IOException {
        boolean validate = true;
        if (!TfInventoryID.getText().isEmpty()) {
            if (checkExistInventory(TfInventoryID.getText()) != -1) {
                String errorMessage = "Inventory ID is already exist";
                lblinventoryIDErrMsg.setText(errorMessage);
                validate = false;
            } else {
                lblinventoryIDErrMsg.setText("");
                String errorMessage = "";
                if (TfInventoryName.getText().isEmpty()) {
                    errorMessage = "Inventory Name is empty";
                    lblinventoryNameErrMsg.setText(errorMessage);
                    validate = false;
                } else {
                    lblinventoryNameErrMsg.setText("");
                }

                if (TaInventoryAddress.getText().isEmpty()) {
                    errorMessage = "Inventory Address is empty";
                    lblinventoryAddressErrMsg.setText(errorMessage);
                    validate = false;
                } else {
                    lblinventoryAddressErrMsg.setText("");
                }
            }
        } else {
            String errorMessage = "Please enter the inventory id";
            lblinventoryIDErrMsg.setText(errorMessage);
            validate = false;
        }

        return validate;
    }


}
