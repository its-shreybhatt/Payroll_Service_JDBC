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

        String checkByDate = String.format("SELECT * FROM employee_payroll " +
                "WHERE start BETWEEN '%s' and '%s'; ", Date.valueOf("2020-12-29"), Date.valueOf("2022-01-01"));
        ResultSet result = statement.executeQuery(checkByDate);
        while (result.next()) {
            String id = result.getString("id");
            String name = result.getString("name");
            String salary = result.getString("salary");
            String start = result.getString("start");

            System.out.println(id + "- " + name + ", " + salary + ", " + start);
        }


        String deleteDatabase = "DROP DATABASE payroll_service_JDBC";
        System.out.print("Do you want to delete the Table (Yes/No) - ");
        String choice = new Scanner(System.in).next();

        if (choice.equalsIgnoreCase("yes")) {
            statement.execute(deleteDatabase);
            System.out.println("Tables deleted successfully....");
        }
    }
}