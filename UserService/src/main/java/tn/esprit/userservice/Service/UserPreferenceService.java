
package tn.esprit.userservice.Service;

import tn.esprit.userservice.dto.UserPreferenceDTO;

import java.util.UUID;

public interface UserPreferenceService {

    UserPreferenceDTO createPreferences(UUID userId, UserPreferenceDTO dto);

    UserPreferenceDTO getPreferencesByUserId(UUID userId);

    UserPreferenceDTO updatePreferences(UUID userId, UserPreferenceDTO dto);
}
