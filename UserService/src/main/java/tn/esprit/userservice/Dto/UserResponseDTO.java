
package tn.esprit.userservice.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "User information returned by the API")
public record UserResponseDTO(

        @Schema(description = "Unique identifier of the user", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,

        @Schema(description = "User's last name", example = "Ben Ali")
        String nom,

        @Schema(description = "User's first name", example = "Ranim")
        String prenom,

        @Schema(description = "User's email address", example = "ranim@example.com")
        String email,

        @Schema(description = "User account status", example = "ACTIF")
        String statut,

        @Schema(description = "Account creation date", example = "2026-11-20T10:30:00")
        LocalDateTime dateCreation

) {
}
