package com.byte_sized.byte_sized.repository;

import com.byte_sized.byte_sized.model.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageTypeRepository extends JpaRepository<ImageType, Integer> {
    Optional<ImageType> findTopByImageTypeId(Integer imageTypeId);
}