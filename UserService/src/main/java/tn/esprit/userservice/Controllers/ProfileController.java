
package tn.esprit.userservice.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import tn.esprit.userservice.Dto.ProfileDTO;
import tn.esprit.userservice.Service.ProfileService;

import java.util.UUID;

@RestController
@RequestMapping("/api/users/{userId}/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ProfileDTO createProfile(
            @PathVariable UUID userId,
            @Valid @RequestBody ProfileDTO dto) {

        return profileService.createProfile(userId, dto);
    }

    @GetMapping
    public ProfileDTO getProfile(@PathVariable UUID userId) {

        return profileService.getProfileByUserId(userId);
    }

    @PutMapping
    public ProfileDTO updateProfile(
            @PathVariable UUID userId,
            @Valid @RequestBody ProfileDTO dto) {

        return profileService.updateProfile(userId, dto);
    }
}
