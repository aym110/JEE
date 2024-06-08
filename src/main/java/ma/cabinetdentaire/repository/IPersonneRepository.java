package ma.cabinetdentaire.repository;

import ma.cabinetdentaire.entity.Patient;
import ma.cabinetdentaire.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonneRepository extends JpaRepository<Personne, Long> {
}
