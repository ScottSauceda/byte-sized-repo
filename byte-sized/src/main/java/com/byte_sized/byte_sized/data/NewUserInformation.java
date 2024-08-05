package com.byte_sized.byte_sized.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserInformation {

    // User
    private String userName;
    private String password;

    // Profile
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    // AssignedRole
    private Integer roleId;

}