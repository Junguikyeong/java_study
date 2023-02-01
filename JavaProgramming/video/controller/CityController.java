package video.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityController {
    private Connection connection;

    public CityController(Connection connection) {
        this.connection = connection;
    }

    public boolean confirm(String city) {
        String query = "SELECT * FROM sakila.city WHERE `city`  = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,city);

            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean register(String city,String country){
        int countryId = 0 ;
        String query = "SELECT * FROM sakila.country where country = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,country);
            ResultSet cityRes = pstmt.executeQuery();
            if(cityRes.next()){
                countryId = cityRes.getInt("country_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "INSERT INTO sakila.city (`city`,`country_id`) VALUES(?,?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,city);
            pstmt.setInt(2,countryId);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
