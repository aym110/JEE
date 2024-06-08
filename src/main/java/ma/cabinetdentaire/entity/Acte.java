package ma.cabinetdentaire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.entity.enums.CategorieActe;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Acte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double prixDeBase;
    @Enumerated(EnumType.STRING)
    private CategorieActe categorieActe;
    @OneToMany(mappedBy = "acte" ,cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<InterventionMedecin> interventionsMedecin = new ArrayList<>();

}
