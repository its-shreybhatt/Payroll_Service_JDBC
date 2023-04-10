package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome to Payroll Service Using JDBC");

        Connection connection = DriverManager.getConnection(OS.url, OS.user, OS.password);
        Statement statement = connection.createStatement();

        String createDatabase = "CREATE DATABASE payroll_service_JDBC";
        String useDatabase = "USE payroll_service_JDBC";

        String createTable = "CREATE TABLE employee_payroll" +
                "(id INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY ," +
                "name VARCHAR(150) NOT NULL, salary DOUBLE NOT NULL,start DATE NOT NULL)";

        String insertIntoTable = "INSERT INTO employee_payroll(name,salary,start) VALUES " +
                "('Shrey',1234,'2020-05-29'), " +
                "('Kartikeya',1111,'2021-01-01')," +
                "('Sapna',999,'2021-05-20');";


        statement.execute(createDatabase);
        statement.execute(useDatabase);
        statement.execute(createTable);
        statement.execute(insertIntoTable);

        String updateData = "UPDATE employee_payroll set salary = 30000 WHERE name='Sapna'";
        statement.execute(updateData);

        String nameToEdit = "Sapna";
        String updateUsingPrepared = String.format("UPDATE employee_payroll set salary = ?  WHERE name='%s'; ", nameToEdit);
        PreparedStatement newStatement = connection.prepareStatement(updateUsingPrepared);
        newStatement.setDouble(1, 2500);
        newStatement.execute();

        String deleteDatabase = "DROP DATABASE payroll_service_JDBC";

        System.out.print("Do you want to delete the Table (Yes/No) - ");
        String choice = new Scanner(System.in).next();

        if (choice.equalsIgnoreCase("yes")) {
            statement.execute(deleteDatabase);
            System.out.println("Tables deleted successfully....");
        }

    }
}