package ssii.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Personne {

    @Id
    @Setter(AccessLevel.NONE) // Ne génère pas de setter pour cet attribut
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matricule;

    @Column(nullable = false)
    @NonNull
    private String nom;

    @Column(nullable = false)
    @NonNull
    private String prenom;

    @Column(nullable = false)
    @NonNull
    private String poste;

    @ToString.Exclude
    @OneToMany(mappedBy = "personne", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Participation> affectations = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne
    private Personne superieur;

    @ToString.Exclude
    @OneToMany(mappedBy = "superieur", orphanRemoval = true)
    private List<Personne> subordonnes = new ArrayList<>();

    /**
     * Ajoute une participation à un projet pour cette personne
     * @param affectation Le projet auquel la personne participe
     * @param role Le rôle de la personne dans le projet
     * @param pourcentage Le pourcentage de temps que la personne consacre au projet
     */
    public void addParticipation(Projet affectation, String role, float pourcentage) {
        var participation = new Participation(role, pourcentage, affectation, this);
        affectations.add(participation);
    }
}
