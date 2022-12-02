package com.codecool.fileshare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private String id;
    private String title;
    private String description;
    private String owner;
    private byte[] content;
    private String extension;
    private String tags;
}
