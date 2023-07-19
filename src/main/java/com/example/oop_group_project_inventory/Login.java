package com.example.oop_group_project_inventory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.net.URL;
import java.util.ResourceBundle;


public class Login implements Initializable {
    @FXML
    private Label lbPasswordError;
    @FXML
    private TextField tfPassword;
    @FXML
    private Tooltip toolTipTxtPassword;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    protected void passwordFieldTriggered() throws Exception {
        toolTipTxtPassword = new Tooltip("Enter your password");
        Tooltip.install(tfPassword, toolTipTxtPassword);
//        tfPassword.setTooltip(new Tooltip("Enter your password"));

    }


    public void changeStage() throws Exception {


        Stage stageOftheLabel = (Stage) lbPasswordError.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(inventoryApplication.class.getResource("mainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stageOftheLabel.setScene(scene);
//        stageOftheLabel.setMaximized(true);

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
