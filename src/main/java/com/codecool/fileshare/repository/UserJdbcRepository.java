package com.codecool.fileshare.repository;

import com.codecool.fileshare.exception.UserAlreadyExistsException;
import com.codecool.fileshare.model.AppUser;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.codecool.fileshare.repository.DbConstants.*;

@Repository
public class UserJdbcRepository implements UserRepository {


    @Override
    public AppUser findByEmail(String email) {
        final String SQL = "SELECT email, password FROM app_user WHERE email = ?;";
        AppUser appUser = null;
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                appUser = new AppUser(email, rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appUser;
    }

    @Override
    public void save(AppUser appUser) throws UserAlreadyExistsException {
        final String SQL = "INSERT INTO app_user(email, password) VALUES(?, ?);";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, appUser.getEmail());
            st.setString(2, appUser.getPassword());
        } catch (SQLException e) {
            throw new UserAlreadyExistsException("Email " + appUser.getEmail() + " is already in use!", e);
        }
    }

    @Override
    public List<AppUser> getAppUsers() {
        final String SQL = "SELECT email, password FROM app_user;";
        List<AppUser> appUsers = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                var appUser = new AppUser(rs.getString(1), rs.getString(2));
                appUsers.add(appUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appUsers;
    }
}
