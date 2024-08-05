package com.byte_sized.byte_sized.repository;

import com.byte_sized.byte_sized.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findTopByRoleName(String roleName);
}