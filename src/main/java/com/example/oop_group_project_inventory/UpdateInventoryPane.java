package com.example.oop_group_project_inventory;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import javafx.scene.input.MouseEvent;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainPage.inventoryArrayList.forEach((m) -> autoComplete.add(m.getInventoryID()));

        TextFields.bindAutoCompletion(TfInventoryID, autoComplete);

        TfInventoryID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (checkExistInventory(newValue) != -1) {
                Inventory inventory = MainPage.inventoryArrayList.get(checkExistInventory(newValue));
                TfInventoryID.setText(inventory.getInventoryID());
                TfInventoryName.setText(inventory.getInventoryName());
                TaInventoryAddress.setText(inventory.getInventoryLocation());
                CbFreezerAvailable.setSelected(inventory.isFreezerAvailable());
            }
        });

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

    public void UpdateInventory(MouseEvent event) {


    }
}
