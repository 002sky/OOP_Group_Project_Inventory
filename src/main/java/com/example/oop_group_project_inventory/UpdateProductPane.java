package com.example.oop_group_project_inventory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;



import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class UpdateProductPane implements Initializable {
    ArrayList<Product> productArrayList = new ArrayList<>();
    ArrayList<String> autoComplete = new ArrayList<>();

    @FXML
    private TextField TfProductID, TfProductName, TfUnitPrice, TfSellingPrice, TfProductBrand, TfElectronicColor, TfModel, TfClothingType, TfClothingSize, TfClothingColor, TfClothingMaterial;

    public void UpdateProduct(MouseEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Clothing> tmpClothingArrayList = new ArrayList<>();

        Clothing clothing = new Clothing();
        tmpClothingArrayList.addAll(clothing.loadFromDatabase());


        ArrayList<Electronic> ElectronicList = new ArrayList<>();

        Electronic electronic = new Electronic();
        ElectronicList.addAll(electronic.loadFromDatabase());
        productArrayList.addAll(tmpClothingArrayList);
        productArrayList.addAll(ElectronicList);


        productArrayList.forEach((n) -> autoComplete.add(n.getProductID()));
        AutoCompletionBinding ProductID = TextFields.bindAutoCompletion(TfProductID,autoComplete);





    }


    public void suggestion(KeyEvent keyEvent) {



    }
}
