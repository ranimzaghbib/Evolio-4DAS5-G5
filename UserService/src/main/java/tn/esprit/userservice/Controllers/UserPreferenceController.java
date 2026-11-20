
package tn.esprit.userservice.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import tn.esprit.userservice.dto.UserPreferenceDTO;
import tn.esprit.userservice.Service.UserPreferenceService;

import java.util.UUID;

@RestController
@RequestMapping("/api/users/{userId}/preferences")
@RequiredArgsConstructor
public class UserPreferenceController {

    private final UserPreferenceService preferenceService;

    @PostMapping
    public UserPreferenceDTO createPreferences(
            @PathVariable UUID userId,
            @Valid @RequestBody UserPreferenceDTO dto) {

        return preferenceService.createPreferences(userId, dto);
    }

    @GetMapping
    public UserPreferenceDTO getPreferences(@PathVariable UUID userId) {

        return preferenceService.getPreferencesByUserId(userId);
    }

    @PutMapping
    public UserPreferenceDTO updatePreferences(
            @PathVariable UUID userId,
            @Valid @RequestBody UserPreferenceDTO dto) {

        return preferenceService.updatePreferences(userId, dto);
    }
}
