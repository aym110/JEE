package ma.cabinetdentaire.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.entity.enums.TypeConsultation;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dossierMedical_id")
    private DossierMedical dossierMedicale;
    @CreationTimestamp
    private LocalDate dateConsultation;
    private TypeConsultation typeConsultation;
    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<InterventionMedecin> interventionsMedecin =new ArrayList<>();
//    @OneToMany(mappedBy = "consultation")
//    private List<Facture> factures =new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "factureId")
    private Facture facture;
}
