package com.example.oop_group_project_inventory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class AddPatchPane implements Initializable {
    @FXML
    private ComboBox<Product> CbProductBox;
    @FXML
    private ComboBox<Inventory> CbInventoryBox;
    @FXML
    private DatePicker DpReceivedDate;
    @FXML
    private TextField TfPatchNumber, TfQuantity, TfSupplySource;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();

        productList.addAll(MainPage.productArrayList);
        inventoryList.addAll(MainPage.inventoryArrayList);

        CbProductBox.setItems(productList);
        CbInventoryBox.setItems(inventoryList);

    }


    public void SavePatch(MouseEvent event) {
        Product product = CbProductBox.getSelectionModel().getSelectedItem();

        System.out.println(product.getProductID());
    }
}
