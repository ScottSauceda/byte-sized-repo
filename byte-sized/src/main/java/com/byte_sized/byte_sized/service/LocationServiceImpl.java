package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.exception.LocationNotFoundException;
import com.byte_sized.byte_sized.model.Location;
import com.byte_sized.byte_sized.repository.LocationRepository;
import com.byte_sized.byte_sized.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public List<Location> getLocations() throws LocationNotFoundException {
        List<Location> locations = new ArrayList();
        if(locationRepository.findAll().isEmpty()){
            throw new LocationNotFoundException("No Locations to return");
        } else {
            List<Location> dbLocations = locationRepository.findAll();
            for(Location location: dbLocations){
                locations.add(location);
            }
        }
        return locations;
    }

    @Transactional
    public Optional<Location> getLocationByLocationId(Integer locationId) throws LocationNotFoundException {
        Optional <Location> location = null;
        if(locationRepository.findTopByLocationId(locationId).isEmpty()){
            throw new LocationNotFoundException("Location with Id: " + locationId + " does not exists. Please try again.");
        } else {
            location = locationRepository.findTopByLocationId(locationId);
        }
        return location;
    }

    @Transactional
    public String createLocation(Location newLocation){
        Location savedLocation = null;
        savedLocation = locationRepository.saveAndFlush(newLocation);

        if(savedLocation.getLocationId() != null){
            return "New Location created with Id: " + savedLocation.getLocationId();
        } else {
            return "Something went wrong. Please try again";
        }
    }

    @Transactional
    public String updateLocation(Integer locationId, Location updateLocation) throws LocationNotFoundException {
        Location dbLocation = locationRepository.findTopByLocationId(locationId).orElse(null);

        if(dbLocation == null) {
            throw new LocationNotFoundException("Location with id: " + locationId + " does not exists. Please try again");
        } else {
            dbLocation.setLocationName(updateLocation.getLocationName());
            dbLocation.setAddress (updateLocation.getAddress() );
            dbLocation.setCity (updateLocation.getCity() );
            dbLocation.setState (updateLocation.getState() );
            dbLocation.setZipCode (updateLocation.getZipCode());

            return "Location has been updated successfully";
        }
    }

    @Transactional
    public String deleteLocation(Integer locationId) throws LocationNotFoundException {
        Location dbLocation = locationRepository.findTopByLocationId(locationId).orElse(null);

        if(dbLocation == null){
            throw new LocationNotFoundException("Location with Id: " + locationId + " does not exists. Please try again.");
        } else {
            locationRepository.delete(dbLocation);
            return "Location has been deleted successfully";
        }
    }
}