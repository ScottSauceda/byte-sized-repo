package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.model.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    public List<Profile> getUserProfiles();

    public Optional<Profile> getProfileByUserId(Integer userId);

    public String createUserProfile(Profile newProfile);

    public String updateProfile(Integer userId, Profile updateProfile);

    public String deleteProfile(Integer userId);
}