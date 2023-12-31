package com.example.oop_group_project_inventory.model;

import java.sql.*;
import java.util.ArrayList;


/**
 *  Clothing class with extend Product
 */
public class Clothing extends Product {
    private String clothingType;
    private String clothingSize;
    private String color;
    private String material;

    /**
     * constructor with no parameter
     */
    public Clothing() {

    }

    /**
     * constructor with all parameter
     * @param clothingType accept clothingType as String
     * @param clothingSize accept clothingSize as String
     * @param color accept color as String
     * @param material accept material as String
     */
    public Clothing(String productID, String productName, double unitPrice, double sellingPrice, String productBrand, boolean productStatus, String clothingType, String clothingSize, String color, String material) {
        super(productID, productName, unitPrice, sellingPrice, productBrand, productStatus);
        this.clothingType = clothingType;
        this.clothingSize = clothingSize;
        this.color = color;
        this.material = material;
    }


    /**
     * getters
     * @return return clothing type as String
     */
    public String getClothingType() {
        return clothingType;
    }

    /**
     * getters
     * @return return clothing size as String
     */
    public String getClothingSize() {
        return clothingSize;
    }

    /**
     * getters
     * @return return color as String
     */
    public String getColor() {
        return color;
    }

    /**
     * getters
     * @return return material as String
     */
    public String getMaterial() {
        return material;
    }

    /**
     * setters
     * @param clothingType accept clothingType as String
     */
    public void setClothingType(String clothingType) {
        this.clothingType = clothingType;
    }

    /**
     * setters
     * @param clothingSize accept clothingSize as String
     */
    public void setClothingSize(String clothingSize) {
        this.clothingSize = clothingSize;
    }

    /**
     * setters
     * @param color accept color as String
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @param material accept material as String
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * load data from database
     * @return return the clothing list as ArrayList
     */
    public ArrayList<Clothing> loadFromDatabase() {
        ArrayList<Clothing> ClothingList = new ArrayList<>();
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";

        try {
            Connection connection = DriverManager.getConnection(url);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String sql = "SELECT * FROM Clothing LEFT JOIN Product ON Clothing.productID = Product.productID";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                String id = result.getString("productID");
                String name = result.getString("Product.productName");
                double unitPrice = result.getDouble("Product.unitPrice");
                double sellingPrice = result.getDouble("Product.sellingPrice");
                String brand = result.getString("Product.productBrand");
                boolean status = result.getBoolean("Product.productStatus");
                String type = result.getString("clothingType");
                String size = result.getString("clothingSize");
                String color = result.getString("color");
                String material = result.getString("material");

                Clothing a = new Clothing(id, name, unitPrice, sellingPrice, brand, status, type, size, color, material);

                ClothingList.add(a);


            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ClothingList;

    }

    /**
     * update the clothing data in the database by clothing object
     * @param clothing accept a clothing object as parameter
     * @return return a boolean value to determine the success or failure
     */
    //update from database
    public boolean updateProduct(Clothing clothing) {
        String url = "jdbc:ucanaccess://src/main/resources/Inventory.accdb";

        boolean hasError = false;
        try {
            Connection connection = DriverManager.getConnection(url);

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            int result = super.updateProduct(clothing);

            if (result >= 1) {

                String newSql = "UPDATE Clothing SET  clothingType = ?, clothingSize = ?, color = ?, material = ? WHERE productID = ?";
                PreparedStatement newStatement = connection.prepareStatement(newSql);

                newStatement.setString(1, clothing.getClothingType());
                newStatement.setString(2, clothing.getClothingSize());
                newStatement.setString(3, clothing.getColor());
                newStatement.setString(4, clothing.getMaterial());
                newStatement.setString(5, clothing.getProductID());

                newStatement.executeUpdate();

                System.out.println("Clothing Updated");
            } else {
                hasError = true;
            }
            //@todo if there is an error return an error message to the user


        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return hasError;
    }

}
