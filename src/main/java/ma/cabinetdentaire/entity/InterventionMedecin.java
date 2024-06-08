package ma.cabinetdentaire.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class InterventionMedecin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String noteMedecin;
    private Double prixPatient;
    private Long dent;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "acte_id")
    private Acte acte;


}
