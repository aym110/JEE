package ma.cabinetdentaire.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
public enum CategorieAntecedentsMedicaux {
    MALADIE_CHRONIQUE,
    CONTRE_INDICATION,
    AUTRE,
    MALADIE_HEREDITAIRE,
    ALLERGIE;
    Risque risque;
    String description;


}
