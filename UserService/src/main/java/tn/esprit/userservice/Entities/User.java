package tn.esprit.userservice.Entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String keycloakId;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus statut = AccountStatus.ACTIF;

    private LocalDateTime dateCreation;

    // Relation avec Profile
    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Profile profile;

    // Relation avec UserPreference
    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private UserPreference userPreference;

    @PrePersist
    public void prePersist() {
        if (dateCreation == null) {
            dateCreation = LocalDateTime.now();
        }
    }

    // Méthodes pour synchroniser les relations
    public void setProfile(Profile profile) {
        this.profile = profile;

        if (profile != null) {
            profile.setUser(this);
        }
    }

    public void setUserPreference(UserPreference userPreference) {
        this.userPreference = userPreference;

        if (userPreference != null) {
            userPreference.setUser(this);
        }
    }
}
