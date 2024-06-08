package ma.cabinetdentaire.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.entity.Utilisateur;
import ma.cabinetdentaire.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data @AllArgsConstructor @NoArgsConstructor
@Service
public class AuthService {
    @Autowired
    IUserRepository userRepository;

    public void login(String username,String password){
        Utilisateur user = userRepository.findByNomUtilisateurAndMotDePasse(username, password);
    };
}
