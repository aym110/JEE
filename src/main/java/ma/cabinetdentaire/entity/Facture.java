package ma.cabinetdentaire.entity;

import ma.cabinetdentaire.entity.enums.StatutPaiement;
import ma.cabinetdentaire.entity.enums.TypePaiement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.entity.enums.TypePaiement;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "facture")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "situationFinanciere_id")
    private SituationFinanciere situationFinanciere;
    private double montantRestant;
    private double montantPaye;
    @CreationTimestamp
    private LocalDate dateFacturation;
    private double montantTotal;
    private TypePaiement typePaiement;
    @Enumerated(EnumType.STRING)
    private StatutPaiement statutPaiement;
}