package com.byte_sized.byte_sized.repository;


import com.byte_sized.byte_sized.model.AssignedImageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssignedImageTypeRepository extends JpaRepository<AssignedImageType, Integer> {
    Optional<AssignedImageType> findByImagesId(Integer imagesId);

    List<AssignedImageType> findByImagesTypeId(Integer imageTypeId);
}