package com.codecool.fileshare.repository;

import com.codecool.fileshare.exception.UserAlreadyExistsException;
import com.codecool.fileshare.model.AppUser;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserJdbcRepository implements UserRepository{


    @Override
    public AppUser findByUsername(String username) {
        //TODO
        throw new RuntimeException("Not yet implemented!");
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
