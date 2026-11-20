
package tn.esprit.userservice.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.userservice.dto.UserPreferenceDTO;
import tn.esprit.userservice.Entities.User;
import tn.esprit.userservice.Entities.UserPreference;
import tn.esprit.userservice.Mapper.UserMapper;
import tn.esprit.userservice.Repositories.UserPreferenceRepository;
import tn.esprit.userservice.Repositories.UserRepository;
import tn.esprit.userservice.Exceptions.ConflictException;
import tn.esprit.userservice.Exceptions.ResourceNotFoundException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserPreferenceServiceImpl implements UserPreferenceService {

    private final UserPreferenceRepository preferenceRepository;
    private final UserRepository userRepository;

    @Override
    public UserPreferenceDTO createPreferences(UUID userId, UserPreferenceDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        if (preferenceRepository.existsByUserId(userId)) {
            throw new ConflictException("Preferences already exist");
        }

        UserPreference preference = UserMapper.toPreferenceEntity(dto);

        user.setUserPreference(preference);

        UserPreference savedPreference = preferenceRepository.save(preference);

        return UserMapper.toPreferenceDTO(savedPreference);
    }

    @Override
    @Transactional(readOnly = true)
    public UserPreferenceDTO getPreferencesByUserId(UUID userId) {

        UserPreference preference = preferenceRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Preferences not found")
                );

        return UserMapper.toPreferenceDTO(preference);
    }

    @Override
    public UserPreferenceDTO updatePreferences(UUID userId, UserPreferenceDTO dto) {

        UserPreference preference = preferenceRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Preferences not found")
                );

        preference.setLanguePreferee(dto.languePreferee());
        preference.setObjectifApprentissage(dto.objectifApprentissage());

        if (dto.categoriesPreferees() != null) {
            preference.setCategoriesPreferees(
                    new java.util.HashSet<>(dto.categoriesPreferees())
            );
        }

        UserPreference updatedPreference = preferenceRepository.save(preference);

        return UserMapper.toPreferenceDTO(updatedPreference);
    }
}
