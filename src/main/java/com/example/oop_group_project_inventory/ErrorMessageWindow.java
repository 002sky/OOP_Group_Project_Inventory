package com.example.oop_group_project_inventory;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class ErrorMessageWindow extends InheritWindow {


    public ErrorMessageWindow(){}

    public ErrorMessageWindow(String errorMessage) throws IOException {
        AStaticClass.assignErrorMessage(errorMessage);
        Parent root = FXMLLoader.load(getClass().getResource("errorMessage.fxml"));
        aWindowStage = new Stage();
        aWindowStage.setTitle("Error");
        aWindowStage.initModality(Modality.APPLICATION_MODAL);
        aWindowStage.setScene(new Scene(root, 500, 275));
        aWindowStage.show();


    }

}
