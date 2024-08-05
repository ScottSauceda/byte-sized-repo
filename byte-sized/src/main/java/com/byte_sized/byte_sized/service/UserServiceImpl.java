package com.byte_sized.byte_sized.service;


import com.byte_sized.byte_sized.data.NewUserInformation;
import com.byte_sized.byte_sized.data.UserInformation;
import com.byte_sized.byte_sized.exception.ProfileNotFoundException;
import com.byte_sized.byte_sized.exception.UserNotFoundException;
import com.byte_sized.byte_sized.model.AssignedRole;
import com.byte_sized.byte_sized.model.Profile;
import com.byte_sized.byte_sized.model.User;
import com.byte_sized.byte_sized.repository.AssignedRoleRepository;
import com.byte_sized.byte_sized.repository.ProfileRepository;
import com.byte_sized.byte_sized.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AssignedRoleRepository assignedRoleRepository;

    @Transactional
    public List<User> getUsers() throws UserNotFoundException {
        List<User> users = new ArrayList<>();
        if(userRepository.findAll().isEmpty()){
            throw new UserNotFoundException("No Users to return");
        } else {
            List<User> dbUsers = userRepository.findAll();
            for(User user: dbUsers){
                users.add(user);
            }
            return users;
        }
    }

    @Transactional
    public List<UserInformation> getAllUserInformation() throws UserNotFoundException{
        List<UserInformation>  allUserInformation = new ArrayList<>();

        if(profileRepository.findAll().isEmpty()){
            throw new ProfileNotFoundException("Something went wrong, please try again.");
        } else {
            for(Profile dbProfile: profileRepository.findAll()){
                allUserInformation.add(getUserInformation(dbProfile.getProfileUserId()));
            }
        }
        return allUserInformation;
    }

    @Transactional
    public UserInformation getUserByUserId(Integer userId) throws UserNotFoundException {
        UserInformation userInformation = null;
        if(userRepository.findTopByUserId(userId).isEmpty()){
            throw new UserNotFoundException("User with userId: " + userId + "does not exists. Please try again.");
        } else {
            userInformation = getUserInformation(userId);
        }
        return userInformation;
    }

    @Transactional
    public String createUser(NewUserInformation newUserInformation) throws UserNotFoundException {
        System.out.println("newUserInformation");
        System.out.println(newUserInformation);

        // Create User
        User newUser = new User();
        User savedUser = new User();

        newUser.setUserName(newUserInformation.getUserName());
        newUser.setPassword(newUserInformation.getPassword());
        savedUser = userRepository.saveAndFlush(newUser);

        if(userRepository.findTopByUserId(savedUser.getUserId()).isEmpty()){
            return "Something went wrong. Please try again. U";
        } else {
            System.out.println("New User created with Id: " + savedUser.getUserId());

            // Assign Role to new user;
            AssignedRole newAssignedRole = new AssignedRole();
            AssignedRole savedAssignedRole = new AssignedRole();
            newAssignedRole.setUsersId(savedUser.getUserId());
            newAssignedRole.setRolesId(newUserInformation.getRoleId());

            System.out.println("newAssignedrole");
            System.out.println(newAssignedRole);
            System.out.println("newAssignedrole.RoleId");
            System.out.println(newAssignedRole.getRolesId());
            System.out.println("newAssignedrole.UserId");
            System.out.println(newAssignedRole.getUsersId());

            savedAssignedRole = assignedRoleRepository.saveAndFlush(newAssignedRole);
            if(assignedRoleRepository.findByUsersId(savedAssignedRole.getRolesId()).isEmpty()){
                System.out.println("Something went wrong. Please try again. R");
            } else {
                System.out.println("Role assigned successfully");
            }

            //  Create Profile for user
            Profile savedProfile = new Profile();
            Profile newProfile = new Profile();
            newProfile.setFirstName(newUserInformation.getFirstName());
            newProfile.setLastName(newUserInformation.getLastName());
            newProfile.setEmail(newUserInformation.getEmail());
            newProfile.setPhone(newUserInformation.getPhone());
            newProfile.setProfileUserId(savedUser.getUserId());
            savedProfile = profileRepository.saveAndFlush(newProfile);

            if(profileRepository.findByProfileUserId(savedProfile.getProfileUserId()).isEmpty()){
                return "Something went wrong. Please try again. P";
            } else {
                return "Profile created successfully";
            }

        }
    }

    @Transactional
    public UserInformation getUserInformation(Integer userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();

        Optional<Profile> optionalProfile = profileRepository.findByProfileUserId(userId);
        Profile profile = optionalProfile.get();

        UserInformation userInformation = new UserInformation();

        // set user information
        userInformation.setUserId(user.getUserId());
        userInformation.setUserName(user.getUserName());

        // set profile information
        userInformation.setFirstName(profile.getFirstName());
        userInformation.setLastName(profile.getLastName());
        userInformation.setEmail(profile.getEmail());
        userInformation.setPhone(profile.getPhone());

        // set user role information
        if(user.getUserRole() != null){
            System.out.println("User is assigned a role");
            userInformation.setRoleName(user.getUserRole().getRoleName());
        } else {
            System.out.println("No role found for user.");
        }

        // set user image information

        return userInformation;
    }

}