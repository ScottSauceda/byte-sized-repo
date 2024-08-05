package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.data.NewUserInformation;
import com.byte_sized.byte_sized.data.UserInformation;
import com.byte_sized.byte_sized.exception.UserNotFoundException;
import com.byte_sized.byte_sized.model.Profile;
import com.byte_sized.byte_sized.model.User;

import java.util.List;
import java.util.Optional;
public interface UserService {
    public List<User> getUsers();

    public List<UserInformation> getAllUserInformation();

    public UserInformation getUserByUserId(Integer userId);

    public String createUser(NewUserInformation newUserInformation);
}
