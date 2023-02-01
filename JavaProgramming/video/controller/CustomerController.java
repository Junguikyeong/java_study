package video.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController {
    private Connection connection;

    public CustomerController(Connection connection) {
        this.connection = connection;
    }

    public boolean confirm(String email) {
        String query = "SELECT * FROM sakila.customer WHERE `email`  = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,email);

            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void register(int storeId, String firstName, String lastName, String email, String address) {
        int addressId = 0;
        String query = "SELECT * FROM sakila.address where address = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, address);
            ResultSet addressRes = pstmt.executeQuery();
            if (addressRes.next()) {
                addressId = addressRes.getInt("address_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "INSERT INTO sakila.customer (`store_id`,`first_name`,`last_name`,`email`,`address_id`,`create_date`) VALUES(?,?,?,?,?,NOW())";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, storeId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, email);
            pstmt.setInt(5, addressId);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int storeId, String firstName, String lastName, String email, String address,String originEmail){
        int addressId = 0;
        String query = "SELECT * FROM sakila.address where address = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, address);
            ResultSet addressRes = pstmt.executeQuery();
            if (addressRes.next()) {
                addressId = addressRes.getInt("address_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "SELECT * FROM sakila.customer WHERE `email`  = ?";
        int customerId = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,originEmail);

            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                customerId = resultSet.getInt("customer_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "UPDATE `sakila`.`customer` SET `store_id` = ?,`first_name` = ?,`last_name` = ?,`email` = ?,`address_id` = ? WHERE (`customer_id` = ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, storeId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, email);
            pstmt.setInt(5, addressId);
            pstmt.setInt(6,customerId);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String email){
        String query = "SELECT * FROM sakila.customer WHERE `email`  = ?";
        int customerId = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,email);

            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                customerId = resultSet.getInt("customer_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "DELETE FROM `sakila`.`customer` WHERE (`customer_id` = ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, customerId);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}