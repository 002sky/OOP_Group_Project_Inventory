module com.example.oop_group_project_inventory {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires ucanaccess;


    opens com.example.oop_group_project_inventory to javafx.fxml;
    exports com.example.oop_group_project_inventory;
}