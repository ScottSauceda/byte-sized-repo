package com.byte_sized.byte_sized.controller;

import com.byte_sized.byte_sized.exception.RoleNotFoundException;
import com.byte_sized.byte_sized.model.AssignedRole;
import com.byte_sized.byte_sized.service.AssignedRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/assignedRole")
public class AssignedRoleController {

    @Autowired
    AssignedRoleService assignedRoleService;

    @GetMapping(value = "/assignedRoles")
    public ResponseEntity<List<AssignedRole>> getAssignedRoles() throws RoleNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(assignedRoleService.getAssignedRoles());
        } catch(RoleNotFoundException roleNotFoundException){
            return new ResponseEntity(roleNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}