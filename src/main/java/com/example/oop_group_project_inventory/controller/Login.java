package com.example.oop_group_project_inventory.controller;

import com.example.oop_group_project_inventory.inventoryApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Login implements Initializable {

    @FXML
    private Button btnLogin;
    @FXML
    private TextField tfPasswordShow, tfUsername;
    @FXML
    private PasswordField tfPasswordHidden;
    @FXML
    CheckBox showPasswordCheckBox;
    @FXML
    Label lblUserNameErrMsg, lblPasswordErrMsg;
    public Stage primaryStage;

    /**
     * reset all error messages
     */
    //set every error messages empty
    public void resetLBLErrMsg() {
        lblUserNameErrMsg.setText("");
        lblPasswordErrMsg.setText("");

        lblUserNameErrMsg.setTextFill(Color.color(1, 0, 0));
        lblPasswordErrMsg.setTextFill(Color.color(1, 0, 0));
    }

    /**
     * initialize
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetLBLErrMsg();
        this.handleShowPasswordChecked(null);
    }


    /**
     * change to main page if login is successful
     * @throws Exception
     */
    public void changeStage() throws Exception {
        Stage stageOftheLabel = (Stage) btnLogin.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(inventoryApplication.class.getResource("mainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stageOftheLabel.setScene(scene);

    }

    /**
     * login function (hardcoded username "Admin" and password "Admin123")
     * if login is successful, change to main page
     * if not, show error message
     * @throws Exception
     */
    //login function
    public void login() throws Exception {
        if (validation()) {
            resetLBLErrMsg();
            if (tfUsername.getText().equals("Admin") && passwordValue().equals("Admin123")) {
                changeStage();
            } else {
                primaryStage = new Stage();
                primaryStage.setTitle("Error");
                final Popup popup = new Popup();

                popup.setX(300);
                popup.setY(200);

                Button show = new Button("OK");
                show.setFont(Font.font(18));

                show.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
//                            popup.show(primaryStage);
                        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                    }
                });

                Label errorMessage = new Label("Invalid username or password");
                errorMessage.setFont(Font.font(18));

                VBox layout = new VBox(10);
                layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
                layout.getChildren().addAll(show, errorMessage);
                layout.setAlignment(Pos.CENTER);
                primaryStage.setScene(new Scene(layout, 400, 400));
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.setResizable(false);
                primaryStage.show();


            }
        }

    }

    /**
     * validation for every text field
     * @return true if every text field is not empty
     * @throws IOException
     */
    //validation for every text field
    public boolean validation() throws IOException {
        boolean validate = true;

        String errorMessage = "";
        if (tfUsername.getText().isEmpty()) {
            errorMessage = "Username field is empty";
            lblUserNameErrMsg.setText(errorMessage);
            validate = false;
        } else {
            lblUserNameErrMsg.setText("");
        }

        if (passwordValue().isEmpty()) {
            errorMessage = "Password field is empty";
            lblPasswordErrMsg.setText(errorMessage);
            validate = false;
        } else {
            lblPasswordErrMsg.setText("");
        }
        return validate;
    }

    /**
     * show and hide password
     */
    //show and hide password
    public void handleShowPasswordChecked(ActionEvent event) {
        if (showPasswordCheckBox.isSelected()) {
            tfPasswordShow.setText(tfPasswordHidden.getText());
            tfPasswordShow.setVisible(true);
            tfPasswordHidden.setVisible(false);
            return;
        }

        tfPasswordHidden.setText(tfPasswordShow.getText());
        tfPasswordHidden.setVisible(true);
        tfPasswordShow.setVisible(false);
    }

    /**
     * @return password value as string
     */
    //return password value
    private String passwordValue() {
        return showPasswordCheckBox.isSelected() ?
                tfPasswordShow.getText() : tfPasswordHidden.getText();
    }


}
