package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.exception.ImageNotFoundException;
import com.byte_sized.byte_sized.exception.UserNotFoundException;
import com.byte_sized.byte_sized.model.Image;
import com.byte_sized.byte_sized.repository.ImageRepository;
import com.byte_sized.byte_sized.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<Image> getImages() throws ImageNotFoundException {
        List<Image> images = new ArrayList();
        if(imageRepository.findAll().isEmpty()){
            throw new ImageNotFoundException("No Images to return");
        } else {
            List<Image> dbImages = imageRepository.findAll();
            for(Image image: dbImages){
                images.add(image);
            }
        }
        return images;
    }

    @Transactional
    public List<Image> getUserImages(Integer userId) throws UserNotFoundException, ImageNotFoundException {
        List<Image> images = new ArrayList<>();
        if(imageRepository.findAll().isEmpty()){
            throw new ImageNotFoundException("No Images to return");
        } else if(imageRepository.findAllByImageUserId(userId).isEmpty()){
            throw new UserNotFoundException("No Images for that user to return");
        } else {
            List<Image> dbImages = imageRepository.findAllByImageUserId(userId);
            for(Image image: dbImages){
                images.add(image);
            }
            return images;
        }
    }
}
