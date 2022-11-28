package com.codecool.fileshare.repository;

import com.codecool.fileshare.model.Image;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("jdbc")
public class ImageJdbcRepository implements ImageRepository {

    @Override
    public String storeImageFile(String title, String description, String owner, byte[] content, String extension) {
        //TODO
        throw new RuntimeException("Not yet implemented!");
    }

    @Override
    public boolean checkOwner(String owner, String id) {
        //TODO owner = email, id = image id
        throw new RuntimeException("Not yet implemented!");
    }

    @Override
    public List<Image> getAll(String owner) {
        //TODO
        throw new RuntimeException("Not yet implemented!");
    }

    @Override
    public void delete(String uuid, String owner) {
        //TODO
        throw new RuntimeException("Not yet implemented!");
    }

    @Override
    public void updateImage(String id, String title, String description, String owner) {
        //TODO
        throw new RuntimeException("Not yet implemented!");
    }

    @Override
    public byte[] getImageFile(String id) {
        //TODO
        throw new RuntimeException("Not yet implemented!");
    }
}
