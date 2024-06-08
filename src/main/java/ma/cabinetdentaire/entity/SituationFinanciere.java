package ma.cabinetdentaire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "situationFinanciere")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SituationFinanciere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSituationFinanciere;
    private double montantGlobalePaye;
    @OneToMany(mappedBy = "situationFinanciere")
    private List<Facture> factures = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "caisse_id")
    private Caisse caisse;
    private LocalDate dateCreation;
    private double montantGlobaleRestant;
    @OneToOne(mappedBy = "situationFinanciere")
    private DossierMedical dossierMedical;
}

