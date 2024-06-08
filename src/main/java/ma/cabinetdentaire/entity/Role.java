package ma.cabinetdentaire.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nomRole;
    @ElementCollection
    List<String> privileges;
    @ManyToOne
    @JoinColumn(name = "utilisateurId")
    Utilisateur utilisateur;
}
