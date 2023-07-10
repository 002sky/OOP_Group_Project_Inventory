package com.example.oop_group_project_inventory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;


public class AddProductPane implements Initializable {
    @FXML
    private ComboBox productBox;

    @FXML
    private GridPane Grocery, Electronic, Clothing;

    @FXML
    private TextField TfProductID, TfProductName, TfUnitPrice, TfSellingPrice, TfProductBrand, TfElectronicColor, TfModel, TfClothingType, TfClothingSize, TfClothingColor, TfClothingMaterial;

    @FXML
    private CheckBox CbProductStatus;

    @FXML
    private DatePicker DpExpiryDate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String productType[] = {"Grocery", "Electronic", "Clothing"};

        productBox.setItems(FXCollections.observableArrayList(productType));

        productBox.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {

                if (t1.toString().equalsIgnoreCase("Grocery")) {
                    Grocery.setVisible(true);
                    Electronic.setVisible(false);
                    Clothing.setVisible(false);

                } else if (t1.toString().equalsIgnoreCase("Electronic")) {
                    Grocery.setVisible(false);
                    Clothing.setVisible(false);
                    Electronic.setVisible(true);
                } else if (t1.toString().equalsIgnoreCase("Clothing")) {
                    Grocery.setVisible(false);
                    Clothing.setVisible(true);
                    Electronic.setVisible(false);
                } else {
                    Grocery.setVisible(false);
                    Clothing.setVisible(false);
                    Electronic.setVisible(false);
                }


            }
        });
    }


    public boolean validation() {
        boolean validate = true;
        if (!productBox.getSelectionModel().isEmpty()) {

            if (TfProductID.getText().isEmpty()) {
                System.out.println("product ID is empty");
                validate = false;
            }
            if (TfProductName.getText().isEmpty()) {
                System.out.println("product name is empty");
                validate = false;
            }
            if (TfUnitPrice.getText().isEmpty()) {
                System.out.println("product unit price is empty");
                validate = false;
            }
            if (TfSellingPrice.getText().isEmpty()) {
                System.out.println("product selling price is empty");
                validate = false;
            }
            if (TfProductBrand.getText().isEmpty()) {
                System.out.println("product brand is empty");
                validate = false;
            }
            if (productBox.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Clothing")) {
                if (TfClothingColor.getText().isEmpty()) {
                    System.out.println("clothing color is empty");
                    validate = false;
                }
                if (TfClothingMaterial.getText().isEmpty()) {
                    System.out.println("clothing material is empty");
                    validate = false;
                }
                if (TfClothingSize.getText().isEmpty()) {
                    System.out.println("clothing size is empty");
                    validate = false;
                }
                if (TfClothingType.getText().isEmpty()) {
                    System.out.println("clothing size is empty");
                    validate = false;
                }
            }
            if (productBox.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Grocery")) {
                if (DpExpiryDate.getValue() == null) {
                    System.out.println("expiry date is empty");
                    validate = false;
                }
            }
            if (productBox.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Electronic")) {
                if (TfElectronicColor.getText().isEmpty()) {
                    System.out.println(" color is empty");
                    validate = false;
                }
                if (TfModel.getText().isEmpty()) {
                    System.out.println("model is empty");
                    validate = false;
                }
            }

        } else {
            System.out.println("please select the product type");
            validate = false;
        }

        return validate;
    }


    public void SubmitToDatabase(MouseEvent event) {
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";

        boolean check = validation();

        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            if (check) {
                String sqlProduct = "INSERT INTO Product (ProductID, ProductName, UnitPrice, SellingPrice, ProductBrand) VALUES ('" + TfProductID.getText() + "', '" + TfProductName.getText() + "', '"  + TfUnitPrice.getText() + "', '" + TfSellingPrice.getText() + "', '" + TfProductBrand.getText() + "')";
                String sql = "";

                Statement statement = connection.createStatement();
                int result = statement.executeUpdate(sqlProduct);

                if (result > 0) {
                    if (productBox.getValue().toString().equalsIgnoreCase("Clothing")) {
                        sql = "INSERT INTO Clothing (ProductID, color, material, clothingSize,clothingType) VALUES ('" + TfProductID.getText() + "', '" + TfClothingColor.getText() + "', '" + TfClothingMaterial.getText() + "', '" + TfClothingSize.getText() + "', '" + TfClothingType.getText() + "')";
                    } else if (productBox.getValue().toString().equalsIgnoreCase("Grocery")) {
                        sql = "INSERT INTO Grocery (ProductID, expiry_date) VALUES ('" + TfProductID.getText() + "', '" + DpExpiryDate.getValue() + "')";
                    } else if (productBox.getValue().toString().equalsIgnoreCase("Electronic")) {
                        sql = "INSERT INTO Electronic (ProductID, Color, models) VALUES ('" + TfProductID.getText() + "', '" + TfElectronicColor.getText() + "', '" + TfModel.getText() + "')";
                    }

                    statement.executeUpdate(sql);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
