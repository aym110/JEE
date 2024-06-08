package ma.cabinetdentaire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.entity.enums.GroupSanguin;
import ma.cabinetdentaire.entity.enums.Mutuelle;

import java.time.LocalDate;
import java.util.List;

//@Embeddable pour dire que cette classe est table inclus dans une autre table, c'est pour une relation composante
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient extends Personne {
    String profession;
    LocalDate dateNaissance;
    @Enumerated(EnumType.STRING)
    Mutuelle mutuelle;
    @Enumerated(EnumType.STRING)
    GroupSanguin groupSanguin;
    @OneToOne(mappedBy = "patient",// il faut voir order de création
            cascade = {CascadeType.ALL, CascadeType.MERGE},// lire de hat vers le bas
            fetch = FetchType.LAZY, // EAGER pour avoir le dossier medicale de patient sans envoyer deux requete
            orphanRemoval = true ) // lire de bas vers le haut
    DossierMedical dossierMedical;
    @ManyToMany
    @JoinTable(
            name ="patient-antecedent",
            joinColumns =  @JoinColumn(name = "patientId"),
            inverseJoinColumns = @JoinColumn(name = "antecedentMedicalId")
    )

    List<AntecedentMedicale> antecedentMedicaleList;

    // Ajouter les consctruteur personnalisé avec super

    //@Embedded

//    @PrePersist
//    void getId(){
//
//    }
}
