
package tn.esprit.userservice.Entities;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.userservice.Entities.User;

import java.time.LocalDate;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private java.util.UUID id;

    private String photoUrl;

    @Column(length = 1000)
    private String bio;

    private String telephone;

    private String specialite;

    private LocalDate dateNaissance;

    // Relation avec User
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}
