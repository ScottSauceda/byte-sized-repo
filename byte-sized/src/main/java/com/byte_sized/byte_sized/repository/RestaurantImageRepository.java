package com.byte_sized.byte_sized.repository;

import com.byte_sized.byte_sized.model.RestaurantImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RestaurantImageRepository extends JpaRepository<RestaurantImage, Integer> {
    List<RestaurantImage> findAllByRestaurantsId(Integer restaurantId);

    Optional<RestaurantImage> findByImagesId(Integer imageId);
}