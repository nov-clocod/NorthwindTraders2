package com.pluralsight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                System.out.println("Error on required application details to run");
                System.exit(1);
            }

            String username = args[0];
            String password = args[1];

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    username,
                    password
            );

            String query = """
                    SELECT ProductID, ProductName, UnitPrice, UnitsInStock
                    FROM products
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                int productID = results.getInt(1);
                String productName = results.getString(2);
                double productPrice = results.getDouble(3);
                int productStock = results.getInt(4);

                System.out.println("Product ID: " + productID);
                System.out.println("Name: " + productName);
                System.out.println("Price: " + productPrice);
                System.out.println("Stock: " + productStock);
                System.out.println("--------------------------");
            }

            results.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println("Error occur!");
            System.out.println(ex.getMessage());
        }
    }
}
