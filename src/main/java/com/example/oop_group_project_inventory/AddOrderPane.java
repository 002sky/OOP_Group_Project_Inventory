package com.example.oop_group_project_inventory;


import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import net.ucanaccess.console.Main;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;


public class AddOrderPane implements Initializable {
    @FXML
    private TextField TfProductID, TfQuantity, TfOrderID;
    @FXML
    private Label lblProductName, lblSellingPrice,lblSubTotal;
    @FXML
    private TableColumn<OrderItem, String> clmProductID, clmProductName, clmSellingPrice, clmQuantity, clmTotal;
    @FXML
    private TableView<OrderItem> TvProduct;
    @FXML
    private DatePicker dpOrderDate;

    private ArrayList<String> autoComplete = new ArrayList<>();
    private ObservableList<OrderItem> ObservableListProducts;

    private OrderItem tmpProduct;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainPage.productArrayList.forEach(product -> {
            autoComplete.add(product.getProductID());
        });
        TextFields.bindAutoCompletion(TfProductID, autoComplete);

        TfProductID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (checkExistProduct(newValue) != -1) {
;
                TfProductID.setText(MainPage.productArrayList.get(checkExistProduct(newValue)).getProductID());
                lblProductName.setText(MainPage.productArrayList.get(checkExistProduct(newValue)).getProductName());
                lblSellingPrice.setText(Double.toString(MainPage.productArrayList.get(checkExistProduct(newValue)).getSellingPrice()));
            }
        });

        initializeColumn();
        ObservableListProducts = FXCollections.observableArrayList();
        TvProduct.setItems(ObservableListProducts);


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


    public void addToTable(MouseEvent event) {
        double total = Double.parseDouble(lblSellingPrice.getText()) * Integer.parseInt(TfQuantity.getText());

        ObservableListProducts.add(
                new OrderItem(MainPage.productArrayList.get(checkExistProduct(TfProductID.getText())), Integer.parseInt(TfQuantity.getText()),total )
        );
        calSubTotal();
        TvProduct.refresh();

    }

    private void initializeColumn() {


        clmProductID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getOrderProduct().getProductID()));

        clmProductName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getOrderProduct().getProductName()));

        clmSellingPrice.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getOrderProduct().getSellingPrice())));


        clmQuantity.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        clmTotal.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));

    }

    public void deleteItem(MouseEvent event) {
        OrderItem product = TvProduct.getSelectionModel().getSelectedItem();
        ObservableListProducts.remove(product);
        calSubTotal();
        TvProduct.refresh();
    }

    public void calSubTotal(){
        double total = 0;
        for(OrderItem product : ObservableListProducts){
            total += product.getTotalPrice();
        }
        lblSubTotal.setText(Double.toString(total));
    }

    public void submitToDatabase(MouseEvent event) {
        ArrayList<OrderItem> orderItem = new ArrayList<>();
        orderItem.addAll(ObservableListProducts.stream().toList());
        Order order = new Order(TfOrderID.getText(),dpOrderDate.getValue() ,orderItem);
        order.saveToDatabase(order);

        MainPage.orderArrayList.clear();
        MainPage.orderArrayList.addAll(order.getAllOrders());
    }
}
