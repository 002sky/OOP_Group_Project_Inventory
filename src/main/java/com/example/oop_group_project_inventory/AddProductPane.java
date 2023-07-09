package com.example.oop_group_project_inventory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
    private TextField TfProductID, TfProductName, TfUnitPrice, TfSellingPrice, TfProductBrand, TfElectronicColor, TfModel, TfClothingType, tfClothingSize, TfClothingColor, TfClothingMaterial;

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




    public void SubmitToDatabase(MouseEvent event) {

        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";

        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            String sql = "INSERT INTO";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);


        }catch (Exception e){

        }


    }
}
