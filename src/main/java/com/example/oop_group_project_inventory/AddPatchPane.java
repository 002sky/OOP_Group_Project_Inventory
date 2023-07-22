package com.example.oop_group_project_inventory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


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

        productList.addAll(MainPage.productArrayList);
        inventoryList.addAll(MainPage.inventoryArrayList);

        CbProductBox.setItems(productList);
        CbInventoryBox.setItems(inventoryList);
        TfQuantity.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                TfQuantity.setText(newValue.replaceAll("[^\\d\\.]", ""));
            }
        });
    }

    public boolean validation(MouseEvent event) throws IOException {
        boolean validate = true;
            String errorMessage = "";
            if (TfPatchNumber.getText().isEmpty()) {
                errorMessage = "Patch Number is empty";
                lblPatchNumberErrMsg.setText(errorMessage);
                validate = false;
            } else {
                lblPatchNumberErrMsg.setText("");
            }

            if (TfQuantity.getText().isEmpty()) {
                errorMessage = "Quantity is empty";
                lblQuantityErrMsg.setText(errorMessage);
                validate = false;
            } else {
                lblQuantityErrMsg.setText("");
            }

        if (TfSupplySource.getText().isEmpty()) {
            errorMessage = "Supply Source is empty";
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

        if (DpReceivedDate.getValue()==null) {
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

    public void SavePatch(MouseEvent event) {
        Product product = CbProductBox.getSelectionModel().getSelectedItem();

        System.out.println(product.getProductID());
    }
}
