
package tn.esprit.userservice.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Data used to create a user")
public record UserRequestDTO(

        @Schema(description = "User's last name", example = "Ben Ali")
        @NotBlank(message = "Last name is required")
        @Size(max = 100, message = "Last name must be at most 100 characters")
        String nom,

        @Schema(description = "User's first name", example = "Ranim")
        @NotBlank(message = "First name is required")
        @Size(max = 100, message = "First name must be at most 100 characters")
        String prenom,

        @Schema(description = "User's email address", example = "ranim@example.com")
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @Schema(description = "Keycloak user identifier", example = "a1b2c3d4-e5f6-7890")
        @NotBlank(message = "Keycloak ID is required")
        String keycloakId

) {
}
