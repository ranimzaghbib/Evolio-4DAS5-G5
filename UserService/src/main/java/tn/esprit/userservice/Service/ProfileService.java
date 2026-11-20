
package tn.esprit.userservice.Service;

import tn.esprit.userservice.Dto.ProfileDTO;

import java.util.UUID;

public interface ProfileService {

    ProfileDTO createProfile(UUID userId, ProfileDTO dto);

    ProfileDTO getProfileByUserId(UUID userId);

    ProfileDTO updateProfile(UUID userId, ProfileDTO dto);
}
