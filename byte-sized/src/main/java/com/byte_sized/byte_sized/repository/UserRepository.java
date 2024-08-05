package com.byte_sized.byte_sized.repository;

import com.byte_sized.byte_sized.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findTopByUserName(String userName);
    Optional<User> findTopByUserId(Integer userId);
}
