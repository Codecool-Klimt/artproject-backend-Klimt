package com.codecool.fileshare.repository;

import com.codecool.fileshare.model.Image;
import org.springframework.stereotype.Component;

import java.util.List;
//Recomenden implementation. store data about users and images in csv files.
//store the images in the same folder with image name like 41d6608d-0803-4239-9235-09f902fbf705.jpg
@Component("file")
public class ImageFileRepository implements ImageRepository {
    @Override
    public String storeImageFile(String title, String description, String owner, byte[] content, String extension) {
        //TODO
        throw new RuntimeException("Not yet implemented!");
    }

    @Override
    public boolean checkOwner(String owner, String id) {
        //TODO
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
