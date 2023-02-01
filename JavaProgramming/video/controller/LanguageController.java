package video.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageController {
    private Connection connection;

    public LanguageController(java.sql.Connection connection) {
        this.connection = connection;
    }

    public boolean confirm(String language) {
        String query = "SELECT * FROM sakila.language WHERE `name`  = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,language);

            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void register(String language){
        String query = "INSERT INTO sakila.language (`name`) VALUES(?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,language);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
