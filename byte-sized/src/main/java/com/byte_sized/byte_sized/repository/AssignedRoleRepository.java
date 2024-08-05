package com.byte_sized.byte_sized.repository;

import com.byte_sized.byte_sized.model.AssignedRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignedRoleRepository extends JpaRepository<AssignedRole, Integer> {
    Optional<AssignedRole> findByUsersId(Integer userId);
}