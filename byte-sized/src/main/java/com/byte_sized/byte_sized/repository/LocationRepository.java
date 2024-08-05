package com.byte_sized.byte_sized.repository;

import com.byte_sized.byte_sized.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Optional<Location>  findTopByLocationName(String restaurantName);
    Optional<Location>  findTopByLocationId(Integer locationId);
}