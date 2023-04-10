package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome to Payroll Service Using JDBC");

        Connection connection = DriverManager.getConnection(OS.url, OS.user, OS.password);
        Statement statement = connection.createStatement();

        String createDatabase = "CREATE DATABASE payroll_service_JDBC";
        String useDatabase = "USE payroll_service_JDBC";

        statement.execute(createDatabase);
        statement.execute(useDatabase);


        String deleteDatabase = "DROP DATABASE payroll_service_JDBC";


        System.out.print("Do you want to delete the Table (Yes/No) - ");
        String choice = new Scanner(System.in).next();

        if (choice.equalsIgnoreCase("yes")) {
            statement.execute(deleteDatabase);
            System.out.println("Tables deleted successfully....");
        }

    }
}