package com.codecool.fileshare.repository;

import com.codecool.fileshare.model.AppUser;
import com.codecool.fileshare.model.Image;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.codecool.fileshare.repository.DbConstants.*;

@Component("jdbc")
public class ImageJdbcRepository implements ImageRepository {

    @Override
    public String storeImageFile(String title, String description, String owner, byte[] content, String extension) {
        //TODO return generated id
        final String SQL = "INSERT INTO image(title, description, \"owner\", content, extension) VALUES(?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement st = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, title);
            st.setString(2, description);
            st.setString(3, owner);
            st.setBytes(4, content);
            st.setString(5, extension);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();

            return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkOwner(String owner, String id) {
        //TODO owner = email, id = image id
        final String SQL = "SELECT CASE WHEN EXISTS (\n" +
                "    SELECT id, \"owner\"\n" +
                "    FROM image\n" +
                "    WHERE \"owner\" = ? AND id::text = ?\n" +
                ")\n" +
                "THEN CAST(1 AS BIT)\n" +
                "ELSE CAST(0 AS BIT) END;";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, owner);
            st.setString(2, id);
            ResultSet rs = st.executeQuery();
            rs.next();

            return rs.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Image> getAll(String owner) {
        final String SQL = "SELECT id, title, description, content, extension FROM image WHERE \"owner\" = ?";
        List<Image> images = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, owner);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                byte[] content = rs.getBytes(4);
                String extension = rs.getString(5);
                images.add(new Image(id, title, description, owner, content, extension));
            }

            return images;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    @Override
    public void delete(String uuid, String owner) {
        final String SQL = "DELETE FROM image WHERE \"owner\" = ? AND id::text = ?";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, owner);
            st.setString(2, uuid);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateImage(String id, String title, String description, String owner) {
        final String SQL = "UPDATE image SET title = ?, description = ? WHERE id::text = ? AND \"owner\" = ?";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, title);
            st.setString(2, description);
            st.setString(3, id);
            st.setString(4, owner);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getImageFile(String id) {
        final String SQL = "SELECT content FROM image WHERE id::text = ?";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();

            if (!rs.next())
                return null;

            return rs.getBytes(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
