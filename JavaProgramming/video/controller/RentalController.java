package video.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalController {
    private Connection connection;

    public RentalController(Connection connection){
        this.connection = connection;
    }

    public boolean confirm(int inventoryId, int staffId) {
        String query = "SELECT * FROM sakila.rental where staff_id = ? and inventory_id = ? and return_date is null";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, staffId);
            pstmt.setInt(2, inventoryId);

            ResultSet resultSet = pstmt.executeQuery();
            // true면 빌린거 false면 반납된거
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void rental(String email,int inventoryId, int staffId) {
        int customerId = 0;
        String query = "SELECT * FROM sakila.customer where email = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                customerId = resultSet.getInt("customer_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "INSERT INTO sakila.rental (`rental_date`, `inventory_id`, `customer_id`,`staff_id`) VALUES(NOW(),?,?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, inventoryId);
            pstmt.setInt(2, customerId);
            pstmt.setInt(3, staffId);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnVideo(String email,int inventoryId,int staffId){
        int customerId = 0;
        String query = "SELECT * FROM sakila.customer where email = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                customerId = resultSet.getInt("customer_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int rentalId = 0;
        query = "SELECT * FROM sakila.rental where inventory_id = ? and customer_id = ? and staff_id = ? and return_date is null";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, inventoryId);
            pstmt.setInt(2,customerId);
            pstmt.setInt(3,staffId);

            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                rentalId = resultSet.getInt("rental_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "UPDATE `sakila`.`rental` SET `return_date` = NOW() WHERE (`rental_id` = ?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, rentalId);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
