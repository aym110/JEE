package ma.cabinetdentaire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "caisse")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Caisse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double recetteDuJours;
    private double recetteDeLAnnee;
    private double recetteDumois;
    @OneToMany(mappedBy = "caisse")
    private List<SituationFinanciere> situationFinancieres;
}