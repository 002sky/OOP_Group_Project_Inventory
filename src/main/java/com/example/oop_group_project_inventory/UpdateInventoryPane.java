package com.example.oop_group_project_inventory;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
            }
        });

    }

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

    public void UpdateInventory(MouseEvent event) throws IOException {
        boolean hasError = false;

        if (validation() == true) {
            Inventory inventory = MainPage.inventoryArrayList.get(checkExistInventory(TfInventoryID.getText()));

            MainPage.inventoryArrayList.get(checkExistInventory(TfInventoryID.getText())).setInventoryName(TfInventoryName.getText());
            MainPage.inventoryArrayList.get(checkExistInventory(TfInventoryID.getText())).setInventoryLocation(TaInventoryAddress.getText());
            MainPage.inventoryArrayList.get(checkExistInventory(TfInventoryID.getText())).setFreezerAvailable(CbFreezerAvailable.isSelected());

            hasError = inventory.updateInventory(MainPage.inventoryArrayList.get(checkExistInventory(TfInventoryID.getText())));

            //TODO: error message

        }
    }

}
