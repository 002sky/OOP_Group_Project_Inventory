package com.example.oop_group_project_inventory;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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

public class AddInventoryPane {

    @FXML
    private TextField TfInventoryID, TfInventoryName;
    @FXML
    private TextArea TaInventoryAddress;
    @FXML
    private CheckBox CbFreezerAvailable;
    @FXML
    private Button BtnSubmit;
    @FXML
    private Label lblinventoryIDErrMsg, lblinventoryNameErrMsg, lblinventoryAddressErrMsg;
    public Stage primaryStage;

    public void resetLBLErrMsg() {
        lblinventoryIDErrMsg.setText("");
        lblinventoryNameErrMsg.setText("");
        lblinventoryAddressErrMsg.setText("");

        lblinventoryIDErrMsg.setTextFill(Color.color(1, 0, 0));
        lblinventoryNameErrMsg.setTextFill(Color.color(1, 0, 0));
        lblinventoryAddressErrMsg.setTextFill(Color.color(1, 0, 0));
    }

    public void AddInventory(MouseEvent event) throws IOException {
        Inventory inventory = new Inventory();
        if (!inventory.checkInventoryExists(TfInventoryID.getText())) {

            boolean check = validation(event);

            if (check) {
                resetLBLErrMsg();

                // TODO validation of the Textfield
                inventory = new Inventory(TfInventoryID.getText(), TfInventoryName.getText(), TaInventoryAddress.getText(), CbFreezerAvailable.isSelected());
                MainPage.inventoryArrayList.add(inventory);
                inventory.addInventory(inventory);
            }

        } else {
//            System.out.println("Inventory already exists");
//Show pop up error message
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

            Label errorMessage = new Label("Inventory already exists");


            VBox layout = new VBox(10);
            layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
            layout.getChildren().addAll(show, errorMessage);
            layout.setAlignment(Pos.CENTER);
            primaryStage.setScene(new Scene(layout, 400, 400));
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.show();

        }

        // TODO Error Message here
    }

    public boolean validation(MouseEvent event) throws IOException {
        boolean validate = true;
        if (!TfInventoryID.getText().isEmpty()) {
            lblinventoryIDErrMsg.setText("");
            String errorMessage = "";
            if (TfInventoryName.getText().isEmpty()) {
                errorMessage = "Inventory Name is empty";
                lblinventoryNameErrMsg.setText(errorMessage);
                validate = false;
            } else {
                lblinventoryNameErrMsg.setText("");
            }

            if (TaInventoryAddress.getText().isEmpty()) {
                errorMessage = "Inventory Address is empty";
                lblinventoryAddressErrMsg.setText(errorMessage);
                validate = false;
            } else {
                lblinventoryAddressErrMsg.setText("");
            }

        } else {
            String errorMessage = "Please enter the inventory id";
            lblinventoryIDErrMsg.setText(errorMessage);
            validate = false;
        }

        return validate;
    }


}
