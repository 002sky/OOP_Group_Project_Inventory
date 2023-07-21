package com.example.oop_group_project_inventory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.textfield.TextFields;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class UpdateProductPane implements Initializable {

    @FXML
    private GridPane Grocery, Electronic, Clothing;
    @FXML
    private Label productBox;

    @FXML
    private CheckBox CbProductStatus;
    @FXML
    private GridPane mainGP;
    @FXML
    private TextField TfProductID, TfProductName, TfUnitPrice, TfSellingPrice, TfProductBrand, TfElectronicColor, TfModel, TfClothingType, TfClothingSize, TfClothingColor, TfClothingMaterial, TfCategory;
    public ArrayList<String> autoComplete = new ArrayList<>();
    public String currentItem;



    public void UpdateProduct(MouseEvent event) {

        if(checkExitProduct(currentItem) != -1){

            MainPage.productArrayList.get(checkExitProduct(currentItem)).setProductName(TfProductName.getText());
            MainPage.productArrayList.get(checkExitProduct(currentItem)).setUnitPrice(Double.parseDouble(TfUnitPrice.getText()));
            MainPage.productArrayList.get(checkExitProduct(currentItem)).setSellingPrice(Double.parseDouble(TfSellingPrice.getText()));
            MainPage.productArrayList.get(checkExitProduct(currentItem)).setProductBrand(TfProductBrand.getText());
            MainPage.productArrayList.get(checkExitProduct(currentItem)).setProductStatus(CbProductStatus.isSelected());
            if(MainPage.productArrayList.get(checkExitProduct(currentItem)).getClass().equals(Electronic.class)){
                Electronic e = (Electronic) MainPage.productArrayList.get(checkExitProduct(currentItem));
                e.setColor(TfElectronicColor.getText());
                e.setModel(TfModel.getText());
                e.updateProduct(e);

            }else if(MainPage.productArrayList.get(checkExitProduct(currentItem)).getClass().equals(Grocery.class)){
                Grocery g = (Grocery) MainPage.productArrayList.get(checkExitProduct(currentItem));
                g.setCategory(TfCategory.getText());
                g.updateProduct(g);

            }else if(MainPage.productArrayList.get(checkExitProduct(currentItem)).getClass().equals(Clothing.class)){

                Clothing c = (Clothing) MainPage.productArrayList.get(checkExitProduct(currentItem));
                c.setColor(TfClothingColor.getText());
                c.setMaterial(TfClothingMaterial.getText());
                c.setClothingSize(TfClothingSize.getText());
                c.setClothingType(TfClothingType.getText());

                c.updateProduct(c);
            }


            autoComplete.clear();
            MainPage.productArrayList.forEach((m) -> autoComplete.add(m.getProductID()));
            TextFields.bindAutoCompletion(TfProductID, autoComplete);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MainPage.productArrayList.forEach((n) -> autoComplete.add(n.getProductID()));
        TextFields.bindAutoCompletion(TfProductID, autoComplete);

        TfProductID.textProperty().addListener((observable, oldValue, newValue) -> {
            if(checkExitProduct(newValue) != -1){

                Product tmp = MainPage.productArrayList.get(checkExitProduct(newValue));
                currentItem = newValue;
                TfProductName.setText(tmp.getProductName());
                TfUnitPrice.setText(String.valueOf(tmp.getUnitPrice()));
                TfSellingPrice.setText(String.valueOf(tmp.getSellingPrice()));
                TfProductBrand.setText(tmp.getProductBrand());
                CbProductStatus.setSelected(tmp.isProductStatus());

                if (tmp.getClass().equals(Electronic.class)) {
                    Electronic e = (Electronic) tmp;
                    TfElectronicColor.setText(e.getColor());
                    TfModel.setText(e.getModel());
                    Grocery.setVisible(false);
                    Clothing.setVisible(false);
                    Electronic.setVisible(true);

                } else if (tmp.getClass().equals(Grocery.class)) {
                    Grocery g = (Grocery) tmp;
                    TfCategory.setText(g.getCategory());
                    Electronic.setVisible(false);
                    Clothing.setVisible(false);
                    Grocery.setVisible(true);

                } else if (tmp.getClass().equals(Clothing.class)) {
                    Clothing c = (Clothing) tmp;
                    TfClothingType.setText(c.getClothingType());
                    TfClothingSize.setText(c.getClothingSize());
                    TfClothingColor.setText(c.getColor());
                    TfClothingMaterial.setText(c.getMaterial());
                    Electronic.setVisible(false);
                    Grocery.setVisible(false);
                    Clothing.setVisible(true);
                }
            }
        });

    }

    public int checkExitProduct(String productID) {
        int[] a = new int[1];
        a[0] = -1;
        MainPage.productArrayList.forEach((n) -> {
            if (n.getProductID().equalsIgnoreCase(productID)) {
                a[0] = MainPage.productArrayList.indexOf(n);
            }
        });

        return a[0];
    }


}
