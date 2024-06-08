package ma.cabinetdentaire.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",
        length = 3,
        discriminatorType = DiscriminatorType.STRING
        )

public class Utilisateur extends Personne{
    String nomUtilisateur;
    String motDePasse;
    @OneToMany(mappedBy = "utilisateur")
    List<Role> roles;

}
