package com.example.oop_group_project_inventory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPatchPane implements Initializable {
    @FXML
    private ComboBox<Product> CbProductBox;
    @FXML
    private ComboBox<Inventory> CbInventoryBox;
    @FXML
    private DatePicker DpReceivedDate;
    @FXML
    private TextField TfPatchNumber, TfQuantity, TfSupplySource;
    @FXML
    private Label lblPatchNumberErrMsg, lblQuantityErrMsg, lblSupplySourceErrMsg, lblReceivedDateErrMsg, lblInventoryBoxErrMsg, lblProductBoxErrMsg;
    public Stage primaryStage;

    public void resetLBLErrMsg() {
        lblPatchNumberErrMsg.setText("");
        lblQuantityErrMsg.setText("");
        lblSupplySourceErrMsg.setText("");
        lblReceivedDateErrMsg.setText("");
        lblInventoryBoxErrMsg.setText("");
        lblProductBoxErrMsg.setText("");

        lblPatchNumberErrMsg.setTextFill(Color.color(1, 0, 0));
        lblQuantityErrMsg.setTextFill(Color.color(1, 0, 0));
        lblSupplySourceErrMsg.setTextFill(Color.color(1, 0, 0));
        lblReceivedDateErrMsg.setTextFill(Color.color(1, 0, 0));
        lblInventoryBoxErrMsg.setTextFill(Color.color(1, 0, 0));
        lblProductBoxErrMsg.setTextFill(Color.color(1, 0, 0));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();

        resetLBLErrMsg();

        productList.addAll(MainPage.productArrayList);
        inventoryList.addAll(MainPage.inventoryArrayList);

        CbProductBox.setItems(productList);
        CbInventoryBox.setItems(inventoryList);
        TfQuantity.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                TfQuantity.setText(newValue.replaceAll("[^\\d\\.]", ""));
            }
        });

        TfPatchNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            if (checkExistPatch(newValue) != -1) {
                lblPatchNumberErrMsg.setText("Patch number is already exist");
            } else {
                lblPatchNumberErrMsg.setText("");
            }
        });
    }

    public int checkExistPatch(String patchNumber) {
        int[] a = new int[1];
        a[0] = -1;
        MainPage.productPatchArrayList.forEach((n) -> {
            if (n.getPatchNumber().equalsIgnoreCase(patchNumber)) {
                a[0] = MainPage.productPatchArrayList.indexOf(n);
            }
        });

        return a[0];
    }

    public boolean validation() throws IOException {
        boolean validate = true;
        String errorMessage = "";
        if (TfPatchNumber.getText().isEmpty()) {
            errorMessage = "Patch number is empty";
            lblPatchNumberErrMsg.setText(errorMessage);
            validate = false;
        } else {
            if (checkExistPatch(TfPatchNumber.getText()) != -1) {
                errorMessage = "Patch number is already exist";
                lblPatchNumberErrMsg.setText(errorMessage);
                validate = false;
            } else {
                lblPatchNumberErrMsg.setText("");
            }
        }

        if (TfQuantity.getText().isEmpty()) {
            errorMessage = "Quantity is empty";
            lblQuantityErrMsg.setText(errorMessage);
            validate = false;
        } else {
            lblQuantityErrMsg.setText("");
        }

        if (TfSupplySource.getText().isEmpty()) {
            errorMessage = "Supply source is empty";
            lblSupplySourceErrMsg.setText(errorMessage);
            validate = false;
        } else {
            lblSupplySourceErrMsg.setText("");
        }

        if (CbProductBox.getSelectionModel().isEmpty()) {
            errorMessage = "There is no any product item is being selected";
            lblProductBoxErrMsg.setText(errorMessage);
            validate = false;
        } else {
            lblProductBoxErrMsg.setText("");
        }

        if (DpReceivedDate.getValue() == null) {
            errorMessage = "Please select a received date";
            lblReceivedDateErrMsg.setText(errorMessage);
            validate = false;
        } else {
            lblReceivedDateErrMsg.setText("");
        }

        if (CbInventoryBox.getSelectionModel().isEmpty()) {
            errorMessage = "There is no any inventory item is being selected";
            lblInventoryBoxErrMsg.setText(errorMessage);
            validate = false;
        } else {
            lblInventoryBoxErrMsg.setText("");
        }

        return validate;
    }

    public void clearData() {
        resetLBLErrMsg();
        TfPatchNumber.setText("");
        TfQuantity.setText("");
        TfSupplySource.setText("");
        DpReceivedDate.setValue(null);
        CbInventoryBox.getSelectionModel().clearSelection();
        CbProductBox.getSelectionModel().clearSelection();
    }

    public void SavePatch(MouseEvent event) throws IOException {

        Product product = CbProductBox.getSelectionModel().getSelectedItem();
        Inventory inventory = CbInventoryBox.getSelectionModel().getSelectedItem();

        if (validation()) {
            ProductPatch productPatch = new ProductPatch();
            productPatch.setPatchNumber(TfPatchNumber.getText());
            productPatch.setSupplySource(TfSupplySource.getText());
            productPatch.setItems(product);
            productPatch.setInventory(inventory);
            productPatch.setReceivedDate(DpReceivedDate.getValue());
            productPatch.setQuantity(Integer.parseInt(TfQuantity.getText()));
            int result = productPatch.saveToDatabase(productPatch);

//            if(result == 1){
//                //Todo message here
//                System.out.println("success");
//            }
//            else {
//                System.out.println("error");
//            }
            if (result == 1) {
                clearData();
            }

            primaryStage = new Stage();
            primaryStage.setTitle(result == 1 ? "Patch saved" : "Error");
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

            Label message = new Label(result == 1 ? "Your patch has been saved successfully" : "There is an error happened");


            VBox layout = new VBox(10);
            layout.setStyle(result == 1 ? "-fx-background-color: lightgreen; -fx-padding: 10;" : "-fx-background-color: cornsilk; -fx-padding: 10;");
            layout.getChildren().addAll(show, message);
            layout.setAlignment(Pos.CENTER);
            primaryStage.setScene(new Scene(layout, 400, 400));
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.show();


        }


//        System.out.println(product.getProductID());
    }
}
