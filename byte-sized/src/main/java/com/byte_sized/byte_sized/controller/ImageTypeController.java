package com.byte_sized.byte_sized.controller;

import com.byte_sized.byte_sized.exception.ImageTypeNotFoundException;
import com.byte_sized.byte_sized.model.ImageType;
import com.byte_sized.byte_sized.service.ImageTypeService;
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
@RequestMapping("/imageType")
public class ImageTypeController {

    @Autowired
    ImageTypeService imageTypeService;

    @GetMapping(value = "/imageTypes")
    public ResponseEntity<List<ImageType>> getImageTypes() throws ImageTypeNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(imageTypeService.getImageTypes());
        } catch(ImageTypeNotFoundException imageTypeNotFoundException){
            return new ResponseEntity(imageTypeNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{imageTypeId}")
    public ResponseEntity<Optional<ImageType>> getImageTypeByImageTypeId(@PathVariable Integer imageTypeId) throws ImageTypeNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(imageTypeService.getImageTypeByImageTypeId(imageTypeId));
        } catch(ImageTypeNotFoundException imageTypeNotFoundException){
            return new ResponseEntity(imageTypeNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}