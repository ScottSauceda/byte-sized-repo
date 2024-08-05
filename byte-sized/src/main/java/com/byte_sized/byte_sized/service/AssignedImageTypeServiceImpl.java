package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.exception.ImageNotFoundException;
import com.byte_sized.byte_sized.exception.ImageTypeNotFoundException;
import com.byte_sized.byte_sized.model.AssignedImageType;
import com.byte_sized.byte_sized.repository.AssignedImageTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssignedImageTypeServiceImpl implements AssignedImageTypeService {

    @Autowired
    AssignedImageTypeRepository assignedImageTypeRepository;

    @Transactional
    public List<AssignedImageType> getAssignedImageTypes() throws ImageTypeNotFoundException {
        List<AssignedImageType> assignedImageTypes = new ArrayList<>();
        if(assignedImageTypeRepository.findAll().isEmpty()){
            throw new ImageTypeNotFoundException("No AssignedImageType");
        } else {
            List<AssignedImageType> dbAssignedImageTypes = assignedImageTypeRepository.findAll();
            for(AssignedImageType assignedImageType: dbAssignedImageTypes){
                assignedImageTypes.add(assignedImageType);
            }
            return assignedImageTypes;
        }
    }

    @Transactional
    public List<AssignedImageType> getAssignedImagesByImagesTypeId(Integer imagesTypeId) throws ImageTypeNotFoundException  {
        List<AssignedImageType> assignedImageTypes = new ArrayList<>();
        if(assignedImageTypeRepository.findByImagesTypeId(imagesTypeId).isEmpty()){
            throw new ImageTypeNotFoundException("No AssignedImageType for that ImageType exists..");
        } else {
            List<AssignedImageType> dbAssignedImageTypes = assignedImageTypeRepository.findByImagesTypeId(imagesTypeId);
            for(AssignedImageType assignedImageType: dbAssignedImageTypes){
                assignedImageTypes.add(assignedImageType);
            }
            return assignedImageTypes;
        }
    }

    @Transactional
    public Optional<AssignedImageType> getAssignedImageTypeByImageId(Integer imageId) throws ImageNotFoundException {
        Optional<AssignedImageType> assignedImageType = null;
        if(assignedImageTypeRepository.findByImagesId(imageId).isEmpty()){
            throw new ImageTypeNotFoundException("No AssignedImageType for that ImageType exists.");
        } else {
           assignedImageType = assignedImageTypeRepository.findByImagesId(imageId);
        }
        return assignedImageType;
    }
}