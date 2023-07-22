package com.example.oop_group_project_inventory;

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
    private GridPane Grocery, Electronic, Clothing;

    @FXML
    private TextField TfProductID, TfProductName, TfUnitPrice, TfSellingPrice, TfProductBrand, TfElectronicColor, TfModel, TfClothingType, TfClothingSize, TfClothingColor, TfClothingMaterial;

    @FXML
    private CheckBox CbProductStatus;

    @FXML
    private TextField TfCategory;

    @FXML
    private Label lblproductBoxErrMsg, lblproductIDErrMsg, lblProductNameErrMsg, lblUnitPriceErrMsg, lblSellingPrice, lblproductBrandErrMsg, lblElectronicColorErrMsg, lblModelErrMsg, lblCategory, lblClothingTypeErrMsg, lblClothingSizeErrMsg, lblClothingColorErrMsg, lblClothingMaterialErrMsg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String productType[] = {"Grocery", "Electronic", "Clothing"};

        productBox.setItems(FXCollections.observableArrayList(productType));

        resetLBLErrMsg();

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

    public void resetLBLErrMsg() {
        lblproductBoxErrMsg.setText("");
        lblproductIDErrMsg.setText("");
        lblProductNameErrMsg.setText("");
        lblUnitPriceErrMsg.setText("");
        lblSellingPrice.setText("");
        lblproductBrandErrMsg.setText("");
        lblElectronicColorErrMsg.setText("");
        lblModelErrMsg.setText("");
        lblCategory.setText("");
        lblClothingTypeErrMsg.setText("");
        lblClothingSizeErrMsg.setText("");
        lblClothingColorErrMsg.setText("");
        lblClothingMaterialErrMsg.setText("");

        lblproductIDErrMsg.setTextFill(Color.color(1, 0, 0));
        lblProductNameErrMsg.setTextFill(Color.color(1, 0, 0));
        lblUnitPriceErrMsg.setTextFill(Color.color(1, 0, 0));
        lblSellingPrice.setTextFill(Color.color(1, 0, 0));
        lblproductBrandErrMsg.setTextFill(Color.color(1, 0, 0));
        lblElectronicColorErrMsg.setTextFill(Color.color(1, 0, 0));
        lblModelErrMsg.setTextFill(Color.color(1, 0, 0));
        lblCategory.setTextFill(Color.color(1, 0, 0));
        lblClothingTypeErrMsg.setTextFill(Color.color(1, 0, 0));
        lblClothingSizeErrMsg.setTextFill(Color.color(1, 0, 0));
        lblClothingColorErrMsg.setTextFill(Color.color(1, 0, 0));
        lblClothingMaterialErrMsg.setTextFill(Color.color(1, 0, 0));
        lblproductBoxErrMsg.setTextFill(Color.color(1, 0, 0));

    }


    public boolean validation(MouseEvent event) throws IOException {
        boolean validate = true;
        if (!productBox.getSelectionModel().isEmpty()) {
            lblproductBoxErrMsg.setText("");
            String errorMessage = "";
            if (TfProductID.getText().isEmpty()) {
//                System.out.println("product ID is empty");
                errorMessage = "Product ID is empty";
                lblproductIDErrMsg.setText(errorMessage);
//                ErrorMessageWindow errorMessageWindow = new ErrorMessageWindow(errorMessage);

//                aNewStage = errorMessageWindow.getTheStage();
//                aNewStage.show();
                validate = false;

            } else {
                lblproductIDErrMsg.setText("");
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
                lblproductBrandErrMsg.setText(errorMessage);
                validate = false;
            } else {
                lblproductBrandErrMsg.setText("");
            }

            if (productBox.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Clothing")) {
                if (TfClothingColor.getText().isEmpty()) {
//                    System.out.println("clothing color is empty");
                    errorMessage = "Clothing color is empty";
                    lblClothingColorErrMsg.setText(errorMessage);
                    validate = false;
                }else {
                    lblClothingColorErrMsg.setText("");
                }

                if (TfClothingMaterial.getText().isEmpty()) {
//                    System.out.println("clothing material is empty");
                    errorMessage = "Clothing material is empty";
                    lblClothingMaterialErrMsg.setText(errorMessage);
                    validate = false;
                }else {
                    lblClothingMaterialErrMsg.setText("");
                }

                if (TfClothingSize.getText().isEmpty()) {
//                    System.out.println("clothing size is empty");
                    errorMessage = "Clothing size is empty";
                    lblClothingSizeErrMsg.setText(errorMessage);
                    validate = false;
                }else {
                    lblClothingSizeErrMsg.setText("");
                }

                if (TfClothingType.getText().isEmpty()) {
//                    System.out.println("clothing size is empty");
                    errorMessage = "Clothing size is empty";
                    lblClothingTypeErrMsg.setText(errorMessage);
                    validate = false;
                }else {
                    lblClothingTypeErrMsg.setText("");
                }
            }

            if (productBox.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Grocery")) {
                if (TfCategory.getText() == null) {
//                    System.out.println("expiry date is empty");
                    errorMessage = "Expiry date is empty";
                    lblCategory.setText(errorMessage);
                    validate = false;
                }
                else {
                    lblCategory.setText("");
                }
            }

            if (productBox.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Electronic")) {
                if (TfElectronicColor.getText().isEmpty()) {
//                    System.out.println(" color is empty");
                    errorMessage = "Color is empty";
                    lblElectronicColorErrMsg.setText(errorMessage);
                    validate = false;
                }else {
                    lblElectronicColorErrMsg.setText("");
                }

                if (TfModel.getText().isEmpty()) {
//                    System.out.println("model is empty");
                    errorMessage = "Model is empty";
                    lblModelErrMsg.setText(errorMessage);
                    validate = false;
                }
                else {
                    lblModelErrMsg.setText("");
                }
            }

        } else {
            String errorMessage = "please select the product type";
//            System.out.println("please select the product type");
            lblproductBoxErrMsg.setText(errorMessage);
            validate = false;
        }

        return validate;
    }

    public void SubmitToDatabase(MouseEvent event) throws IOException {
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";
        boolean check = validation(event);
        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            if (check) {
                resetLBLErrMsg();

                String sqlProduct = "INSERT INTO Product (ProductID, ProductName, UnitPrice, SellingPrice, ProductBrand) VALUES ('" + TfProductID.getText() + "', '" + TfProductName.getText() + "', '" + TfUnitPrice.getText() + "', '" + TfSellingPrice.getText() + "', '" + TfProductBrand.getText() + "')";
                String sql = "";

                Statement statement = connection.createStatement();
                int result = statement.executeUpdate(sqlProduct);

                if (result > 0) {
                    if (productBox.getValue().toString().equalsIgnoreCase("Clothing")) {
                        sql = "INSERT INTO Clothing (ProductID, color, material, clothingSize,clothingType) VALUES ('" + TfProductID.getText() + "', '" + TfClothingColor.getText() + "', '" + TfClothingMaterial.getText() + "', '" + TfClothingSize.getText() + "', '" + TfClothingType.getText() + "')";
                    } else if (productBox.getValue().toString().equalsIgnoreCase("Grocery")) {
                        sql = "INSERT INTO Grocery (ProductID, Category) VALUES ('" + TfProductID.getText() + "', '" + TfCategory.getText() + "')";
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
