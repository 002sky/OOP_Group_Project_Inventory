package com.example.oop_group_project_inventory.controller;

import com.example.oop_group_project_inventory.model.Clothing;
import com.example.oop_group_project_inventory.model.Electronic;
import com.example.oop_group_project_inventory.model.Grocery;
import com.example.oop_group_project_inventory.model.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;


public class AddProductPane implements Initializable {
    @FXML
    private ComboBox productBox;

    @FXML
    private GridPane GpGrocery, GpElectronic, GpClothing;

    @FXML
    private TextField TfCategory, TfProductID, TfProductName, TfUnitPrice, TfSellingPrice, TfProductBrand, TfElectronicColor, TfModel, TfClothingType, TfClothingSize, TfClothingColor, TfClothingMaterial;

    @FXML
    private CheckBox CbProductStatus;

//    @FXML
//    private TextField ;

    @FXML
    private Label lblProductBoxErrMsg, lblProductIDErrMsg, lblProductNameErrMsg, lblUnitPriceErrMsg, lblSellingPrice, lblProductBrandErrMsg, lblElectronicColorErrMsg, lblModelErrMsg, lblCategoryErrMsg, lblClothingTypeErrMsg, lblClothingSizeErrMsg, lblClothingColorErrMsg, lblClothingMaterialErrMsg;

    /**
     * initialize combo box with product type "Grocery", "Electronic", "Clothing" and set it to combo box ProductBox
     * Combo box ProductBox is used to select product type and based on the selected product type, it will show the corresponding field
     * Text field tfUnitPrice, tfSellingPrice was initialized with listener for ensuring only numbers
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String productType[] = {"Grocery", "Electronic", "Clothing"};

        productBox.setItems(FXCollections.observableArrayList(productType));

        resetLBLErrMsg();

        productBox.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {

                if (t1.toString().equalsIgnoreCase("Grocery")) {
                    GpGrocery.setVisible(true);
                    GpElectronic.setVisible(false);
                    GpClothing.setVisible(false);

                } else if (t1.toString().equalsIgnoreCase("Electronic")) {
                    GpGrocery.setVisible(false);
                    GpClothing.setVisible(false);
                    GpElectronic.setVisible(true);
                } else if (t1.toString().equalsIgnoreCase("Clothing")) {
                    GpGrocery.setVisible(false);
                    GpClothing.setVisible(true);
                    GpElectronic.setVisible(false);
                } else {
                    GpGrocery.setVisible(false);
                    GpClothing.setVisible(false);
                    GpElectronic.setVisible(false);
                }


            }
        });


        TfUnitPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                TfUnitPrice.setText(newValue.replaceAll("[^\\d\\.]", ""));
            }
        });

        TfSellingPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                TfSellingPrice.setText(newValue.replaceAll("[^\\d\\.]", ""));
            }
        });
    }

    /**
     * reset all error messages
     */
    //set every error messages empty
    public void resetLBLErrMsg() {
        lblProductBoxErrMsg.setText("");
        lblProductIDErrMsg.setText("");
        lblProductNameErrMsg.setText("");
        lblUnitPriceErrMsg.setText("");
        lblSellingPrice.setText("");
        lblProductBrandErrMsg.setText("");
        lblElectronicColorErrMsg.setText("");
        lblModelErrMsg.setText("");
        lblCategoryErrMsg.setText("");
        lblClothingTypeErrMsg.setText("");
        lblClothingSizeErrMsg.setText("");
        lblClothingColorErrMsg.setText("");
        lblClothingMaterialErrMsg.setText("");

        lblProductIDErrMsg.setTextFill(Color.color(1, 0, 0));
        lblProductNameErrMsg.setTextFill(Color.color(1, 0, 0));
        lblUnitPriceErrMsg.setTextFill(Color.color(1, 0, 0));
        lblSellingPrice.setTextFill(Color.color(1, 0, 0));
        lblProductBrandErrMsg.setTextFill(Color.color(1, 0, 0));
        lblElectronicColorErrMsg.setTextFill(Color.color(1, 0, 0));
        lblModelErrMsg.setTextFill(Color.color(1, 0, 0));
        lblCategoryErrMsg.setTextFill(Color.color(1, 0, 0));
        lblClothingTypeErrMsg.setTextFill(Color.color(1, 0, 0));
        lblClothingSizeErrMsg.setTextFill(Color.color(1, 0, 0));
        lblClothingColorErrMsg.setTextFill(Color.color(1, 0, 0));
        lblClothingMaterialErrMsg.setTextFill(Color.color(1, 0, 0));
        lblProductBoxErrMsg.setTextFill(Color.color(1, 0, 0));

    }

    /**
     * clear all text field
     */
    //clear all text field
    public void clearData() {
        resetLBLErrMsg();
        TfCategory.setText("");
        TfProductID.setText("");
        TfProductName.setText("");
        TfUnitPrice.setText("");
        TfSellingPrice.setText("");
        TfProductBrand.setText("");
        TfElectronicColor.setText("");
        TfModel.setText("");
        TfClothingType.setText("");
        TfClothingSize.setText("");
        TfClothingColor.setText("");
        TfClothingMaterial.setText("");
    }

    /**
     * check whether got existing product
     *
     * @param productID accept product id as parameter
     * @return return index of the product in Mainpage.productArrayList
     */
    //check and return whether got existing product
    public int checkExistProduct(String productID) {
        int[] a = new int[1];
        a[0] = -1;
        MainPage.productArrayList.forEach((n) -> {
            if (n.getProductID().equalsIgnoreCase(productID)) {
                a[0] = MainPage.productArrayList.indexOf(n);
            }
        });
        return a[0];
    }

    /**
     * validation for every text field
     *
     * @throws IOException
     */
    //validation for every text field
    public boolean validation(MouseEvent event) throws IOException {
        boolean validate = true;
        if (!productBox.getSelectionModel().isEmpty()) {
            lblProductBoxErrMsg.setText("");
            String errorMessage = "";
            if (TfProductID.getText().isEmpty()) {
//                System.out.println("product ID is empty");
                errorMessage = "Product ID is empty";
                lblProductIDErrMsg.setText(errorMessage);
//                ErrorMessageWindow errorMessageWindow = new ErrorMessageWindow(errorMessage);
//                aNewStage = errorMessageWindow.getTheStage();
//                aNewStage.show();
                validate = false;
            } else {
                if (checkExistProduct(TfProductID.getText()) != -1) {
                    errorMessage = "Product id is already exist";
                    lblProductIDErrMsg.setText(errorMessage);
                    validate = false;
                } else {
                    lblProductIDErrMsg.setText("");
                }

            }

            if (TfProductName.getText().isEmpty()) {
//                System.out.println("product name is empty");
                errorMessage = "Product name is empty";
                lblProductNameErrMsg.setText(errorMessage);
//                ErrorMessageWindow errorMessageWindow = new ErrorMessageWindow(errorMessage);
//                aNewStage = errorMessageWindow.getTheStage();
//                aNewStage.show();
                validate = false;
            } else {
                lblProductNameErrMsg.setText("");
            }

            if (TfUnitPrice.getText().isEmpty()) {
//                System.out.println("product unit price is empty");
                errorMessage = "Product unit price is empty";
                lblUnitPriceErrMsg.setText(errorMessage);
                validate = false;
            } else {
                lblUnitPriceErrMsg.setText("");
            }

            if (TfSellingPrice.getText().isEmpty()) {
//                System.out.println("product selling price is empty");
                errorMessage = "Product selling price is empty";
                lblSellingPrice.setText(errorMessage);
                validate = false;
            } else {
                lblSellingPrice.setText("");
            }

            if (TfProductBrand.getText().isEmpty()) {
//                System.out.println("product brand is empty");
                errorMessage = "Product brand is empty";
                lblProductBrandErrMsg.setText(errorMessage);
                validate = false;
            } else {
                lblProductBrandErrMsg.setText("");
            }

            if (productBox.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Clothing")) {
                if (TfClothingColor.getText().isEmpty()) {
//                    System.out.println("clothing color is empty");
                    errorMessage = "Clothing color is empty";
                    lblClothingColorErrMsg.setText(errorMessage);
                    validate = false;
                } else {
                    lblClothingColorErrMsg.setText("");
                }

                if (TfClothingMaterial.getText().isEmpty()) {
//                    System.out.println("clothing material is empty");
                    errorMessage = "Clothing material is empty";
                    lblClothingMaterialErrMsg.setText(errorMessage);
                    validate = false;
                } else {
                    lblClothingMaterialErrMsg.setText("");
                }

                if (TfClothingSize.getText().isEmpty()) {
//                    System.out.println("clothing size is empty");
                    errorMessage = "Clothing size is empty";
                    lblClothingSizeErrMsg.setText(errorMessage);
                    validate = false;
                } else {
                    lblClothingSizeErrMsg.setText("");
                }

                if (TfClothingType.getText().isEmpty()) {
//                    System.out.println("clothing size is empty");
                    errorMessage = "Clothing size is empty";
                    lblClothingTypeErrMsg.setText(errorMessage);
                    validate = false;
                } else {
                    lblClothingTypeErrMsg.setText("");
                }
            }

            if (productBox.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Grocery")) {
                if (TfCategory.getText().isEmpty()) {
//                    System.out.println("expiry date is empty");
                    errorMessage = "Category is empty";
                    lblCategoryErrMsg.setText(errorMessage);
                    validate = false;
                } else {
                    lblCategoryErrMsg.setText("");
                }
            }

            if (productBox.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Electronic")) {
                if (TfElectronicColor.getText().isEmpty()) {
//                    System.out.println(" color is empty");
                    errorMessage = "Color is empty";
                    lblElectronicColorErrMsg.setText(errorMessage);
                    validate = false;
                } else {
                    lblElectronicColorErrMsg.setText("");
                }

                if (TfModel.getText().isEmpty()) {
//                    System.out.println("model is empty");
                    errorMessage = "Model is empty";
                    lblModelErrMsg.setText(errorMessage);
                    validate = false;
                } else {
                    lblModelErrMsg.setText("");
                }
            }

        } else {
            String errorMessage = "Please select the product type";
//            System.out.println("please select the product type");
            lblProductBoxErrMsg.setText(errorMessage);
            validate = false;
        }

        return validate;
    }

    /**
     * add product into database via Product class
     * if validation is true then add into database
     * if validation is false then show error message
     *
     * @throws IOException
     */
    //add into database
    public void SubmitToDatabase(MouseEvent event) throws IOException {
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";
        boolean check = validation(event);
        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            if (check) {
                String sql = "";

                if (productBox.getValue().toString().equalsIgnoreCase("Clothing")) {
                    Clothing product = new Clothing(TfProductID.getText(), TfProductName.getText(), Double.parseDouble(TfUnitPrice.getText()), Double.parseDouble(TfSellingPrice.getText()), TfProductBrand.getText(),CbProductStatus.isSelected(), TfClothingColor.getText(), TfClothingMaterial.getText(), TfClothingSize.getText(), TfClothingType.getText());

                    product.addProduct(product, productBox.getValue().toString());

                } else if (productBox.getValue().toString().equalsIgnoreCase("Grocery")) {
                    Grocery product = new Grocery(TfProductID.getText(), TfProductName.getText(), Double.parseDouble(TfUnitPrice.getText()), Double.parseDouble(TfSellingPrice.getText()), TfProductBrand.getText(), CbProductStatus.isSelected(), TfCategory.getText());

                    product.addProduct(product, productBox.getValue().toString());


                } else if (productBox.getValue().toString().equalsIgnoreCase("Electronic")) {
                    Electronic product = new Electronic(TfProductID.getText(), TfProductName.getText(), Double.parseDouble(TfUnitPrice.getText()), Double.parseDouble(TfSellingPrice.getText()), TfProductBrand.getText(), CbProductStatus.isSelected(),TfElectronicColor.getText(), TfModel.getText());

                    product.addProduct(product, productBox.getValue().toString());

                }

                clearData();

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
