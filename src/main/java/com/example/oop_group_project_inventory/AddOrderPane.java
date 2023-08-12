package com.example.oop_group_project_inventory;


import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.ucanaccess.console.Main;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;


public class AddOrderPane implements Initializable {
    @FXML
    private TextField TfProductID, TfQuantity, TfOrderID;
    @FXML
    private Label lblProductName, lblSellingPrice, lblSubTotal, lblOrderIDErrMsg, lblDateErrMsg, lblProductIDErrMsg, lblQuantityErrMsg;
    @FXML
    private TableColumn<OrderItem, String> clmProductID, clmProductName, clmSellingPrice, clmQuantity, clmTotal;
    @FXML
    private TableView<OrderItem> TvProduct;
    @FXML
    private DatePicker dpOrderDate;

    private ArrayList<String> autoComplete = new ArrayList<>();
    private ObservableList<OrderItem> ObservableListProducts;

    private OrderItem tmpProduct;
    public Stage primaryStage;

    //set every error messages empty
    public void resetLBLErrMsg() {
        lblOrderIDErrMsg.setText("");
        lblDateErrMsg.setText("");
        lblProductIDErrMsg.setText("");
        lblQuantityErrMsg.setText("");


        lblOrderIDErrMsg.setTextFill(Color.color(1, 0, 0));
        lblDateErrMsg.setTextFill(Color.color(1, 0, 0));
        lblProductIDErrMsg.setTextFill(Color.color(1, 0, 0));
        lblQuantityErrMsg.setTextFill(Color.color(1, 0, 0));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TfOrderID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (checkExistOrder(newValue) != -1) {
                lblOrderIDErrMsg.setText("The order id is already exist");
            } else {
                lblOrderIDErrMsg.setText("");
            }
        });
        MainPage.productArrayList.forEach(product -> {
            autoComplete.add(product.getProductID());
        });
        TextFields.bindAutoCompletion(TfProductID, autoComplete);
        resetLBLErrMsg();
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

    //check and return whether got existing order

    public int checkExistOrder(String orderID) {
        int[] a = new int[1];
        a[0] = -1;
        MainPage.orderArrayList.forEach((n) -> {
            if (n.getOrderID().equalsIgnoreCase(orderID)) {
                a[0] = MainPage.orderArrayList.indexOf(n);
            }
        });

        return a[0];
    }

    //clear all text field
    public void clearData() {
        TfProductID.setText("");
        TfQuantity.setText("");
        TfOrderID.setText("");
        lblProductName.setText("");
        lblSellingPrice.setText("");
        lblSubTotal.setText("");
        resetLBLErrMsg();
        dpOrderDate.setValue(null);
        ObservableListProducts.clear();
    }

    //add product to table
    public void addToTable(MouseEvent event) throws IOException {
        if (validation()) {
            double total = Double.parseDouble(lblSellingPrice.getText()) * Integer.parseInt(TfQuantity.getText());

            ObservableListProducts.add(
                    new OrderItem(MainPage.productArrayList.get(checkExistProduct(TfProductID.getText())), Integer.parseInt(TfQuantity.getText()), total)
            );
            calSubTotal();
            TvProduct.refresh();
        }
    }

    //initialize table
    private void initializeColumn() {


        clmProductID.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getOrderProduct().getProductID()));

        clmProductName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getOrderProduct().getProductName()));

        clmSellingPrice.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getOrderProduct().getSellingPrice())));


        clmQuantity.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        clmTotal.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));

    }

    //delete an item(product) from table
    public void deleteItem(MouseEvent event) {
        OrderItem product = TvProduct.getSelectionModel().getSelectedItem();
        ObservableListProducts.remove(product);
        calSubTotal();
        TvProduct.refresh();
    }

    //calculate subtotal of price of all items in the table
    public void calSubTotal() {
        double total = 0;
        for (OrderItem product : ObservableListProducts) {
            total += product.getTotalPrice();
        }
        lblSubTotal.setText(Double.toString(total));
    }

    //validation for every text field
    public boolean validation() throws IOException {
        boolean validate = true;
        String errorMessage = "";
        if (TfOrderID.getText().isEmpty()) {
            errorMessage = "Order ID is empty";
            lblOrderIDErrMsg.setText(errorMessage);
            validate = false;
        } else {
            if (checkExistOrder(TfOrderID.getText()) != -1) {
                errorMessage = "Order ID is already exist";
                lblOrderIDErrMsg.setText(errorMessage);
                validate = false;
            } else {
                lblOrderIDErrMsg.setText("");
            }

        }

        if (TfQuantity.getText().isEmpty()) {
            errorMessage = "Quantity is empty";
            lblQuantityErrMsg.setText(errorMessage);
            validate = false;
        } else {
            lblQuantityErrMsg.setText("");
        }


        if (dpOrderDate.getValue() == null) {
            errorMessage = "Please select a date";
            lblDateErrMsg.setText(errorMessage);
            validate = false;
        } else {
            lblDateErrMsg.setText("");
        }

        if (TfProductID.getText().isEmpty()) {
            errorMessage = "Please enter the product id";
            lblProductIDErrMsg.setText(errorMessage);
            validate = false;
        } else {
            lblProductIDErrMsg.setText("");
        }

        return validate;
    }

    //add to database
    public void submitToDatabase(MouseEvent event) throws IOException {
        if (validation()) {
            ArrayList<OrderItem> orderItem = new ArrayList<>();
            if (ObservableListProducts.size() != 0) {
                orderItem.addAll(ObservableListProducts.stream().toList());

                Order order = new Order(TfOrderID.getText(), dpOrderDate.getValue(), orderItem);
                boolean hasError = order.saveToDatabase(order);

                //show pop up message
                primaryStage = new Stage();
                primaryStage.setTitle(hasError == true ? "There is an error" : "Success");
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

                Label message = new Label(hasError == true ? "There is an error" : "Your order has been saved successfully");


                VBox layout = new VBox(10);
                layout.setStyle(hasError == true ? "-fx-background-color: cornsilk; -fx-padding: 10;" : "-fx-background-color: lightgreen; -fx-padding: 10;");
                layout.getChildren().addAll(show, message);
                layout.setAlignment(Pos.CENTER);
                primaryStage.setScene(new Scene(layout, 400, 400));
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.setResizable(false);
                primaryStage.show();

                MainPage.orderArrayList.clear();
                MainPage.orderArrayList.addAll(order.getAllOrders());
            }

        }
    }
}
