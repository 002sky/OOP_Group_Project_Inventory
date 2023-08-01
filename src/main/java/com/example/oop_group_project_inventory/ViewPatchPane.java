package com.example.oop_group_project_inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewPatchPane implements Initializable {

    @FXML
    private TextField TfSearch;
    @FXML
    private TableView TvProductPatch;

    private TableColumn<ProductPatch,String> productPatchTableCloumn[] = new TableColumn[6];





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ProductPatch productPatch = new ProductPatch();

        MainPage.productPatchArrayList.addAll(productPatch.loadFromDatabase());


        FilteredList<ProductPatch> filteredList = new FilteredList<>(FXCollections.observableArrayList(MainPage.productPatchArrayList), p -> true);

        productPatchTableCloumn[0] = new TableColumn<>("Patch Number");
        productPatchTableCloumn[0].setCellValueFactory(new PropertyValueFactory<ProductPatch,String>("patchNumber"));

        productPatchTableCloumn[1] = new TableColumn<>("Supply Source");
        productPatchTableCloumn[1].setCellValueFactory(new PropertyValueFactory<ProductPatch,String>("supplySource"));

        productPatchTableCloumn[2] = new TableColumn<>("Items");
        productPatchTableCloumn[2].setCellValueFactory(new PropertyValueFactory<ProductPatch,String>("items"));

        productPatchTableCloumn[3] = new TableColumn<>("Inventory");
        productPatchTableCloumn[3].setCellValueFactory(new PropertyValueFactory<ProductPatch,String>("storedInventory"));

        productPatchTableCloumn[4] = new TableColumn<>("Received Date");
        productPatchTableCloumn[4].setCellValueFactory(new PropertyValueFactory<ProductPatch,String>("receivedDate"));

        productPatchTableCloumn[5] = new TableColumn<>("Quantity");
        productPatchTableCloumn[5].setCellValueFactory(new PropertyValueFactory<ProductPatch,String>("quantity"));


        TfSearch.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredList.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (product.getPatchNumber().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (product.getSupplySource().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (product.getItems().getProductID().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (product.getStoredInventory().getInventoryID().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            }
            );
        });

        SortedList<ProductPatch> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(TvProductPatch.comparatorProperty());

        for(TableColumn<ProductPatch,String> column : productPatchTableCloumn){
            TvProductPatch.getColumns().add(column);
        }

        TvProductPatch.setItems(sortedList);


    }
}
