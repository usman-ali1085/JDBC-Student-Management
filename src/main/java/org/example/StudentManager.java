package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentManager {
    private final Scanner input = new Scanner(System.in);


    public void deleteStudent() {
        System.out.print("Enter Student ID to Delete: ");
        int id = input.nextInt();
        String sqlQuery = "DELETE FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlQuery)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Deleted Successfully");
            }
            else {
                System.out.println("Student Not Found!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void updateStudent() {
        System.out.print("Enter Student ID to Update Details: ");
        int id = input.nextInt();
        input.nextLine();
        System.out.print("Enter Name: ");
        String name = input.nextLine();
        System.out.print("Enter Age: ");
        int age = input.nextInt();
        System.out.print("Enter Marks: ");
        int marks = input.nextInt();

        boolean valid = !name.isBlank()
                && age > 0
                && marks >= 0
                && marks <= 100;

        String sqlQuery = "UPDATE students SET name = ?, age = ?, marks = ? WHERE id = ?";
        if (valid) {
            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sqlQuery)) {

                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setInt(3, marks);
                ps.setInt(4, id);
                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Student Updated Successfully");
                }
                else {
                    System.out.println("Student Not Found!");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("Name, Age or Marks Invalid!");
        }
    }


    public void searchStudent() {
        System.out.print("Enter Student ID to Search: ");
        int id = input.nextInt();
        String sqlQuery = "SELECT * FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlQuery)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    System.out.println("Student Found");
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("Student Name: " + rs.getString("name"));
                    System.out.println("Age: " + rs.getInt("age"));
                    System.out.println("Marks: " + rs.getInt("marks"));
                } else {
                    System.out.println("Student Not Found!");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void viewAllStudents() {
        String sqlQuery = "SELECT * FROM students";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlQuery);
        ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            while(rs.next()) {
                found = true;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Student Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Marks: " + rs.getInt("marks"));
                System.out.println();
            }
            if (!found) {
                System.out.println("No Students Found!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void showMenu() {
        int choice;
        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Student name: ");
                    String name = input.nextLine();
                    System.out.print("Enter age: ");
                    int age = input.nextInt();
                    System.out.print("Enter marks: ");
                    int marks = input.nextInt();

                    boolean valid = !name.isBlank()
                            && age > 0
                            && marks >= 0
                            && marks <= 100;

                    String sqlQuery = "INSERT INTO students (name, age, marks) VALUES (?, ?, ?)";

                    if (valid) {
                        try (Connection con = DBConnection.getConnection();
                             PreparedStatement ps = con.prepareStatement(sqlQuery)) {

                            ps.setString(1, name);
                            ps.setInt(2, age);
                            ps.setInt(3, marks);

                            int rows = ps.executeUpdate();
                            System.out.println(rows + " Student Added Successfully.");

                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    else {
                        System.out.println("Age or Marks Invalid!");
                    }

                    break;

                case 2:
                    viewAllStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    System.out.println("Exit Successful");
                    break;

                default:
                    System.out.println("Invalid Choice! Enter choice between 1 and 6");
            }

        } while (choice != 6);
    }
}