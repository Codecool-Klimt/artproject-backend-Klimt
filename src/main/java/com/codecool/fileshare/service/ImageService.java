package com.codecool.fileshare.service;

import com.codecool.fileshare.dto.ImageDataDTO;
import com.codecool.fileshare.dto.ImageUpdateDTO;
import com.codecool.fileshare.model.Image;
import com.codecool.fileshare.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    @Autowired
    @Qualifier("jdbc")
    private ImageRepository imageRepository;

    public List<ImageDataDTO> getAll(String user) {
        return imageRepository.getAll(user).stream()
                .map(i -> new ImageDataDTO(i.getId(), i.getTitle(), i.getDescription(),
                        System.getenv("url") + "/api/artwork/" + i.getId() + "." + i.getExtension(), i.getTags()))
                .collect(Collectors.toList());
    }

    public boolean checkOwner(String owner, String id) {
        return imageRepository.checkOwner(owner, id);
    }

    public void delete(String id, String owner) {
        imageRepository.delete(id, owner);
    }

    public void updateCategory(String id, ImageUpdateDTO imageUpdateDTO, String owner) {
        imageRepository.updateImage(id, imageUpdateDTO.getTitle(), imageUpdateDTO.getDescription(), imageUpdateDTO.getTags(), owner);
    }

    public byte[] getImageFile(String filename) {
        //help: filename is for example 41d6608d-0803-4239-9235-09f902fbf705.jpg
        String id = filename.split("\\.")[0];
        return imageRepository.getImageFile(id);
    }

    public String storeFile(MultipartFile file, String title, String description, String tags, String owner) {
        //help: filename is for example 41d6608d-0803-4239-9235-09f902fbf705.jpg
        try {
            String extension = file.getOriginalFilename().split("\\.")[1];
            return imageRepository.storeImageFile(title, description, owner, file.getBytes(), extension, tags);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
