
package tn.esprit.userservice.Mapper;

import tn.esprit.userservice.Dto.UserRequestDTO;
import tn.esprit.userservice.Dto.UserResponseDTO;
import tn.esprit.userservice.Dto.ProfileDTO;
import tn.esprit.userservice.dto.UserPreferenceDTO;

import tn.esprit.userservice.Entities.User;
import tn.esprit.userservice.Entities.Profile;
import tn.esprit.userservice.Entities.UserPreference;

/**
 * Converts User-related entities to DTOs and DTOs to entities.
 */
public final class UserMapper {

    private UserMapper() {
    }

    // Convert User Entity to UserResponseDTO
    public static UserResponseDTO toResponse(User user) {

        return new UserResponseDTO(
                user.getId(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                user.getStatut().name(),
                user.getDateCreation()
        );
    }

    // Convert UserRequestDTO to User Entity
    public static User toEntity(UserRequestDTO dto) {

        User user = new User();

        user.setNom(dto.nom());
        user.setPrenom(dto.prenom());
        user.setEmail(dto.email());
        user.setKeycloakId(dto.keycloakId());

        return user;
    }

    // Convert Profile Entity to ProfileDTO
    public static ProfileDTO toProfileDTO(Profile profile) {

        return new ProfileDTO(
                profile.getId(),
                profile.getPhotoUrl(),
                profile.getBio(),
                profile.getTelephone(),
                profile.getSpecialite(),
                profile.getDateNaissance()
        );
    }

    // Convert ProfileDTO to Profile Entity
    public static Profile toProfileEntity(ProfileDTO dto) {

        Profile profile = new Profile();

        profile.setPhotoUrl(dto.photoUrl());
        profile.setBio(dto.bio());
        profile.setTelephone(dto.telephone());
        profile.setSpecialite(dto.specialite());
        profile.setDateNaissance(dto.dateNaissance());

        return profile;
    }

    // Convert UserPreference Entity to UserPreferenceDTO
    public static UserPreferenceDTO toPreferenceDTO(
            UserPreference preference) {

        return new UserPreferenceDTO(
                preference.getId(),
                preference.getLanguePreferee(),
                preference.getObjectifApprentissage(),
                preference.getCategoriesPreferees()
        );
    }

    // Convert UserPreferenceDTO to UserPreference Entity
    public static UserPreference toPreferenceEntity(
            UserPreferenceDTO dto) {

        UserPreference preference = new UserPreference();

        preference.setLanguePreferee(dto.languePreferee());
        preference.setObjectifApprentissage(dto.objectifApprentissage());

        if (dto.categoriesPreferees() != null) {
            preference.setCategoriesPreferees(
                    new java.util.HashSet<>(dto.categoriesPreferees())
            );
        }

        return preference;
    }
}
