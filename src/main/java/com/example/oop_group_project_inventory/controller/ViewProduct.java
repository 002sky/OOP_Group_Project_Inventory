package com.example.oop_group_project_inventory.controller;

import com.example.oop_group_project_inventory.model.Clothing;
import com.example.oop_group_project_inventory.model.Electronic;
import com.example.oop_group_project_inventory.model.Grocery;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewProduct implements Initializable {

    @FXML
    private TableView<Electronic> ElectronicTableView;
    @FXML
    private TableView<Grocery> GroceryTableView;
    @FXML
    private TableView<Clothing> ClothingTableView;
    @FXML
    private ComboBox CbViewProductType;
    private TableColumn<Grocery, String> groceryTableCloumn[] = new TableColumn[7];
    private TableColumn<Electronic, String> electronicTableCloumn[] = new TableColumn[8];
    private TableColumn<Clothing, String> clothingTableCloumn[] = new TableColumn[10];

    private ObservableList<Grocery> GroceryData;
    private ObservableList<Electronic> ElectronicData;
    private ObservableList<Clothing> ClothingData;


    /**
     * initialize the tableview TvProduct, TvElectronic, TvClothing
     * based on the type of product selected in the cbViewProductType
     * the tableview will be change accordingly
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        groceryTableCloumn[0] = new TableColumn<>("Product ID");
        groceryTableCloumn[0].setCellValueFactory(new PropertyValueFactory<Grocery, String>("productID"));

        groceryTableCloumn[1] = new TableColumn<>("Product Name");
        groceryTableCloumn[1].setCellValueFactory(new PropertyValueFactory<Grocery, String>("ProductName"));

        groceryTableCloumn[2] = new TableColumn<>("Unit Price");
        groceryTableCloumn[2].setCellValueFactory(new PropertyValueFactory<Grocery, String>("unitPrice"));

        groceryTableCloumn[3] = new TableColumn<>("Selling Price");
        groceryTableCloumn[3].setCellValueFactory(new PropertyValueFactory<Grocery, String>("sellingPrice"));

        groceryTableCloumn[4] = new TableColumn<>("Product Status");
        groceryTableCloumn[4].setCellValueFactory(new PropertyValueFactory<Grocery, String>("productStatus"));


        groceryTableCloumn[5] = new TableColumn<>("Product Brand");
        groceryTableCloumn[5].setCellValueFactory(new PropertyValueFactory<Grocery, String>("productBrand"));

        groceryTableCloumn[6] = new TableColumn<>("category");
        groceryTableCloumn[6].setCellValueFactory(new PropertyValueFactory<Grocery, String>("category"));


        electronicTableCloumn[0] = new TableColumn<>("Product ID");
        electronicTableCloumn[0].setCellValueFactory(new PropertyValueFactory<Electronic, String>("productID"));

        electronicTableCloumn[1] = new TableColumn<>("Product Name");
        electronicTableCloumn[1].setCellValueFactory(new PropertyValueFactory<Electronic, String>("productName"));

        electronicTableCloumn[2] = new TableColumn<>("Unit Price");
        electronicTableCloumn[2].setCellValueFactory(new PropertyValueFactory<Electronic, String>("unitPrice"));

        electronicTableCloumn[3] = new TableColumn<>("Selling Price");
        electronicTableCloumn[3].setCellValueFactory(new PropertyValueFactory<Electronic, String>("sellingPrice"));

        electronicTableCloumn[4] = new TableColumn<>("Product Status");
        electronicTableCloumn[4].setCellValueFactory(new PropertyValueFactory<Electronic, String>("productStatus"));

        electronicTableCloumn[5] = new TableColumn<>("Product Brand");
        electronicTableCloumn[5].setCellValueFactory(new PropertyValueFactory<Electronic, String>("productBrand"));

        electronicTableCloumn[6] = new TableColumn<>("Color");
        electronicTableCloumn[6].setCellValueFactory(new PropertyValueFactory<Electronic, String>("color"));

        electronicTableCloumn[7] = new TableColumn<>("Models");
        electronicTableCloumn[7].setCellValueFactory(new PropertyValueFactory<Electronic, String>("model"));

        clothingTableCloumn[0] = new TableColumn<>("Product ID");
        clothingTableCloumn[0].setCellValueFactory(new PropertyValueFactory<Clothing, String>("productID"));

        clothingTableCloumn[1] = new TableColumn<>("Product Name");
        clothingTableCloumn[1].setCellValueFactory(new PropertyValueFactory<Clothing, String>("productName"));

        clothingTableCloumn[2] = new TableColumn<>("Unit Price");
        clothingTableCloumn[2].setCellValueFactory(new PropertyValueFactory<Clothing, String>("unitPrice"));

        clothingTableCloumn[3] = new TableColumn<>("Selling Price");
        clothingTableCloumn[3].setCellValueFactory(new PropertyValueFactory<Clothing, String>("sellingPrice"));

        clothingTableCloumn[4] = new TableColumn<>("Product Status");
        clothingTableCloumn[4].setCellValueFactory(new PropertyValueFactory<Clothing, String>("productStatus"));

        clothingTableCloumn[5] = new TableColumn<>("Product Brand");
        clothingTableCloumn[5].setCellValueFactory(new PropertyValueFactory<Clothing, String>("productBrand"));

        clothingTableCloumn[6] = new TableColumn<>("Color");
        clothingTableCloumn[6].setCellValueFactory(new PropertyValueFactory<Clothing, String>("color"));

        clothingTableCloumn[7] = new TableColumn<>("Material");
        clothingTableCloumn[7].setCellValueFactory(new PropertyValueFactory<Clothing, String>("material"));

        clothingTableCloumn[8] = new TableColumn<>("Size");
        clothingTableCloumn[8].setCellValueFactory(new PropertyValueFactory<Clothing, String>("clothingSize"));

        clothingTableCloumn[9] = new TableColumn<>("Type");
        clothingTableCloumn[9].setCellValueFactory(new PropertyValueFactory<Clothing, String>("ClothingType"));

        for (TableColumn<Grocery, String> tableColumn : groceryTableCloumn) {
            GroceryTableView.getColumns().add(tableColumn);
        }

        for (TableColumn<Electronic, String> tableColumn : electronicTableCloumn) {
            ElectronicTableView.getColumns().add(tableColumn);
        }

        for (TableColumn<Clothing, String> tableColumn : clothingTableCloumn) {
            ClothingTableView.getColumns().add(tableColumn);
        }


        String productType[] = {"Grocery", "Electronic", "Clothing"};

        CbViewProductType.setItems(FXCollections.observableArrayList(productType));

        CbViewProductType.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if (t1.toString().equalsIgnoreCase("Grocery")) {
                    buildData(t1.toString());
                    GroceryTableView.setVisible(true);
                    ElectronicTableView.setVisible(false);
                    ClothingTableView.setVisible(false);

                } else if (t1.toString().equalsIgnoreCase("Electronic")) {
                    buildData(t1.toString());
                    GroceryTableView.setVisible(false);
                    ClothingTableView.setVisible(false);
                    ElectronicTableView.setVisible(true);
                } else if (t1.toString().equalsIgnoreCase("Clothing")) {
                    buildData(t1.toString());
                    GroceryTableView.setVisible(false);
                    ClothingTableView.setVisible(true);
                    ElectronicTableView.setVisible(false);
                } else {
                    GroceryTableView.setVisible(false);
                    ClothingTableView.setVisible(false);
                    ElectronicTableView.setVisible(false);
                }
            }
        });
    }


    /**
     * this method is used to populate the tableview based on the product type selected
     * @param ProductType accepted product type as parameter
     */
    public void buildData(String ProductType) {

        try {
            if (ProductType.equalsIgnoreCase("Grocery")) {
                GroceryData = FXCollections.observableArrayList();
                GroceryData.addAll(new Grocery().loadFromDatabase());
                GroceryTableView.setItems(GroceryData);

            } else if (ProductType.equalsIgnoreCase("Electronic")) {
                ElectronicData = FXCollections.observableArrayList();
                ElectronicData.addAll(new Electronic().loadFromDatabase());

                ElectronicTableView.setItems(ElectronicData);
            } else if (ProductType.equalsIgnoreCase("Clothing")) {
                ClothingData = FXCollections.observableArrayList();

                ClothingData.addAll(new Clothing().loadFromDatabase());

                ClothingTableView.setItems(ClothingData);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
