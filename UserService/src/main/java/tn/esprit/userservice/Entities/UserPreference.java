
package tn.esprit.userservice.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_preferences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String languePreferee;

    private String objectifApprentissage;

    // Liste des catégories préférées
    @ElementCollection
    @CollectionTable(
            name = "user_preferred_categories",
            joinColumns = @JoinColumn(name = "preference_id")
    )
    @Column(name = "category")
    private Set<String> categoriesPreferees = new HashSet<>();

    // Relation avec User
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}
