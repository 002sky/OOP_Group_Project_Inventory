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
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
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

    public Stage primaryStage;

    public void UpdateProduct(MouseEvent event) {
        boolean hasError = false;
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

            if (hasError == true) {
                primaryStage.setTitle("Error");
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

                Label errorMessage = new Label("There is an error happened");


                VBox layout = new VBox(10);
                layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
                layout.getChildren().addAll(show, errorMessage);
                layout.setAlignment(Pos.CENTER);
                primaryStage.setScene(new Scene(layout, 400, 400));
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.setResizable(false);
                primaryStage.show();
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
