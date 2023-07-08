package com.example.oop_group_project_inventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;


public class Login implements Initializable {
    @FXML
    private Label lbPasswordError;

    @FXML
    private TextField tfPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void changeStage() throws Exception{


        Stage stageOftheLabel = (Stage) lbPasswordError.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(inventoryApplication.class.getResource("mainPage.fxml"));

        stageOftheLabel.setScene(new Scene(fxmlLoader.load(), 600, 400));
    }

//    public void checkForm(){
//        if(tfPassword.getText().isBlank()){
//
//            lbPasswordError.setText("Please enter a valid password");
//            lbPasswordError.setVisible(true);
//
//        }
//    }




}
