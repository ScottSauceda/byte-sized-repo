package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.exception.RoleNotFoundException;
import com.byte_sized.byte_sized.model.Role;
import com.byte_sized.byte_sized.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Transactional
    public List<Role> getRoles() throws RoleNotFoundException {
        List<Role> roles = new ArrayList<>();
        if(roleRepository.findAll().isEmpty()){
            throw new RoleNotFoundException("No Roles to return");
        } else {
            List<Role> dbRoles = roleRepository.findAll();
            for(Role role: dbRoles){
                roles.add(role);
            }
        }
        return roles;
    }

    @Transactional
    public Optional<Role> getRoleById(Integer roleId) throws RoleNotFoundException {
        Optional<Role> role = null;
        if(roleRepository.findById(roleId).isEmpty()){
            throw new RoleNotFoundException("Role with Id: " + roleId + " does not exists. Please Try again.");
        } else {
            role = roleRepository.findById(roleId);
        }
        return role;
    }

    @Transactional
    public String createRole(Role newRole) {
        Role savedRole = null;
        savedRole = roleRepository.saveAndFlush(newRole);

        if(savedRole.getRoleId() != null){
            return "New Role created with Id: " + savedRole.getRoleId();
        } else {
            return "Something went wrong. Please try again.";
        }
    }

    @Transactional
    public String updateRole(Integer roleId, Role updateRole) throws RoleNotFoundException {
        Role dbRole = roleRepository.findById(roleId).orElse(null);

        if(dbRole == null){
            throw new RoleNotFoundException("Role with Id: " + roleId + " does not exists. Please try again.");
        } else {
            dbRole.setRoleName(updateRole.getRoleName());

            return "Role has been updated successfully";
        }
    }

    @Transactional
    public String deleteRole(Integer roleId) throws RoleNotFoundException {
        Role dbRole = roleRepository.findById(roleId).orElse(null);

        if(dbRole == null){
            throw new RoleNotFoundException("Role with Id: " + roleId + " does not exists. Please try again.");
        } else {
            roleRepository.delete(dbRole);
            return "Role has been deleted successfully";
        }
    }
}