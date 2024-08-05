package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.exception.ImageNotFoundException;
import com.byte_sized.byte_sized.exception.ImageTypeNotFoundException;
import com.byte_sized.byte_sized.model.ImageType;
import com.byte_sized.byte_sized.repository.ImageTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageTypeServiceImpl implements ImageTypeService {

    @Autowired
    ImageTypeRepository imageTypeRepository;

    @Transactional
    public List<ImageType> getImageTypes() throws ImageTypeNotFoundException {
        List<ImageType> imageTypes = new ArrayList<>();
        if(imageTypeRepository.findAll().isEmpty()){
            throw new ImageTypeNotFoundException("No ImageTypes to return");
        } else {
            List<ImageType> dbImageTypes = imageTypeRepository.findAll();
            for(ImageType imageType: dbImageTypes){
                imageTypes.add(imageType);
            }
            return imageTypes;
        }
    }

    @Transactional
    public Optional<ImageType> getImageTypeByImageTypeId(Integer imageTypeId) throws ImageTypeNotFoundException {
        Optional<ImageType> imageType = null;
        if(imageTypeRepository.findTopByImageTypeId(imageTypeId).isEmpty()){
            throw new ImageTypeNotFoundException("ImageType with imageTypeId : " + imageTypeId + " does not exists. Please try again.");
        } else {
            imageType = imageTypeRepository.findTopByImageTypeId(imageTypeId);
        }
        return imageType;
    }
}