package com.example.oop_group_project_inventory;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import javafx.scene.control.*;


public class UpdateInventoryPane implements Initializable {
    @FXML
    private TextField TfInventoryID, TfInventoryName;
    @FXML
    private TextArea TaInventoryAddress;
    @FXML
    private CheckBox CbFreezerAvailable;
    @FXML
    private Button BtnSubmit;

    private String currentItem;
    private ArrayList<String> autoComplete = new ArrayList<>();
    @FXML
    private Label lblinventoryIDErrMsg, lblinventoryNameErrMsg, lblinventoryAddressErrMsg;
    public Stage primaryStage;

    //set every error messages empty
    public void resetLBLErrMsg() {
        lblinventoryIDErrMsg.setText("");
        lblinventoryNameErrMsg.setText("");
        lblinventoryAddressErrMsg.setText("");

        lblinventoryIDErrMsg.setTextFill(Color.color(1, 0, 0));
        lblinventoryNameErrMsg.setTextFill(Color.color(1, 0, 0));
        lblinventoryAddressErrMsg.setTextFill(Color.color(1, 0, 0));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainPage.inventoryArrayList.forEach((m) -> autoComplete.add(m.getInventoryID()));
        resetLBLErrMsg();

        TextFields.bindAutoCompletion(TfInventoryID, autoComplete);

        TfInventoryID.textProperty().addListener((observable, oldValue, newValue) -> {
            autoComplete.clear();
            MainPage.inventoryArrayList.forEach((m) -> autoComplete.add(m.getInventoryID()));
            TextFields.bindAutoCompletion(TfInventoryID, autoComplete);


            if (checkExistInventory(newValue) != -1) {
                Inventory inventory = MainPage.inventoryArrayList.get(checkExistInventory(newValue));
                TfInventoryID.setText(inventory.getInventoryID());
                TfInventoryName.setText(inventory.getInventoryName());
                TaInventoryAddress.setText(inventory.getInventoryLocation());
                CbFreezerAvailable.setSelected(inventory.isFreezerAvailable());
                resetLBLErrMsg();

            }
        });

    }

    //validation for every text field
    public boolean validation() throws IOException {
        boolean validate = true;
        if (!TfInventoryID.getText().isEmpty()) {
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

        } else {
            String errorMessage = "Please enter the inventory id";
            lblinventoryIDErrMsg.setText(errorMessage);
            validate = false;
        }

        return validate;
    }

    //check and return whether got existing inventory
    public int checkExistInventory(String InventoryID) {
        int[] a = new int[1];
        a[0] = -1;
        MainPage.inventoryArrayList.forEach((n) -> {
            if (n.getInventoryID().equalsIgnoreCase(InventoryID)) {
                a[0] = MainPage.inventoryArrayList.indexOf(n);
            }
        });
        return a[0];
    }

    ////update inventory
    public void UpdateInventory(MouseEvent event) throws IOException {
        boolean hasError = false;

        if (validation() == true) {
            Inventory inventory = MainPage.inventoryArrayList.get(checkExistInventory(TfInventoryID.getText()));

            MainPage.inventoryArrayList.get(checkExistInventory(TfInventoryID.getText())).setInventoryName(TfInventoryName.getText());
            MainPage.inventoryArrayList.get(checkExistInventory(TfInventoryID.getText())).setInventoryLocation(TaInventoryAddress.getText());
            MainPage.inventoryArrayList.get(checkExistInventory(TfInventoryID.getText())).setFreezerAvailable(CbFreezerAvailable.isSelected());

            hasError = inventory.updateInventory(MainPage.inventoryArrayList.get(checkExistInventory(TfInventoryID.getText())));

            //TODO: error message

            //Show pop up error message


            primaryStage = new Stage();
            primaryStage.setTitle(hasError == true ? "Error" : "Success");
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

            Label errorMessage = new Label(hasError == true ? "There is an error happened" : "Your inventory has been updated successfully");


            VBox layout = new VBox(10);
            layout.setStyle(hasError == true ? "-fx-background-color: cornsilk; -fx-padding: 10;" : "-fx-background-color: lightgreen; -fx-padding: 10;");
            layout.getChildren().addAll(show, errorMessage);
            layout.setAlignment(Pos.CENTER);
            primaryStage.setScene(new Scene(layout, 400, 400));
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.show();


            autoComplete.clear();
            MainPage.productArrayList.forEach((m) -> autoComplete.add(m.getProductID()));
            TextFields.bindAutoCompletion(TfInventoryID, autoComplete);

        }

    }

}
