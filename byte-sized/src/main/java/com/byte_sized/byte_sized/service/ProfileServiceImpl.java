package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.exception.ProfileNotFoundException;
import com.byte_sized.byte_sized.exception.UserNotFoundException;
import com.byte_sized.byte_sized.model.Profile;
import com.byte_sized.byte_sized.repository.ProfileRepository;
import com.byte_sized.byte_sized.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<Profile> getUserProfiles() throws ProfileNotFoundException {
        List<Profile> userProfiles = new ArrayList<>();
        if(profileRepository.findAll().isEmpty()){
            throw new ProfileNotFoundException("No UserProfiles to return");
        } else {
            List<Profile> dbUserProfiles = profileRepository.findAll();
            for(Profile userProfile: dbUserProfiles){
                userProfiles.add(userProfile);
            }
            return userProfiles;
        }
    }

    @Transactional
    public Optional<Profile> getProfileByUserId(Integer userId) throws ProfileNotFoundException {
        Optional<Profile> profile = null;
        if(profileRepository.findByProfileUserId(userId).isEmpty()){
            throw new ProfileNotFoundException("Profile with userId: " + userId + "does not exists. Please try again.");
        } else {
            profile = profileRepository.findByProfileUserId(userId);
        }
        return profile;
    }

    @Transactional
    public String createUserProfile(Profile newProfile) throws UserNotFoundException {
        Profile savedProfile = new Profile();
        if(userRepository.findById(newProfile.getProfileUserId()).isEmpty()){
            throw new UserNotFoundException("User not found for userId: " + newProfile.getProfileUserId() +". Could not create Profile for user;");
        } else {
            savedProfile = profileRepository.saveAndFlush(newProfile);
            if(profileRepository.findByProfileUserId(newProfile.getProfileUserId()).isEmpty()){
                throw new ProfileNotFoundException("Profile not found for userId: " + newProfile.getProfileUserId() + ". Please try again.");
            } else {
                return "New UserProfile created from userId: " + savedProfile.getProfileUserId();
            }
        }
    }

    @Transactional
    public String updateProfile(Integer userId, Profile updateProfile) throws ProfileNotFoundException {
        Profile dbProfile = profileRepository.findByProfileUserId(userId).orElse(null);

        System.out.println("update user profile");
        System.out.println(updateProfile.getProfileUserId());
        System.out.println(updateProfile.getEmail());
        System.out.println(updateProfile.getFirstName());
        System.out.println(updateProfile.getLastName());

        if(dbProfile == null){
            throw new ProfileNotFoundException("Profile with Id: " + userId + " does not exists. Please try again.");
        } else {
            dbProfile.setFirstName(updateProfile.getFirstName());
            dbProfile.setLastName(updateProfile.getLastName());
            dbProfile.setEmail(updateProfile.getEmail());

            return "Profile has been updated successfully.";
        }
    }

    @Transactional
    public String deleteProfile(Integer userId) throws ProfileNotFoundException {
        Profile dbProfile = profileRepository.findByProfileUserId(userId).orElse(null);

        if(dbProfile == null){
            throw new ProfileNotFoundException("Profile with user Id: " + userId + " does not exists. Please try again.");
        } else {
            profileRepository.delete(dbProfile);
            return "User has been deleted successfully";
        }
    }
}