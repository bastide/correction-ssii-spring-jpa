package ssii.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class Projet {
    @Id
    @Setter(AccessLevel.NONE) // Ne génère pas de setter pour cet attribut
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @NotBlank
    @NotEmpty
    @Column(nullable = false, unique = true)
    @NonNull
    private String nom;

    @Column(nullable = false)
    private LocalDate debut = LocalDate.now();

    private LocalDate fin;

    @ToString.Exclude
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private List<Participation> contributeurs = new ArrayList<>();


}