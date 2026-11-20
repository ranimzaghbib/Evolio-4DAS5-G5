
package tn.esprit.userservice.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.userservice.Dto.ProfileDTO;
import tn.esprit.userservice.Entities.Profile;
import tn.esprit.userservice.Entities.User;
import tn.esprit.userservice.Mapper.UserMapper;
import tn.esprit.userservice.Repositories.ProfileRepository;
import tn.esprit.userservice.Repositories.UserRepository;
import tn.esprit.userservice.Exceptions.ConflictException;
import tn.esprit.userservice.Exceptions.ResourceNotFoundException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Override
    public ProfileDTO createProfile(UUID userId, ProfileDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        if (profileRepository.existsByUserId(userId)) {
            throw new ConflictException("Profile already exists");
        }

        Profile profile = UserMapper.toProfileEntity(dto);

        user.setProfile(profile);

        Profile savedProfile = profileRepository.save(profile);

        return UserMapper.toProfileDTO(savedProfile);
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileDTO getProfileByUserId(UUID userId) {

        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profile not found")
                );

        return UserMapper.toProfileDTO(profile);
    }

    @Override
    public ProfileDTO updateProfile(UUID userId, ProfileDTO dto) {

        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profile not found")
                );

        profile.setPhotoUrl(dto.photoUrl());
        profile.setBio(dto.bio());
        profile.setTelephone(dto.telephone());
        profile.setSpecialite(dto.specialite());
        profile.setDateNaissance(dto.dateNaissance());

        Profile updatedProfile = profileRepository.save(profile);

        return UserMapper.toProfileDTO(updatedProfile);
    }
}
