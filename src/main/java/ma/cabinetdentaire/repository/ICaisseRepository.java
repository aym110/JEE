package ma.cabinetdentaire.repository;

import ma.cabinetdentaire.entity.Caisse;
import ma.cabinetdentaire.entity.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICaisseRepository extends JpaRepository<Caisse, Long> {
}
