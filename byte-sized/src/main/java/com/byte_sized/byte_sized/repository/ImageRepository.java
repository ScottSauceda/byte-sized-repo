package com.byte_sized.byte_sized.repository;

import com.byte_sized.byte_sized.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findAllByImageUserId(Integer userId);
}