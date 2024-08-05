package com.byte_sized.byte_sized.controller;

import com.byte_sized.byte_sized.exception.LocationNotFoundException;
import com.byte_sized.byte_sized.model.Location;
import com.byte_sized.byte_sized.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping(value = "/locations")
    public ResponseEntity<List<Location>> getLocations() throws LocationNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.getLocations());
        } catch(LocationNotFoundException locationNotFoundException){
            return new ResponseEntity(locationNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/{locationId}")
    public ResponseEntity<Optional<Location>> getLocationByLocationId(@PathVariable Integer locationId) throws LocationNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.getLocationByLocationId(locationId));
        } catch(LocationNotFoundException locationNotFoundException){
            return new ResponseEntity(locationNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createLocation(@RequestBody Location newLocation) throws LocationNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.createLocation(newLocation));
        } catch(LocationNotFoundException locationNotFoundException){
            return new ResponseEntity(locationNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update/{locationId}")
    public ResponseEntity<String> updateLocation(@PathVariable Integer locationId, @RequestBody Location updateLocation) throws LocationNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.updateLocation(locationId, updateLocation));
        } catch(LocationNotFoundException locationNotFoundException) {
            return new ResponseEntity(locationNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{locationId}")
    public ResponseEntity<String> deleteLocation(@PathVariable Integer locationId) throws LocationNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.deleteLocation(locationId));
        } catch(LocationNotFoundException locationNotFoundException) {
            return new ResponseEntity(locationNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}