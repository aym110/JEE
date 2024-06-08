package ma.cabinetdentaire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DossierMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String numeroDossier;
    @CreationTimestamp
    LocalDate dateCreation;
    @OneToOne
    @JoinColumn(name="patientId") //
    Patient patient;
    @ManyToOne
    @JoinColumn(name = "dentiste_id")
    Dentiste dentiste;
    @OneToMany(mappedBy = "dossierMedicale",cascade = CascadeType.ALL ,orphanRemoval = true)
    List<Consultation> consultations;
    @OneToOne
    @JoinColumn(name = "situationFinanciereId")
    private SituationFinanciere situationFinanciere;
}
