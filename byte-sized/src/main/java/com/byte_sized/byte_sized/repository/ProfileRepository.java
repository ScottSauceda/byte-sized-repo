package com.byte_sized.byte_sized.repository;

import com.byte_sized.byte_sized.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Optional<Profile> findTopByEmail(String email);

    Optional<Profile> findByProfileUserId(Integer userId);
}