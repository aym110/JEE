package ma.cabinetdentaire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.entity.enums.Assurance;
import ma.cabinetdentaire.entity.enums.Disponibilite;
import ma.cabinetdentaire.entity.enums.Specialite;
import ma.cabinetdentaire.entity.enums.StatusEmploye;
import org.hibernate.annotations.CreationTimestamp;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data @NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dentiste extends Utilisateur{
    @CreationTimestamp
    private LocalDate dateRetourConge;
    private double salaireDeBase;

    @Enumerated(EnumType.STRING)
    private Specialite specialite;

    @Enumerated(EnumType.STRING)
    private Assurance assurance;

    @Enumerated(EnumType.STRING)
    private StatusEmploye statusActuel;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Map<DayOfWeek, Disponibilite> disponibilite;
    @OneToMany(mappedBy = "dentiste")
    private List<DossierMedical> dossiersMedicaux=new ArrayList<>() ;


}
