package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.model.Image;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    public List<Image> getImages();

    public List<Image> getUserImages(Integer userId);

    // create image

    // edit image

    // delete image
}