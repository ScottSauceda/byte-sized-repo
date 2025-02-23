package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.model.AssignedRole;

import java.util.List;
import java.util.Optional;

public interface AssignedRoleService {
    public List<AssignedRole> getAssignedRoles();

    public Optional<AssignedRole> getAssignedRoleByUserId(Integer userId);

    public String assignRoleToUser(AssignedRole newAssignedRole);

    public String deleteAssignedRole(Integer userId);
}