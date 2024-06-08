package ma.cabinetdentaire.repository;

import ma.cabinetdentaire.entity.Patient;
import ma.cabinetdentaire.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<Utilisateur, Long> {

    Utilisateur findByNomUtilisateurAndMotDePasse(String username, String password);
}
