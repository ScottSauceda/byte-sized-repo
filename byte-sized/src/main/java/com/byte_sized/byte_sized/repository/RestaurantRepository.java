package com.byte_sized.byte_sized.repository;

import com.byte_sized.byte_sized.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    // Useful only for testing as there can be multiple restaurants under the same name.
    Optional<Restaurant> findTopByRestaurantName(String name);

    Optional<Restaurant> findTopByRestaurantId(Integer restaurantId);

    List<Restaurant> findAllByOwnerUserId(Integer ownerId);
}