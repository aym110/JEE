package ma.cabinetdentaire.repository;

import ma.cabinetdentaire.entity.DossierMedical;
import ma.cabinetdentaire.entity.Facture;
import ma.cabinetdentaire.entity.SituationFinanciere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFactureRepository extends JpaRepository<Facture, Long> {
    List<Facture> findBySituationFinanciere(SituationFinanciere situationFinanciere);
}
