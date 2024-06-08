package ma.cabinetdentaire.repository;

import ma.cabinetdentaire.entity.DossierMedical;
import ma.cabinetdentaire.entity.SituationFinanciere;
import org.hibernate.query.results.DomainResultCreationStateImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISituationFinancierRepository extends JpaRepository<SituationFinanciere, Long> {

    SituationFinanciere findByDossierMedical(DossierMedical dossierMedical);
}
