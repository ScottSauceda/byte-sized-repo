package com.byte_sized.byte_sized.controller;

import com.byte_sized.byte_sized.exception.ImageNotFoundException;
import com.byte_sized.byte_sized.exception.ImageTypeNotFoundException;
import com.byte_sized.byte_sized.model.AssignedImageType;
import com.byte_sized.byte_sized.service.AssignedImageTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assignedImageType")
public class AssignedImageTypeController {
    @Autowired
    AssignedImageTypeService assignedImageTypeService;

    @GetMapping(value = "/assignedImageTypes")
    public ResponseEntity<List<AssignedImageType>> getAssignedImageTypes() throws ImageTypeNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(assignedImageTypeService.getAssignedImageTypes());
        } catch(ImageTypeNotFoundException imageTypeNotFoundException) {
            return new ResponseEntity(imageTypeNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/assignedImageTypes/{imagesTypeId}")
    public ResponseEntity<List<AssignedImageType>> getAssignedImagesByImagesTypeId(@PathVariable Integer imagesTypeId) throws ImageNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(assignedImageTypeService.getAssignedImagesByImagesTypeId(imagesTypeId));
        } catch(ImageTypeNotFoundException imageTypeNotFoundException) {
            return new ResponseEntity(imageTypeNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/image/{imageId}")
    public ResponseEntity<Optional<AssignedImageType>> getAssignedImageTypeByImageId(@PathVariable Integer imageId) throws ImageNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(assignedImageTypeService.getAssignedImageTypeByImageId(imageId));
        } catch(ImageNotFoundException imageNotFoundException) {
            return new ResponseEntity(imageNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}