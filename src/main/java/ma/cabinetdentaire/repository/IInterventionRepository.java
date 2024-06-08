package ma.cabinetdentaire.repository;

import ma.cabinetdentaire.entity.Consultation;
import ma.cabinetdentaire.entity.DossierMedical;
import ma.cabinetdentaire.entity.InterventionMedecin;
import ma.cabinetdentaire.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IInterventionRepository  extends JpaRepository<InterventionMedecin, Long> {

    List<InterventionMedecin> findAllByConsultation(Consultation consultation);

}
