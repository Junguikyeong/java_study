package video.controller;

import video.model.VideoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoController {
    private Connection connection;

    public VideoController(Connection connection) {
        this.connection = connection;
    }

    public boolean confirm(String title) {
        String query = "SELECT * FROM sakila.film WHERE `title`  = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,title);

            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void register(String language, String title, String description, String releaseYear, int rentalDuration, String rating, String specialFeatures) {
        int languageId = 0;
        String query = "SELECT * FROM sakila.language where name = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, language);
            ResultSet videoRes = pstmt.executeQuery();
            if (videoRes.next()) {
                languageId = videoRes.getInt("language_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "INSERT INTO sakila.film (`title`,`description`,`release_Year`,`language_id`, `rental_duration`,`rating`,`special_features`) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setString(3, releaseYear);
            pstmt.setInt(4, languageId);
            pstmt.setInt(5, rentalDuration);
            pstmt.setString(6, rating);
            pstmt.setString(7, specialFeatures);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public VideoDTO titleSearch(String title){
        VideoDTO v = null;
        String query = "SELECT * FROM sakila.film where title = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,title);

            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                v = new VideoDTO();
                v.setId(resultSet.getInt("film_id"));
                v.setTitle(resultSet.getString("title"));
                v.setDescription(resultSet.getString("description"));
                v.setReleaseYear(resultSet.getString("release_year"));
                v.setLanguageId(resultSet.getInt("language_id"));
                v.setRentalDuration(resultSet.getInt("rental_duration"));
                v.setRentalRate(resultSet.getDouble("rental_rate"));
                v.setRating(resultSet.getString("rating"));
                v.setSpecialFeatures(resultSet.getString("special_features"));
                v.setLastUpdate(resultSet.getTimestamp("last_update"));
            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    public void update(String language, String title, String description, String releaseYear, int rentalDuration, String rating, String specialFeatures,String oldTitle){
        int languageId = 0;
        String query = "SELECT * FROM sakila.language where name = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, language);
            ResultSet videoRes = pstmt.executeQuery();
            if (videoRes.next()) {
                languageId = videoRes.getInt("language_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "SELECT * FROM sakila.film WHERE `title`  = ?";
        int filmId = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,oldTitle);

            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                filmId = resultSet.getInt("film_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "UPDATE sakila.film SET `title` = ?,`description` = ?,`release_Year` = ?,`language_id` = ?, `rental_duration` = ?,`rating` = ?,`special_features` = ? WHERE `film_id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setString(3, releaseYear);
            pstmt.setInt(4, languageId);
            pstmt.setInt(5, rentalDuration);
            pstmt.setString(6, rating);
            pstmt.setString(7, specialFeatures);
            pstmt.setInt(8, filmId);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(String title){
        String query = "SELECT * FROM sakila.film WHERE `title`  = ?";
        int filmId = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,title);

            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                filmId = resultSet.getInt("film_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "DELETE FROM `sakila`.`film` WHERE (`film_id` = ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, filmId);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cateSearch(String videoCate){

    }

    public void actorSearch(String videoActor){

    }
}
