package ma.cabinetdentaire.repository;

import ma.cabinetdentaire.entity.Patient;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //c'est éqivalent de @Component
//@Scope("singleton") il faut le faire dans ma class et pas dans repository , service et controller, entity
public interface IPatientRepository extends JpaRepository<Patient, Long> {// on a aussi CrudRepository

}

// il va créer une classe Ipation-implt qui implent d'interface de IPatientRepository
