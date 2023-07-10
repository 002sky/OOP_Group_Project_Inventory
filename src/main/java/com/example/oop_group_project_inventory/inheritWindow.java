package com.example.oop_group_project_inventory;

import javafx.stage.Stage;
import javafx.stage.Window;

abstract class InheritWindow extends Window {

    Stage aWindowStage;

    public InheritWindow() {
    }

    public Stage getTheStage(){
        return aWindowStage;
    }
}
