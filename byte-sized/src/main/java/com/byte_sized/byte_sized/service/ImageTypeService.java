package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.model.ImageType;

import java.util.List;
import java.util.Optional;

public interface ImageTypeService {

    public List<ImageType> getImageTypes();

    public Optional<ImageType> getImageTypeByImageTypeId(Integer imageTypeId);

    // createImageType

    // editImageType

    // deleteImageType
}