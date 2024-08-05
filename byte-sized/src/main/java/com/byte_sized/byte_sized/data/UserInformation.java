package com.byte_sized.byte_sized.data;

import com.byte_sized.byte_sized.model.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInformation {
    //user
    private int userId;
    private String userName;
    // profile
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    // assignedRole
    private String roleName;

    // user image
    private Image profileImage;

}