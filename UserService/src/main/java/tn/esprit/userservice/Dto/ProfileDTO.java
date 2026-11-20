
package tn.esprit.userservice.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

@Schema(description = "User personal profile information")
public record ProfileDTO(

        @Schema(description = "Unique identifier of the profile")
        UUID id,

        @Schema(description = "URL of the user's profile photo", example = "https://example.com/photo.jpg")
        String photoUrl,

        @Schema(description = "User biography", example = "Computer science student passionate about software development")
        @Size(max = 1000, message = "Biography must be at most 1000 characters")
        String bio,

        @Schema(description = "User phone number", example = "+21612345678")
        String telephone,

        @Schema(description = "User's specialty", example = "Software Engineering")
        String specialite,

        @Schema(description = "User's date of birth", example = "2003-05-15", type = "string", format = "date")
        LocalDate dateNaissance

) {
}
