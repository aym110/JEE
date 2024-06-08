package ma.cabinetdentaire.repository;

import ma.cabinetdentaire.entity.Consultation;
import ma.cabinetdentaire.entity.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findAllByDossierMedicale(DossierMedical dossierMedical);
}
