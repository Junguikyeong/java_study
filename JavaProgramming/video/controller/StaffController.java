package video.controller;

import video.model.StaffDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffController {

    private Connection connection;
    public StaffController(Connection connection){
        this.connection = connection;
    }

    public StaffDTO auth(String email, String password){
        String query = "SELECT * FROM `staff` WHERE `email` = ? AND `password` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,email);
            pstmt.setString(2,password);

            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet.next()){
                StaffDTO staffDTO = new StaffDTO();
                staffDTO.setId(resultSet.getInt("staff_id"));
                staffDTO.setEmail(resultSet.getString("email"));
                staffDTO.setPassword(resultSet.getString("password"));
                staffDTO.setStoreId(resultSet.getInt("store_id"));

                return staffDTO;
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}