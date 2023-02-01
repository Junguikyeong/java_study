package video.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryController {
    private Connection connection;

    public InventoryController(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Integer> confirmList(int videoId, int storeId) {
        ArrayList<Integer> inventoryIdList = new ArrayList<>();
        String query = "SELECT * FROM sakila.inventory WHERE `film_id`  = ? AND `store_id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,videoId);
            pstmt.setInt(2,storeId);

            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                int inventoryId = resultSet.getInt("inventory_id");
                inventoryIdList.add(inventoryId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryIdList;
    }

    public void delete(int id){
        String query = "DELETE FROM `sakila`.`inventory` WHERE (`inventory_id` = ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
