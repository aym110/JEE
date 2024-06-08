package ma.cabinetdentaire.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.entity.enums.CategorieAntecedentsMedicaux;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AntecedentMedicale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String libelle;
    @Enumerated(EnumType.STRING)
    CategorieAntecedentsMedicaux categorieAntecedentsMedicaux;
    @ManyToMany(mappedBy = "antecedentMedicaleList")
    List<Patient> patients;
}
