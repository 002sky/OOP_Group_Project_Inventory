package com.example.oop_group_project_inventory;

public class AStaticClass {
    private static String errorMessage;

    public static void assignErrorMessage(String message) {
        errorMessage = message;
    }

    public static String getErrorMessage() {

        return errorMessage;
    }
}
