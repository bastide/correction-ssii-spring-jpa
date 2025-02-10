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
    @OneToMany(mappedBy = "personne", cascade = CascadeType.ALL)
    private List<Participation> affectations = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne
    private Personne superieur;

    @ToString.Exclude
    @OneToMany(mappedBy = "superieur")
    private List<Personne> subordonnes = new ArrayList<>();

}
