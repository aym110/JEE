package ma.cabinetdentaire.repository;

import ma.cabinetdentaire.entity.DossierMedical;
import ma.cabinetdentaire.entity.Patient;
import ma.cabinetdentaire.entity.SituationFinanciere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoosierMedicaleRepository extends JpaRepository<DossierMedical, Long> {

    DossierMedical findByPatient(Patient patient);
    DossierMedical findByPatientId(Long id);
}
