package com.codecool.fileshare.repository;

import com.codecool.fileshare.exception.UserAlreadyExistsException;
import com.codecool.fileshare.model.AppUser;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.codecool.fileshare.repository.DbConstants.*;

@Repository
public class UserJdbcRepository implements UserRepository{


    @Override
    public AppUser findByUsername(String email) {
        final String SQL = "SELECT email, password FROM app_user WHERE email = ?;";
        AppUser appUser = null;
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                //TODO is the password encrypted here or not, how do we need it?
                appUser = new AppUser(email, rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appUser;
    }

    @Override
    public void save(AppUser appUser) throws UserAlreadyExistsException {
        //TODO
        throw new RuntimeException("Not yet implemented!");
    }

    @Override
    public List<AppUser> getAppUsers() {
        //TODO
        throw new RuntimeException("Not yet implemented!");
    }
}
