
package tn.esprit.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

@Schema(description = "User learning preferences")
public record UserPreferenceDTO(

        @Schema(description = "Unique identifier of the preferences")
        UUID id,

        @Schema(description = "Preferred language", example = "French")
        String languePreferee,

        @Schema(description = "User's learning objective", example = "Improve Java and Spring Boot skills")
        @Size(max = 500, message = "Learning objective must be at most 500 characters")
        String objectifApprentissage,

        @Schema(description = "Preferred learning categories", example = "[\"Programming\", \"Artificial Intelligence\", \"Databases\"]")
        Set<String> categoriesPreferees

) {
}
