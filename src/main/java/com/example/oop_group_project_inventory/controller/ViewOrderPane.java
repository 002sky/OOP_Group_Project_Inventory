package com.example.oop_group_project_inventory.controller;

import com.example.oop_group_project_inventory.model.OrderItem;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.TextFields;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewOrderPane implements Initializable {
    @FXML
    private TableView<OrderItem> TvOrder;
    @FXML
    private TextField TfOrderID;
    @FXML
    private Label lblDate, lblSubTotal;
    @FXML
    private TableColumn<OrderItem, String> clmProductID, clmProductName, clmSellingPrice, clmQuantity, clmTotal;


    private ArrayList<String> autoComplete = new ArrayList<>();

    private ObservableList<OrderItem> ObservableListProducts;

    /**
     * initialize the TfOrderID with auto complete value from the MainPage.orderArrayList
     * TfOrderID was initialized with Listener to populate the table view
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainPage.orderArrayList.forEach(order -> {
            autoComplete.add(order.getOrderID());
        });
        TextFields.bindAutoCompletion(TfOrderID, autoComplete);
        ObservableListProducts = FXCollections.observableArrayList();

        initializeColumn();

        TfOrderID.textProperty().addListener((observable, oldValue, newValue) -> {
            autoComplete.clear();
            MainPage.orderArrayList.forEach(order -> {
                autoComplete.add(order.getOrderID());
            });
            TextFields.bindAutoCompletion(TfOrderID, autoComplete);

            if (checkExistOrder(newValue) != -1) {
                TfOrderID.setText(MainPage.orderArrayList.get(checkExistOrder(newValue)).getOrderID());
                lblDate.setText(MainPage.orderArrayList.get(checkExistOrder(newValue)).getOrderDate().toString());

                ObservableListProducts.clear();
                ObservableListProducts.addAll(MainPage.orderArrayList.get(checkExistOrder(newValue)).getOrderItem());
                TvOrder.setItems(ObservableListProducts);

                calSubTotal();
                TvOrder.refresh();
            } else {
                lblDate.setText("");
                ObservableListProducts.clear();
                TvOrder.setItems(ObservableListProducts);
                TvOrder.refresh();
            }
        });



    }

    /**
     * check whether the orderID already exists in the MainPage.orderArrayList
     * @param newValue accepted new value as a String
     * @return return the index of the existing order in the MainPage.orderArrayList
     */
    //check and return whether got existing order
    private int checkExistOrder(String newValue) {
        int[] a = new int[1];
        a[0] = -1;
        MainPage.orderArrayList.forEach(order -> {
            if (order.getOrderID().equals(newValue)) {
                a[0] = MainPage.orderArrayList.indexOf(order);
            }
            ;
        });
        return a[0];
    }

    /**
     * initialize the column of the table view
     */
    private void initializeColumn() {

        clmProductID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getOrderProduct().getProductID()));

        clmProductName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getOrderProduct().getProductName()));

        clmSellingPrice.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getOrderProduct().getSellingPrice())));


        clmQuantity.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        clmTotal.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));

    }

    /**
     * calculate the subtotal
     */
    public void calSubTotal() {
        double total = 0;
        for (OrderItem product : ObservableListProducts) {
            total += product.getTotalPrice();
        }
        lblSubTotal.setText(Double.toString(total));
    }
}
