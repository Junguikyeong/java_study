package video.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressController {

    private Connection connection;

    public AddressController(Connection connection) {
        this.connection = connection;
    }

    public boolean confirm(String address) {
        String query = "SELECT * FROM sakila.address WHERE `address`  = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,address);

            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void register(String address, String city, String district, String phone, int latitude, int longitude) {
        int cityId = 0;
        String query = "SELECT * FROM sakila.city where city = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, city);
            ResultSet countryRes = pstmt.executeQuery();
            if (countryRes.next()) {
                cityId = countryRes.getInt("city_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "INSERT INTO sakila.address (`address`,`city_id`,`district`,`phone`,`location`) VALUES(?,?,?,?,point(?,?))";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, address);
            pstmt.setInt(2, cityId);
            pstmt.setString(3, district);
            pstmt.setString(4, phone);
            pstmt.setInt(5, latitude);
            pstmt.setInt(6, longitude);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
