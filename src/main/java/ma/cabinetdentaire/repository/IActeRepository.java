package ma.cabinetdentaire.repository;

import ma.cabinetdentaire.entity.Acte;
import ma.cabinetdentaire.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActeRepository extends JpaRepository<Acte, Long> {
}
