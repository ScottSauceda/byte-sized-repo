package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.model.AssignedImageType;

import java.util.List;
import java.util.Optional;

public interface AssignedImageTypeService {
    public List<AssignedImageType> getAssignedImageTypes();

    public List<AssignedImageType> getAssignedImagesByImagesTypeId(Integer imagesTypeId);

    public Optional<AssignedImageType> getAssignedImageTypeByImageId(Integer imageId);


}