package video.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CountryController {
    private Connection connection;

    public CountryController(Connection connection) {
        this.connection = connection;
    }

    public boolean register(String country){
        String query = "INSERT INTO sakila.country (`country`) VALUES(?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,country);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

}
