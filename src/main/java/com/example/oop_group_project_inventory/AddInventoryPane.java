package com.example.oop_group_project_inventory;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddInventoryPane {

    @FXML
    private TextField TfInventoryID, TfInventoryName;
    @FXML
    private TextArea TaInventoryAddress;
    @FXML
    private CheckBox CbFreezerAvailable;
    @FXML
    private Button BtnSubmit;

    public void AddInventory(MouseEvent event) {
        Inventory inventory = new Inventory();
        if (!inventory.checkInventoryExists(TfInventoryID.getText())) {

            // TODO validation of the Textfield
            inventory = new Inventory(TfInventoryID.getText(), TfInventoryName.getText(), TaInventoryAddress.getText(), CbFreezerAvailable.isSelected());
            MainPage.inventoryArrayList.add(inventory);
            inventory.addInventory(inventory);
        } else {
            System.out.println("Inventory already exists");
        }

        // TODO Error Message here
    }


}
