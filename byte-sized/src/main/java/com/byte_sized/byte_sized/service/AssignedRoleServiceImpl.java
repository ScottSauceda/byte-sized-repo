package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.exception.RoleNotFoundException;
import com.byte_sized.byte_sized.model.AssignedRole;
import com.byte_sized.byte_sized.repository.AssignedRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssignedRoleServiceImpl implements AssignedRoleService {

    @Autowired
    AssignedRoleRepository assignedRoleRepository;

    @Transactional
    public List<AssignedRole> getAssignedRoles() throws RoleNotFoundException {
        List<AssignedRole> assignedRoles = new ArrayList<>();
        if(assignedRoleRepository.findAll().isEmpty()){
            throw new RoleNotFoundException("No AssignedRoles to return");
        } else {
            List<AssignedRole> dbAssignedRoles = assignedRoleRepository.findAll();
            for(AssignedRole assignedRole: dbAssignedRoles){
                assignedRoles.add(assignedRole);
            }
            return assignedRoles;
        }
    }

    @Transactional
    public Optional<AssignedRole> getAssignedRoleByUserId(Integer userId) {
        return Optional.empty();
    }

    @Transactional
    public String assignRoleToUser(AssignedRole newAssignedRole) {
        return null;
    }

    @Transactional
    public String deleteAssignedRole(Integer userId) {
        return null;
    }
}