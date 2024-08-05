package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.model.Location;

import java.util.List;
import java.util.Optional;
public interface LocationService {
    public List<Location> getLocations();

    public Optional<Location> getLocationByLocationId(Integer locationId);

    public String createLocation(Location newLocation);

    public String updateLocation(Integer locationId, Location updateLocation);

    public String deleteLocation(Integer locationId);
}