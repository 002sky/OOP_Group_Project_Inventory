package com.example.oop_group_project_inventory;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class UpdateProductPane implements Initializable {


    @FXML
    private GridPane Grocery, Electronic, Clothing;
    @FXML
    private Label productBox, lblProductIDErrMsg, lblProductNameErrMsg, lblCategoryErrMsg, lblUnitPriceErrMsg, lblSellingPriceErrMsg, lblProductBrandErrMsg, lblElectronicColorErrMsg, lblModelErrMsg, lblClothingTypeErrMsg, lblClothingSizeErrMsg, lblClothingColorErrMsg, lblClothingMaterialErrMsg;
    @FXML
    private CheckBox CbProductStatus;
    @FXML
    private GridPane mainGP;
    @FXML
    private TextField TfProductID, TfProductName, TfUnitPrice, TfSellingPrice, TfProductBrand, TfElectronicColor, TfModel, TfClothingType, TfClothingSize, TfClothingColor, TfClothingMaterial, TfCategory;
    @FXML
    private ComboBox productCategoryBox;
    public ArrayList<String> autoComplete = new ArrayList<>();
    public String currentItem;

    public Stage primaryStage;


    //set every error messages empty
    public void resetLBLErrMsg() {
        lblProductIDErrMsg.setText("");
        lblProductNameErrMsg.setText("");
        lblUnitPriceErrMsg.setText("");
        lblSellingPriceErrMsg.setText("");
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
        lblSellingPriceErrMsg.setTextFill(Color.color(1, 0, 0));
        lblProductBrandErrMsg.setTextFill(Color.color(1, 0, 0));
        lblElectronicColorErrMsg.setTextFill(Color.color(1, 0, 0));
        lblModelErrMsg.setTextFill(Color.color(1, 0, 0));
        lblCategoryErrMsg.setTextFill(Color.color(1, 0, 0));
        lblClothingTypeErrMsg.setTextFill(Color.color(1, 0, 0));
        lblClothingSizeErrMsg.setTextFill(Color.color(1, 0, 0));
        lblClothingColorErrMsg.setTextFill(Color.color(1, 0, 0));
        lblClothingMaterialErrMsg.setTextFill(Color.color(1, 0, 0));

    }

    //validation for every text field
    public boolean validation(MouseEvent event) throws IOException {
        boolean validate = true;

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
            lblProductIDErrMsg.setText("");
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
            lblSellingPriceErrMsg.setText(errorMessage);
            validate = false;
        } else {
            lblSellingPriceErrMsg.setText("");
        }

        if (TfProductBrand.getText().isEmpty()) {
//                System.out.println("product brand is empty");
            errorMessage = "Product brand is empty";
            lblProductBrandErrMsg.setText(errorMessage);
            validate = false;
        } else {
            lblProductBrandErrMsg.setText("");
        }
        Product tmp = null;
        if (!TfProductID.getText().isEmpty()) {
            tmp = MainPage.productArrayList.get(checkExistProduct(TfProductID.getText()));

            if (tmp.getClass().equals(Clothing.class)) {
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

            if (tmp.getClass().equals(Grocery.class)) {
                if (TfCategory.getText().isEmpty()) {
//                    System.out.println("expiry date is empty");
                    errorMessage = "Category is empty";
                    lblCategoryErrMsg.setText(errorMessage);
                    validate = false;
                } else {
                    lblCategoryErrMsg.setText("");
                }
            }

            if (tmp.getClass().equals(Electronic.class)) {
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
        }


        return validate;
    }


    public void UpdateProduct(MouseEvent event) throws IOException {
        boolean hasError = false;

        if (validation(event) == true) {
            if (checkExistProduct(currentItem) != -1) {
                MainPage.productArrayList.get(checkExistProduct(currentItem)).setProductName(TfProductName.getText());
                MainPage.productArrayList.get(checkExistProduct(currentItem)).setUnitPrice(Double.parseDouble(TfUnitPrice.getText()));
                MainPage.productArrayList.get(checkExistProduct(currentItem)).setSellingPrice(Double.parseDouble(TfSellingPrice.getText()));
                MainPage.productArrayList.get(checkExistProduct(currentItem)).setProductBrand(TfProductBrand.getText());
                MainPage.productArrayList.get(checkExistProduct(currentItem)).setProductStatus(CbProductStatus.isSelected());
                if (MainPage.productArrayList.get(checkExistProduct(currentItem)).getClass().equals(Electronic.class)) {
                    Electronic e = (Electronic) MainPage.productArrayList.get(checkExistProduct(currentItem));
                    e.setColor(TfElectronicColor.getText());
                    e.setModel(TfModel.getText());
                    hasError = e.updateProduct(e);


                } else if (MainPage.productArrayList.get(checkExistProduct(currentItem)).getClass().equals(Grocery.class)) {
                    Grocery g = (Grocery) MainPage.productArrayList.get(checkExistProduct(currentItem));
                    g.setCategory(TfCategory.getText());
                    hasError = g.updateProduct(g);

                } else if (MainPage.productArrayList.get(checkExistProduct(currentItem)).getClass().equals(Clothing.class)) {

                    Clothing c = (Clothing) MainPage.productArrayList.get(checkExistProduct(currentItem));
                    c.setColor(TfClothingColor.getText());
                    c.setMaterial(TfClothingMaterial.getText());
                    c.setClothingSize(TfClothingSize.getText());
                    c.setClothingType(TfClothingType.getText());
                    hasError = c.updateProduct(c);
                }

                //Show pop up error message
                primaryStage = new Stage();
                primaryStage.setTitle(hasError == true ? "Error" : "Updated successfully");
                final Popup popup = new Popup();

                popup.setX(300);
                popup.setY(200);

                Button show = new Button("OK");
                show.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
//                            popup.show(primaryStage);
                        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                    }
                });

                Label message = new Label(hasError == true ? "There is an error happened" : "Your product has been updated successfully");


                VBox layout = new VBox(10);
                layout.setStyle(hasError == true ? "-fx-background-color: cornsilk; -fx-padding: 10;" : "-fx-background-color: lightgreen; -fx-padding: 10;");
                layout.getChildren().addAll(show, message);
                layout.setAlignment(Pos.CENTER);
                primaryStage.setScene(new Scene(layout, 400, 400));
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.setResizable(false);
                primaryStage.show();

                autoComplete.clear();
                MainPage.productArrayList.forEach((m) -> autoComplete.add(m.getProductID()));
                TextFields.bindAutoCompletion(TfProductID, autoComplete);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetLBLErrMsg();
        MainPage.productArrayList.forEach((n) -> autoComplete.add(n.getProductID()));
        TextFields.bindAutoCompletion(TfProductID, autoComplete);

        TfProductID.textProperty().addListener((observable, oldValue, newValue) -> {
            autoComplete.clear();
            MainPage.productArrayList.forEach((n) -> autoComplete.add(n.getProductID()));
            TextFields.bindAutoCompletion(TfProductID, autoComplete);
            if (checkExistProduct(newValue) != -1) {
                Product tmp = MainPage.productArrayList.get(checkExistProduct(newValue));
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
                    productBox.setText("Electronic");
                    Grocery.setVisible(false);
                    Clothing.setVisible(false);
                    Electronic.setVisible(true);

                } else if (tmp.getClass().equals(Grocery.class)) {
                    Grocery g = (Grocery) tmp;
                    TfCategory.setText(g.getCategory());
                    Electronic.setVisible(false);
                    Clothing.setVisible(false);
                    Grocery.setVisible(true);
                    productBox.setText("Grocery");

                } else if (tmp.getClass().equals(Clothing.class)) {
                    Clothing c = (Clothing) tmp;
                    TfClothingType.setText(c.getClothingType());
                    TfClothingSize.setText(c.getClothingSize());
                    TfClothingColor.setText(c.getColor());
                    TfClothingMaterial.setText(c.getMaterial());
                    Electronic.setVisible(false);
                    Grocery.setVisible(false);
                    Clothing.setVisible(true);
                    productBox.setText("Clothing");

                }
            }
        });

    }

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


}
